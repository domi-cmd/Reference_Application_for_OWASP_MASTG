package com.dkronig.masvs_crypto.maswe_0022;

import android.content.Context;
import android.util.Base64;
import android.content.SharedPreferences;

import java.nio.charset.StandardCharsets;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Encryption Handler for MASWE-0022
 */
public class EncryptionHandler {
    private static final String SECRET_KEY_ALIAS = "maswe_0022_secret_key";
    private static final String ENCRYPTION_ALGORITHM = "AES";
    private static final String CIPHER_TRANSFORMATION = "AES/CBC/PKCS5PADDING";
    private static final String ENCRYPTION_KEY = "encryption_key";
    private static final int KEY_SIZE = 256;

    private static SharedPreferences sharedPreferences;
    private static byte[] iv = {47, -98, 3, 120, 14, -55, 89, 6, -12, 33, 9, -44, 63, -1, 77, 22};

    /**
     * Loads the SharedPreferences where crypto key is stored. Checks if a key already exists,
     * generates a new one otherwise.
     *
     * @param context Application context for accessing SharedPreferences
     * @throws Exception If key fails
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
     * Initializes SharedPreferences instance. Used for storing the secret key.
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
    private static boolean keyAlreadyExists() {
        String prefKey = sharedPreferences.getString(ENCRYPTION_KEY, null);

        if(prefKey != null){
            return true;
        }
        return false;
    }

    /**
     * Generates AES key to use.
     * Converts key to string, as to enable storing it in shared preferences (only
     * takes primitives).
     *
     * @return The Base64-encoded Secret Key
     * @throws Exception If key generation fails
     */
    private static String createKey() throws Exception{
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ENCRYPTION_ALGORITHM);
        keyGenerator.init(KEY_SIZE);
        SecretKey secretKey = keyGenerator.generateKey();

        String encodedKey = Base64.encodeToString(secretKey.getEncoded(), Base64.DEFAULT);
        return encodedKey;
    }

    /**
     * Takes the encoded secret key and stores it in SharedPreferences.
     * Gets access to the shared preferences of the calling activity.
     *
     * @param encodedKey The crypto key to be stored to SharedPreferences
     */
    private static void storeKey(String encodedKey){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(ENCRYPTION_KEY, encodedKey);
        editor.apply();
    }

    /**
     * Method for encrypting a string using strong AES with CBC.
     * Gets the key from shared preferences using a helper method.
     *
     * @param plaintext The string to be encrypted (user password)
     * @return The encrypted plaintext
     * @throws Exception If encryption fails
     */
    public String encryptData(String plaintext) throws Exception {
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        Cipher cipher = Cipher.getInstance(CIPHER_TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, getKey(), ivSpec);
        byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));

        return Base64.encodeToString(encryptedBytes, Base64.DEFAULT);
    }

    /**
     * Method for decrypting a string using strong AES with CBC.
     * Gets the key from shared preferences using a helper method.
     *
     * @param encryptedData The string to be decrypted (user password)
     * @return The decrypted text
     * @throws Exception If decryption fails
     */
    public String decryptData(String encryptedData) throws Exception {
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        Cipher cipher = Cipher.getInstance(CIPHER_TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, getKey(), ivSpec);
        byte[] decryptedBytes = cipher.doFinal(Base64.decode(encryptedData, Base64.DEFAULT));

        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

    /**
     * Helper method that retrieves the AES key from shared preferences and returns it.
     * Decodes the key from string to byte format.
     * Returns it in SecretKey form.
     *
     * @return The newly generated SecretKey
     */
    private SecretKey getKey(){
        String encodedKey = sharedPreferences.getString(ENCRYPTION_KEY, null);

        byte[] decodedKey = Base64.decode(encodedKey, Base64.DEFAULT);
        return new SecretKeySpec(decodedKey, 0, decodedKey.length, ENCRYPTION_ALGORITHM);
    }
}
