package com.dkronig.maswe_crypto.maswe_0027;

import android.util.Base64;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class EncryptionHandler {
    private static SecretKey secretKey;
    private static IvParameterSpec ivSpec;


    public static void generateAESKey() throws Exception {
        // Generate AES key
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256);
        secretKey = keyGenerator.generateKey();

        // Generate initialization vector for using AES with CBC
        // 16 bytes = 128 bits for AES block size
        byte[] iv = new byte[16];
        Random javaRandom = new Random(System.currentTimeMillis());
        javaRandom.nextBytes(iv);

        // Store it in local variable
        ivSpec = new IvParameterSpec(iv);
    }

    // Method for encrypting a string using strong AES with CBC
    public String encryptData(String plaintext) throws Exception {
        // USE AES in CBC mode for safe and modern encryption
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);
        byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));
        return Base64.encodeToString(encryptedBytes, Base64.DEFAULT);
    }

    // Method for decrypting a string using strong AES with CBC
    public String decryptData(String encrypted) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);
        byte[] decryptedBytes = cipher.doFinal(Base64.decode(encrypted, Base64.DEFAULT));
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }
}
