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

public class EncryptionHandler {
    private static SharedPreferences sharedPreferences;

    public static void generateAESKey(Context context) throws Exception {
        // First check if there is already a key generated. If so, don't generate a new one.
        // Get access to the shared preferences of the calling activity
        sharedPreferences = context.getApplicationContext()
                .getSharedPreferences("maswe_0024_secret_key", Context.MODE_PRIVATE);
        String prefKey = sharedPreferences.getString("encryption_key", null);
        String prefIV = sharedPreferences.getString("IV", null);

        // Return if key and IV have already been generated
        if(prefIV != null && prefKey != null){
            return;
        }

        // Generate AES key
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256);
        SecretKey secretKey = keyGenerator.generateKey();

        // Generate initialization vector for using AES with CBC
        // 16 bytes = 128 bits for AES block size
        byte[] iv = new byte[16];
        Random javaRandom = new Random(System.currentTimeMillis());
        javaRandom.nextBytes(iv);

        // Convert to string, as to enable storing it in shared preferences (only takes primitives)
        String encodedKey = Base64.encodeToString(secretKey.getEncoded(), Base64.DEFAULT);
        // Convert IV to string to store in next to secret key in shared preferences
        String encodedIV = Base64.encodeToString(iv, Base64.DEFAULT);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        // Add key and initialization vector to shared preferences
        editor.putString("encryption_key", encodedKey);
        editor.putString("IV", encodedIV);
        editor.apply();
    }

    // Method for encrypting a string using strong AES with CBC
    public String encryptData(String plaintext) throws Exception {
        // USE AES in CBC mode for safe and modern encryption
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        cipher.init(Cipher.ENCRYPT_MODE, getKey(), getIvSpec());
        byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));
        return Base64.encodeToString(encryptedBytes, Base64.DEFAULT);
    }

    // Method for decrypting a string using strong AES with CBC
    public String decryptData(String encrypted) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        cipher.init(Cipher.DECRYPT_MODE, getKey(), getIvSpec());
        byte[] decryptedBytes = cipher.doFinal(Base64.decode(encrypted, Base64.DEFAULT));
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

    private SecretKey getKey(){
        // Get the key from shared preferences
        String encodedKey = sharedPreferences.getString("encryption_key", null);
        // Regenerate SecretKey from retrieved encodedKey string
        byte[] decodedKey = Base64.decode(encodedKey, Base64.DEFAULT);
        return new SecretKeySpec(decodedKey, 0, decodedKey.length,
                "AES");
    }

    private IvParameterSpec getIvSpec(){
        // Get the iv from shared preferences
        String encodedIV = sharedPreferences.getString("IV", null);
        // Regenerate initialization vector from retrieved encodedIV string
        byte[] iv = Base64.decode(encodedIV, Base64.DEFAULT);
        return new IvParameterSpec(iv);
    }
}
