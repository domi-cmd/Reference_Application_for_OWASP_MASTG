package com.dkronig.masvs_platform.maswe_0067;

import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.util.Base64;

import java.nio.charset.StandardCharsets;
import java.security.KeyStore;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;

/**
 * Encryption Handler for MASWE-0067
 */
public class EncryptionHandler {
    private static final String AES_KEY_ALIAS = "maswe_0067_aes_key";
    private static final String AES_MODE = "AES/GCM/NoPadding";
    private static final int GCM_TAG_LENGTH = 128;

    /**
     * Generates a new AES KEY
     *
     * @throws Exception in case key generation fails.
     */
    public static void generateKey() throws Exception {
        KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
        keyStore.load(null);

        // If key already exists, return without creating a new one
        if (keyStore.containsAlias(AES_KEY_ALIAS)) {
            return;
        }

        KeyGenerator keyGenerator = KeyGenerator
                .getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore");

        KeyGenParameterSpec spec = generateKeySpec();

        keyGenerator.init(spec);
        keyGenerator.generateKey();
    }

    /**
     * Encrypts user data (password)
     * Uses base64-encoding for encryption
     *
     * @param plaintext The data to be encrypted
     * @return The base64-encoded data
     * @throws Exception In case of encryption failing
     */
    public String encryptData(String plaintext) throws Exception {
        Cipher cipher = Cipher.getInstance(AES_MODE);

        cipher.init(Cipher.ENCRYPT_MODE, getSecretKey());

        byte[] iv = cipher.getIV();
        byte[] ciphertext = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));

        // Combine IV + Ciphertext
        byte[] combined = new byte[iv.length + ciphertext.length];
        System.arraycopy(iv, 0, combined, 0, iv.length);
        System.arraycopy(ciphertext, 0, combined, iv.length, ciphertext.length);

        return Base64.encodeToString(combined, Base64.NO_WRAP);
    }

    /**
     * Decrypts data (user password)
     *
     * @param encryptedBase64 Base64-encoded user password
     * @return Decrypted plaintext
     * @throws Exception In case of decryption failing
     */
    public String decryptData(String encryptedBase64) throws Exception {
        byte[] combined = Base64.decode(encryptedBase64, Base64.NO_WRAP);

        // Extract IV (first 12 bytes for AES-GCM)
        byte[] iv = new byte[12];
        byte[] ciphertext = new byte[combined.length - 12];

        System.arraycopy(combined, 0, iv, 0, 12);
        System.arraycopy(combined, 12, ciphertext, 0, ciphertext.length);

        Cipher cipher = Cipher.getInstance(AES_MODE);
        GCMParameterSpec spec = new GCMParameterSpec(GCM_TAG_LENGTH, iv);

        cipher.init(Cipher.DECRYPT_MODE, getSecretKey(), spec);

        byte[] plaintext = cipher.doFinal(ciphertext);
        return new String(plaintext, StandardCharsets.UTF_8);
    }

    /**
     * Helper for getting secret key from Android Keystore
     *
     * @return The secret key from AndroidKeyStore
     * @throws Exception In case the loading of the key fails.
     */
    private SecretKey getSecretKey() throws Exception {
        KeyStore ks = KeyStore.getInstance("AndroidKeyStore");
        ks.load(null);
        return (SecretKey) ks.getKey(AES_KEY_ALIAS, null);
    }

    /**
     * Helper method for generating KeyGenParameterSpec
     *
     * @return the generated spec
     */
    private static KeyGenParameterSpec generateKeySpec(){
        KeyGenParameterSpec spec = new KeyGenParameterSpec.Builder(
                AES_KEY_ALIAS,
                KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)
                .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                .setKeySize(256)
                .setUserAuthenticationRequired(false)
                .build();

        return spec;
    }
}
