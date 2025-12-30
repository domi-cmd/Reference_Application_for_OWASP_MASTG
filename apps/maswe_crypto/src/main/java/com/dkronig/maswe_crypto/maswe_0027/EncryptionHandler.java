package com.dkronig.maswe_crypto.maswe_0027;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import java.nio.charset.StandardCharsets;
import java.util.Random;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Encryption Handler for MASWE-0027
 */
public class EncryptionHandler {
    private static final String SECRET_KEY_ALIAS = "maswe_0024_secret_key";
    private static final String ENCRYPTION_ALGORITHM = "AES";
    private static final String CIPHER_TRANSFORMATION = "AES/CBC/PKCS5PADDING";
    private static final String ENCRYPTION_KEY = "encryption_key";
    private static final String IV = "IV";
    private static final int KEY_SIZE = 256;

    private static SharedPreferences sharedPreferences;

    /**
     * Loads the SharedPreferences where crypto key is stored. Checks if a key already exists,
     * generates a new one otherwise.
     * Stores the key and initialization vector in the SharedPreferences file.
     *
     * @param context Application context for accessing SharedPreferences
     * @throws Exception If key or IV generation fails
     */
    public static void generateAESKey(Context context) throws Exception {
        initializeSharedPreferences(context);

        if(keyAlreadyExists()){
            return;
        }

        String encodedKey = createKey();
        String encodedIV = createIV();
        
        storeKeyAndIV(encodedKey, encodedIV);
    }

    /**
     * Initializes SharedPreferences instance.
     *
     * @param context Application context
     */
    private static void initializeSharedPreferences(Context context) {
        sharedPreferences = context.getApplicationContext()
                .getSharedPreferences(SECRET_KEY_ALIAS, Context.MODE_PRIVATE);
    }

    /**
     * Checks if an encryption key and initialization vector already exist in SharedPreferences.
     *
     * @return true if key and iv exists, false otherwise
     */
    private static boolean keyAlreadyExists() {
        String prefKey = sharedPreferences.getString(ENCRYPTION_KEY, null);
        String prefIV = sharedPreferences.getString(IV, null);

        if(prefIV != null && prefKey != null){
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
    private static String createKey() throws Exception{
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ENCRYPTION_ALGORITHM);
        keyGenerator.init(KEY_SIZE);
        SecretKey secretKey = keyGenerator.generateKey();

        String encodedKey = Base64.encodeToString(secretKey.getEncoded(), Base64.DEFAULT);
        return encodedKey;
    }

    /**
     * Generate initialization vector for using AES.
     * Use 16 bytes = 128 bits for AES block size
     * Convert IV to string to store in next to secret key in shared preferences
     *
     * @return The Base64-encoded initialization vector.
     */
    private static String createIV(){
        byte[] iv = new byte[16];
        Random javaRandom = new Random(System.currentTimeMillis());
        javaRandom.nextBytes(iv);


        String encodedIV = Base64.encodeToString(iv, Base64.DEFAULT);
        return encodedIV;
    }

    /**
     * Takes the encoded secret key and initialization vector and stores them in SharedPreferences.
     *
     * Get access to the shared preferences of the calling activity
     *
     * @param encodedKey The crypto key to be stored to SharedPreferences
     * @param encodedIV The initialization vector to be stored to SharedPreferences
     */
    private static void storeKeyAndIV(String encodedKey, String encodedIV){
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(ENCRYPTION_KEY, encodedKey);
        editor.putString(IV, encodedIV);
        editor.apply();
    }

    /**
     * Method for encrypting a string using strong AES with CBC.
     * Gets the key and iv from shared preferences using helper functions.
     *
     * @param plaintext The string to be encrypted (user password)
     * @return The encrypted plaintext
     * @throws Exception If encryption fails
     */
    public String encryptData(String plaintext) throws Exception {
        Cipher cipher = Cipher.getInstance(CIPHER_TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, getKey(), getIvSpec());

        byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));
        return Base64.encodeToString(encryptedBytes, Base64.DEFAULT);
    }

    /**
     * Method for decrypting a string using strong AES with CBC.
     * Gets the key and iv from shared preferences using helper functions.
     *
     * @param encryptedData The string to be decrypted (user password)
     * @return The decrypted text
     * @throws Exception If decryption fails
     */
    public String decryptData(String encryptedData) throws Exception {
        Cipher cipher = Cipher.getInstance(CIPHER_TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, getKey(), getIvSpec());

        byte[] decryptedBytes = cipher.doFinal(Base64.decode(encryptedData, Base64.DEFAULT));
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

    /**
     * Helper method that retrieves the AES key from shared preferences.
     * Decodes the key from string to byte format.
     *
     * @return The newly generated SecretKey
     */
    private SecretKey getKey(){
        String encodedKey = sharedPreferences.getString(ENCRYPTION_KEY, null);

        byte[] decodedKey = Base64.decode(encodedKey, Base64.DEFAULT);
        return new SecretKeySpec(decodedKey, 0, decodedKey.length, ENCRYPTION_ALGORITHM);
    }

    /**
     * Helper method that retrieves the IV from shared preferences and returns the IVSpec.
     * Decodes the initialization vector (IV) from string to byte format.
     *
     * @return The newly generated IvParameterSpec
     */
    private IvParameterSpec getIvSpec(){
        String encodedIV = sharedPreferences.getString(IV, null);

        byte[] iv = Base64.decode(encodedIV, Base64.DEFAULT);
        return new IvParameterSpec(iv);
    }
}
