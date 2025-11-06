package com.dkronig.maswe_crypto.maswe_0014;

import android.content.Context;
import android.content.SharedPreferences;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import android.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class EncryptionHandler {
    private static SharedPreferences sharedPreferences;

    public static void generateDESKey(Context context) throws Exception {
        // Get access to the shared preferences of the calling activity
        sharedPreferences = context.getApplicationContext()
                .getSharedPreferences("maswe_0014_secret_key", Context.MODE_PRIVATE);

        // Very predictable seed for key generation, very low entropy
        byte[] keySeed = "01234567".getBytes(StandardCharsets.UTF_8);
        // SHA1PRNG is a very old pseudorandom number generator
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.setSeed(keySeed);

        // DES is considered broken
        KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");

        // Use 56 bit DES key
        keyGenerator.init(56, random);
        SecretKey secretKey = keyGenerator.generateKey();

        // Convert to string, as to enable storing it in shared preferences (only takes primitives)
        String encodedKey = Base64.encodeToString(secretKey.getEncoded(), Base64.DEFAULT);

        // Add key to shared preferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        // Make sure no duplicate or multiple keys are stored
        editor.clear();
        editor.putString("encryption_key", encodedKey);
        editor.apply();
    }

    // Method for encrypting a string using DES (ECB mode, which is weak)
    public String encryptDataDES(String plaintext) throws Exception {
        // Get the key from shared preferences
        String encodedKey = sharedPreferences.getString("encryption_key", null);
        byte[] decodedKey = Base64.decode(encodedKey, Base64.DEFAULT);
        SecretKey secretKey = new SecretKeySpec(decodedKey, 0, decodedKey.length,
                "DES");

        // ECB mode is considered insecure by modern standards
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));
        return Base64.encodeToString(encryptedBytes, Base64.DEFAULT);
    }

    // Method for decrypting a string using DES
    public String decryptDataDES(String encrypted) throws Exception {
        // Get the key from shared preferences
        String encodedKey = sharedPreferences.getString("encryption_key", null);
        byte[] decodedKey = Base64.decode(encodedKey, Base64.DEFAULT);
        SecretKey secretKey = new SecretKeySpec(decodedKey, 0, decodedKey.length,
                "DES");

        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.decode(encrypted, Base64.DEFAULT));
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }
}