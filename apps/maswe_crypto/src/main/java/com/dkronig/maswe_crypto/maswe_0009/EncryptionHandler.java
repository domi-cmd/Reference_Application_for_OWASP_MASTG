package com.dkronig.maswe_crypto.maswe_0009;

import android.content.Context;
import android.content.SharedPreferences;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Encryption Handler for MASWE-0009
 */
public class EncryptionHandler {
    private static final String PREFS_NAME = "maswe_0009_secret_key";
    private static final String KEY_ENCRYPTION_KEY = "encryption_key";
    private static final String ALGORITHM_DES = "DES";
    private static final String ALGORITHM_PRNG = "SHA1PRNG";
    private static final String CIPHER_TRANSFORMATION = "DES/ECB/PKCS5Padding";
    private static final int DES_KEY_SIZE = 56;
    private static final String KEY_SEED = "01234567";

    private static SharedPreferences sharedPreferences;

    /**
     * Generates and stores a DES encryption key if one doesn't already exist.
     * The key is seeded with a fixed value and stored in SharedPreferences.
     *
     * Implementation notes:
     * - Uses SHA1PRNG algorithm for random number generation
     * - Generates a 56-bit DES key
     * - Key is Base64-encoded for storage
     * - Skips generation if key already exists
     *
     * @param context Application context for accessing SharedPreferences
     * @throws Exception If key generation fails
     */
    public static void generateDESKey(Context context) throws Exception {
        initializeSharedPreferences(context);

        if (keyAlreadyExists()) {
            return;
        }

        SecretKey secretKey = generateKey();
        storeKey(secretKey);
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

    /**
     * Generates a DES secret key using a seeded SecureRandom instance.
     *
     * Implementation details:
     * - Uses fixed seed for deterministic key generation
     * - Employs SHA1PRNG algorithm
     * - Generates 56-bit DES key
     *
     * @return Generated SecretKey
     * @throws Exception If key generation fails
     */
    private static SecretKey generateKey() throws Exception {
        byte[] keySeed = KEY_SEED.getBytes(StandardCharsets.UTF_8);

        SecureRandom random = SecureRandom.getInstance(ALGORITHM_PRNG);
        random.setSeed(keySeed);

        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM_DES);
        keyGenerator.init(DES_KEY_SIZE, random);

        return keyGenerator.generateKey();
    }

    /**
     * Stores the secret key in SharedPreferences as a Base64-encoded string.
     *
     * @param secretKey The key to store
     */
    private static void storeKey(SecretKey secretKey) {
        String encodedKey = android.util.Base64.encodeToString(
                secretKey.getEncoded(),
                android.util.Base64.DEFAULT);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_ENCRYPTION_KEY, encodedKey);
        editor.apply();
    }

    /**
     * Encrypts plaintext data using DES encryption in ECB mode.
     *
     * @param plaintext The data to encrypt
     * @return Base64-encoded encrypted data
     * @throws Exception If encryption fails
     */
    public String encryptDataDES(String plaintext) throws Exception {
        Cipher cipher = Cipher.getInstance(CIPHER_TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, getKey());

        byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    /**
     * Decrypts Base64-encoded encrypted data using DES decryption.
     *
     * @param encryptedData Base64-encoded encrypted data
     * @return Decrypted plaintext
     * @throws Exception If decryption fails
     */
    public String decryptDataDES(String encryptedData) throws Exception {
        Cipher cipher = Cipher.getInstance(CIPHER_TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, getKey());

        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
        return new String(decryptedBytes, StandardCharsets.UTF_8);
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
        String encodedKey = sharedPreferences.getString(KEY_ENCRYPTION_KEY, null);
        byte[] decodedKey = android.util.Base64.decode(encodedKey, android.util.Base64.DEFAULT);

        return new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
    }
}