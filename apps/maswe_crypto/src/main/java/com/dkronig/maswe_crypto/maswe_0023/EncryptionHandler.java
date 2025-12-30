package com.dkronig.maswe_crypto.maswe_0023;

import android.content.SharedPreferences;
import android.util.Base64;
import android.util.Log;
import android.content.Context;

import java.nio.charset.StandardCharsets;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Encryption Handler for MASWE-0023
 */
public class EncryptionHandler {
    private static final String SECRET_KEY_ALIAS = "maswe_0023_secret_key";
    private static final String ENCRYPTION_ALGORITHM = "AES";
    private static final String CIPHER_TRANSFORMATION_ENCRYPTION = "AES/CBC/PKCS7PADDING";
    private static final String CIPHER_TRANSFORMATION_DECRYPTION = "AES/CBC/NoPadding";
    private static final String ENCRYPTION_KEY = "encryption_key";
    private static final int KEY_SIZE = 256;
    private static final String TAG = "EncryptionHandler";

    private static SharedPreferences sharedPreferences;

    /**
     * Loads the SharedPreferences where crypto key is stored. Checks if a key already exists,
     * generates a new one otherwise.
     * Stores the key in the SharedPreferences file.
     *
     * @param context Application context for accessing SharedPreferences
     * @throws Exception If key generation fails
     */
    public static void generateAESKey(Context context) throws Exception {
        initializeSharedPreferences(context);

        if(keyAlreadyExists()){
            return;
        }

        String encodedKey = createKey();
        storeKey(encodedKey);
    }

    /**
     * Initializes SharedPreferences instance. Used for storing the secret key and the IV.
     *
     * @param context Application context
     */
    private static void initializeSharedPreferences(Context context){
        sharedPreferences = context.getApplicationContext()
                .getSharedPreferences(SECRET_KEY_ALIAS, Context.MODE_PRIVATE);
    }

    /**
     * Checks if encryption key already exists in SharedPreferences.
     *
     * @return true if key exists, false otherwise
     */
    private static boolean keyAlreadyExists(){
        String prefKey = sharedPreferences.getString(ENCRYPTION_KEY, null);

        if(prefKey != null){
            return true;
        }
        return false;
    }

    /**
     * Generates AES key to use.
     * Converts key to string, as to enable storing it in shared preferences (only takes primitives).
     *
     * @return The Base64-encoded Secret Key
     * @throws Exception If key generation fails
     */
    private static String createKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ENCRYPTION_ALGORITHM);
        keyGenerator.init(KEY_SIZE);

        SecretKey secretKey = keyGenerator.generateKey();

        String encodedKey = Base64.encodeToString(secretKey.getEncoded(), Base64.DEFAULT);
        return encodedKey;
    }

    /**
     * Takes the encoded secret key and stores it in SharedPreferences.
     * Gets access to the shared preferences of the calling activity
     *
     * @param encodedKey The crypto key to be stored to SharedPreferences
     */
    private static void storeKey(String encodedKey){
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.clear();
        editor.putString(ENCRYPTION_KEY, encodedKey);
        editor.apply();
    }

    /**
     * Method for encrypting a string using strong AES with CBC.
     * Gets the key from shared preferences using helper function.
     * Uses a zero byte initialization vector.
     *
     * @param plaintext The string to be encrypted (user password)
     * @return The encrypted plaintext
     * @throws Exception If encryption fails
     */
    public String encryptData(String plaintext) throws Exception {
        SecretKey secretKey = getKey();

        byte[] iv = new byte[16];
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        Cipher cipher = Cipher.getInstance(CIPHER_TRANSFORMATION_ENCRYPTION);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);
        byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));

        return Base64.encodeToString(encryptedBytes, Base64.DEFAULT);
    }

    /**
     * Method for decrypting a string using strong AES with CBC.
     * Gets the key from shared preferences using helper function.
     * Uses a zero-byte initialization vector.
     * Manually unpads the encrypted data which is padded with PKCS#7 padding.
     * Uses helper methods to verify integrity of data to be decrypted.
     *
     * @param encryptedData The string to be decrypted (user password)
     * @return The decrypted text
     * @throws Exception If decryption fails
     */
    public String decryptData(String encryptedData) throws Exception {
        SecretKey secretKey = getKey();

        byte[] iv = new byte[16];
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        byte[] encryptedBytes = Base64.decode(encryptedData, Base64.DEFAULT);
        if(!checkEncryptedBytesLength(encryptedBytes)){
            Log.e(TAG, "Invalid ciphertext format or length");
            return null;
        }

        Cipher cipher = Cipher.getInstance(CIPHER_TRANSFORMATION_DECRYPTION);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);
        byte[] decryptedBytesWithPadding = cipher.doFinal(encryptedBytes);

        int paddingLength = decryptedBytesWithPadding[decryptedBytesWithPadding.length - 1] & 0xFF;

        if(!checkPaddingLength(paddingLength)){
            Log.e(TAG, "Invalid PKCS#7 padding length");
            return null;
        }

        if(!checkPaddingValues(decryptedBytesWithPadding, paddingLength)){
            Log.e(TAG, "Invalid PKCS#7 padding value");
            return null;
        }

        byte[] plaintext = new byte[decryptedBytesWithPadding.length - paddingLength];
        System.arraycopy(decryptedBytesWithPadding, 0, plaintext, 0, plaintext.length);

        return new String(plaintext, StandardCharsets.UTF_8);
    }

    /**
     * Helper method that checks the length of the encrypted bytes. They should be padded to a
     * length of at least 16.
     *
     * @param encryptedBytes The bytes to be checked
     * @return
     */
    private boolean checkEncryptedBytesLength(byte[] encryptedBytes){
        if (encryptedBytes.length < 16) {
            return false;
        }
        return true;
    }

    /**
     * Helper method that checks for invalid padding length. Has to be larger than 1 and smaller
     * than 16 to be a valid PKCS# padding.
     *
     * @param paddingLength The length of the padding
     * @return True if the length is within the expected length range, false otherwise.
     */
    private boolean checkPaddingLength(int paddingLength){
        if (paddingLength < 1 || paddingLength > 16) {
            Log.e(TAG, "Invalid PKCS#7 padding length");
            return false;
        }
        return true;
    }

    /**
     * Helper method that checks if all padding bytes are equal to expected paddingLength.
     *
     * @param decryptedBytesWithPadding The bytes to be checked
     * @param paddingLength The given expected padding length
     * @return True if all bytes with their padding fit the expected length, false otherwise.
     */
    private boolean checkPaddingValues(byte[] decryptedBytesWithPadding, int paddingLength){
        for (int i = decryptedBytesWithPadding.length - paddingLength;
             i < decryptedBytesWithPadding.length; i++) {
            if ((decryptedBytesWithPadding[i] & 0xFF) != paddingLength) {
                return false;
            }
        }
        return true;
    }

    /**
     * Helper method that retrieves the AES key from its shared preferences file and
     * returns it.
     *
     * @return The retrieved secret key
     */
    private SecretKey getKey(){
        String encodedKey = sharedPreferences.getString(ENCRYPTION_KEY, null);

        byte[] decodedKey = Base64.decode(encodedKey, Base64.DEFAULT);
        return new SecretKeySpec(decodedKey, 0, decodedKey.length, ENCRYPTION_ALGORITHM);
    }
}
