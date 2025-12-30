package com.dkronig.maswe_crypto.maswe_0010;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Base64;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class EncryptionHandler {
    private static final String PREFS_NAME = "maswe_0010_secret_key";
    private static final String KEY_ENCRYPTION_KEY = "encryption_key";
    private static final String ALGORITHM_PBKDF2 = "PBKDF2WithHmacSHA256";
    private static final String CIPHER_TRANSFORMATION = "AES/CBC/PKCS5Padding";
    private static final int ENCRYPTION_ITERATIONS = 10;
    private static final int KEY_LENGTH = 128;
    private static final byte[] SALT = new byte[16];
    private static final int IV_LENGTH = 16;
    private static final String PASSWORD_FOR_KEY_DERIVATION = "password";

    private static SharedPreferences sharedPreferences;

    /**
     * Generates and stores a AES encryption key if one doesn't already exist.
     * The key is seeded with a fixed value and stored in SharedPreferences.
     *
     * Implementation notes:
     * - Uses PBKDF2 algorithm for secret key generation
     * - Generates a 128-bit AES key
     * - Key is Base64-encoded for storage
     * - Skips generation if key already exists
     *
     * @param context Application context for accessing SharedPreferences
     * @throws Exception If key generation fails
     */
    public static void setupEncryption(Context context) throws Exception {
        initializeSharedPreferences(context);

        if(keyAlreadyExists()){
            return;
        }

        SecretKey secretKey = generateKey();
        storeKey(secretKey);
    }

    /**
     * Encrypts plaintext data using AES encryption in CBC mode.
     *
     * @param plaintext The data to encrypt
     * @return Base64-encoded encrypted data
     * @throws Exception If encryption fails
     */
    public String encryptData(String plaintext) throws Exception {
        Cipher cipher = Cipher.getInstance(CIPHER_TRANSFORMATION);

        // Generate random IV
        byte[] iv = new byte[IV_LENGTH];
        new SecureRandom().nextBytes(iv);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

        // Encrypt the plaintext string
        cipher.init(Cipher.ENCRYPT_MODE, getKey(), ivParameterSpec);
        byte[] plaintextBytes = plaintext.getBytes(StandardCharsets.UTF_8);
        byte[] ciphertext = cipher.doFinal(plaintextBytes);

        // Combine both ciphered text and iv for storage
        byte[] combinedIvCiphertext = new byte[iv.length + ciphertext.length];
        System.arraycopy(iv, 0, combinedIvCiphertext, 0, iv.length);
        System.arraycopy(ciphertext, 0, combinedIvCiphertext, iv.length, ciphertext.length);

        // Convert ciphertext bytes to a string (Base64) and return it
        return Base64.getEncoder().encodeToString(combinedIvCiphertext);
    }

    /**
     * Decrypts Base64-encoded encrypted data using AES decryption.
     *
     * @param encryptedData Base64-encoded encrypted data
     * @return Decrypted plaintext
     * @throws Exception If decryption fails
     */
    public String decryptData(String encryptedData) throws Exception {
        byte[] combined = Base64.getDecoder().decode(encryptedData);

        // Extract IV
        byte[] iv = new byte[IV_LENGTH];
        byte[] encryptedBytes = new byte[combined.length - IV_LENGTH];
        System.arraycopy(combined, 0, iv, 0, IV_LENGTH);
        System.arraycopy(combined, IV_LENGTH, encryptedBytes, 0, encryptedBytes.length);

        // Decrypt ciphertext
        Cipher cipher = Cipher.getInstance(CIPHER_TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, getKey(), new IvParameterSpec(iv));
        byte[] decrypted = cipher.doFinal(encryptedBytes);
        return new String(decrypted, "UTF-8");
    }

    /**
     * Initializes SharedPreferences instance.
     *
     * @param context Application context
     */
    private static void initializeSharedPreferences(Context context) {
        sharedPreferences = context.getApplicationContext()
                .getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    /**
     * Checks if an encryption key already exists in SharedPreferences.
     *
     * @return true if key exists, false otherwise
     */
    private static boolean keyAlreadyExists() {
        String existingKey = sharedPreferences.getString(KEY_ENCRYPTION_KEY, null);
        return existingKey != null;
    }

    private static SecretKey generateKey() throws Exception {
        PBEKeySpec secretKeySpec = new PBEKeySpec(PASSWORD_FOR_KEY_DERIVATION.toCharArray(), SALT,
                ENCRYPTION_ITERATIONS, KEY_LENGTH);

        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(ALGORITHM_PBKDF2);

        byte[] keyBytes = secretKeyFactory.generateSecret(secretKeySpec).getEncoded();
        SecretKey secretKey = new SecretKeySpec(keyBytes, "AES");

        return secretKey;
    }

    private static void storeKey(SecretKey secretKey){
        String encodedKey = android.util.Base64.encodeToString(
                secretKey.getEncoded(),
                android.util.Base64.DEFAULT);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_ENCRYPTION_KEY, encodedKey);
        editor.apply();
    }

    /**
     * Retrieves the stored encryption key from SharedPreferences.
     *
     * Note: The method specifies "AES" algorithm in SecretKeySpec constructor,
     * but the key is actually a DES key. This demonstrates algorithm mismatch
     * in key specification.
     *
     * @return The stored SecretKey
     */
    private SecretKey getKey(){
        String encodedKey = sharedPreferences.getString("encryption_key", null);

        byte[] decodedKey = android.util.Base64.decode(encodedKey, android.util.Base64.DEFAULT);
        return new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
    }
}
