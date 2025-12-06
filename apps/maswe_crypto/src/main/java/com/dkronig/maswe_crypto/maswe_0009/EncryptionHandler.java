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

public class EncryptionHandler {
    //private static SecretKey secretKey;
    private static SharedPreferences sharedPreferences;

    public static void generateDESKey(Context context) throws Exception {
        // Check if there is a key already generated
        sharedPreferences = context.getApplicationContext()
                .getSharedPreferences("maswe_0009_secret_key", Context.MODE_PRIVATE);
        String prefKey = sharedPreferences.getString("encryption_key", null);

        // If a key already exists, do not create a new one
        if(prefKey != null){
            return;
        }

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

        // Convert it to string, as to enable storing it in shared preferences (only takes primitives)
        String encodedKey = android.util.Base64.encodeToString(secretKey.getEncoded(),
                android.util.Base64.DEFAULT);

        // Store the key to sharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("encryption_key", encodedKey);
        editor.apply();
    }

    // Method for encrypting a string using DES (ECB mode, which is weak)
    public String encryptDataDES(String plaintext) throws Exception {
        // ECB mode is considered insecure by modern standards
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, getKey());
        byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    // Method for decrypting a string using DES
    public String decryptDataDES(String encrypted) throws Exception {
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, getKey());
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encrypted));
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

    // Helper function that retrieves the secret key from its shared preferences file and returns it
    private SecretKey getKey(){
        // Get the key from shared preferences
        String encodedKey = sharedPreferences.getString("encryption_key", null);
        // Regenerate SecretKey from retrieved encodedKey string
        byte[] decodedKey = android.util.Base64.decode(encodedKey, android.util.Base64.DEFAULT);
        return new SecretKeySpec(decodedKey, 0, decodedKey.length,
                "AES");
    }
}