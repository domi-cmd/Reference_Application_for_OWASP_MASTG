package com.dkronig.maswe_crypto.maswe_0010;

import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class EncryptionHandler {
    private static IvParameterSpec ivSpec;
    private static int encryptionIterations;
    private static int keyLength;
    private static byte[] salt;
    private static SecretKeyFactory secretKeyFactory;
    private static Cipher cipher;


    public static void setupEncryption() throws Exception {
        // Setup skf and cipher
        secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

        // Specify key length, amount of iterations and salt for encryption
        keyLength = 128;
        salt = new byte[0];
        encryptionIterations = 10;

        // Generate initialization vector for AES with PBKDF2
        // 16 bytes = 128 bits for AES block size
        byte[] iv = new byte[16];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(iv);

        // Store it in local variable
        ivSpec = new IvParameterSpec(iv);
    }

    //
    public String encryptData(String plaintext) throws Exception {
        // Derive the key using PBKDF2 and a helper function
        SecretKey aesKey = derivePBKDF2Key(plaintext);

        // Encrypt the plaintext string
        cipher.init(Cipher.ENCRYPT_MODE, aesKey, ivSpec);
        byte[] plainString = "Sensitive Data".getBytes(StandardCharsets.UTF_8);
        byte[] ciphertext = cipher.doFinal(plainString);

        // Convert ciphertext bytes to a string (Base64) and return it
        return Base64.getEncoder().encodeToString(ciphertext);
    }

    //
    public String decryptData(String encrypted) throws Exception {
        // Derive the key using PBKDF2 and a helper function
        return "";
    }

    private SecretKey derivePBKDF2Key(String plaintext) throws InvalidKeySpecException {
        char[] passwordChars = plaintext.toCharArray();
        PBEKeySpec keySpec = new PBEKeySpec(passwordChars, salt, encryptionIterations, keyLength);
        byte[] keyBytes = secretKeyFactory.generateSecret(keySpec).getEncoded();
        return new SecretKeySpec(keyBytes, "AES");
    }
}
