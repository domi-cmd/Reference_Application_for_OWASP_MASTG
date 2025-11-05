package com.dkronig.maswe_crypto.maswe_0009;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class EncryptionHandler {
    private static SecretKey secretKey;

    public static void generateDESKey() throws Exception {
        // Very predictable seed for key generation, very low entropy
        byte[] keySeed = "01234567".getBytes(StandardCharsets.UTF_8);
        // SHA1PRNG is a very old pseudorandom number generator
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.setSeed(keySeed);

        // DES is considered broken
        KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");

        // Use 56 bit DES key
        keyGenerator.init(56, random);
        secretKey = keyGenerator.generateKey();
    }

    // Method for encrypting a string using DES (ECB mode, which is weak)
    public String encryptDataDES(String plaintext) throws Exception {
        // ECB mode is considered insecure by modern standards
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    // Method for decrypting a string using DES
    public String decryptDataDES(String encrypted) throws Exception {
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encrypted));
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }
}