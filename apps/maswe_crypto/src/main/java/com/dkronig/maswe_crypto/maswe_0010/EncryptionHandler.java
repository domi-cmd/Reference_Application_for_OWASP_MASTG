package com.dkronig.maswe_crypto.maswe_0010;

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
    private static final int ENCRYPTION_ITERATIONS = 10;
    private static final int KEY_LENGTH = 128;
    private static final byte[] SALT = new byte[16];
    private static final int IV_LENGTH = 16;
    private static SecretKey secretKey;
    private static final String PASSWORD_FOR_KEY_DERIVATION = "password";


    public static void setupEncryption() throws Exception {
        PBEKeySpec secretKeySpec = new PBEKeySpec(PASSWORD_FOR_KEY_DERIVATION.toCharArray(), SALT,
                ENCRYPTION_ITERATIONS, KEY_LENGTH);
        SecretKeyFactory secretKeyFactory = SecretKeyFactory
                .getInstance("PBKDF2WithHmacSHA256");
        byte[] keyBytes = secretKeyFactory.generateSecret(secretKeySpec).getEncoded();

        // Generate the secret key
        secretKey = new SecretKeySpec(keyBytes, "AES");
    }

    //
    public String encryptData(String plaintext) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

        // Generate random IV
        byte[] iv = new byte[IV_LENGTH];
        new SecureRandom().nextBytes(iv);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

        // Encrypt the plaintext string
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);
        byte[] plaintextBytes = plaintext.getBytes(StandardCharsets.UTF_8);
        byte[] ciphertext = cipher.doFinal(plaintextBytes);

        // Combine both ciphered text and iv for storage
        byte[] combinedIvCiphertext = new byte[iv.length + ciphertext.length];
        System.arraycopy(iv, 0, combinedIvCiphertext, 0, iv.length);
        System.arraycopy(ciphertext, 0, combinedIvCiphertext, iv.length, ciphertext.length);

        // Convert ciphertext bytes to a string (Base64) and return it
        return Base64.getEncoder().encodeToString(combinedIvCiphertext);
    }


    public String decryptData(String encrypted) throws Exception {
        byte[] combined = Base64.getDecoder().decode(encrypted);

        // Extract IV
        byte[] iv = new byte[IV_LENGTH];
        byte[] encryptedBytes = new byte[combined.length - IV_LENGTH];
        System.arraycopy(combined, 0, iv, 0, IV_LENGTH);
        System.arraycopy(combined, IV_LENGTH, encryptedBytes, 0, encryptedBytes.length);

        // Decrypt ciphertext
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(iv));
        byte[] decrypted = cipher.doFinal(encryptedBytes);
        return new String(decrypted, "UTF-8");
    }
}
