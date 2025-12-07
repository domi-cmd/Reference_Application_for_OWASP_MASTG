package com.dkronig.maswe_crypto.maswe_0022;

import android.content.Context;
import android.util.Base64;
import java.nio.charset.StandardCharsets;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import android.content.SharedPreferences;

public class EncryptionHandler {
    //private static SecretKey secretKey;
    private static SharedPreferences sharedPreferences;
    private static byte[] iv = {47, -98, 3, 120, 14, -55, 89, 6, -12, 33, 9, -44, 63, -1, 77, 22};

    public static void generateAESKey(Context context) throws Exception {
        // Check if there is a key already generated
        sharedPreferences = context.getApplicationContext()
                .getSharedPreferences("maswe_0022_secret_key", Context.MODE_PRIVATE);
        String prefKey = sharedPreferences.getString("encryption_key", null);

        // If a key already exists, do not create a new one
        if(prefKey != null){
            return;
        }

        // Generate AES key
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256);
        SecretKey secretKey = keyGenerator.generateKey();

        // Convert to string, as to enable storing it in shared preferences (only takes primitives)
        String encodedKey = Base64.encodeToString(secretKey.getEncoded(), Base64.DEFAULT);

        // Store the key to sharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        // Make sure no duplicate or multiple keys are stored
        editor.clear();
        editor.putString("encryption_key", encodedKey);
        editor.apply();
    }

    // Method for encrypting a string using strong AES with CBC
    public String encryptData(String plaintext) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");

        // Generate ivSpec from iv
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        cipher.init(Cipher.ENCRYPT_MODE, getKey(), ivSpec);
        byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));
        return Base64.encodeToString(encryptedBytes, Base64.DEFAULT);
    }

    // Method for decrypting a string using strong AES with CBC
    public String decryptData(String encrypted) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");

        // Generate ivSpec from iv
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        cipher.init(Cipher.DECRYPT_MODE, getKey(), ivSpec);
        byte[] decryptedBytes = cipher.doFinal(Base64.decode(encrypted, Base64.DEFAULT));
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

    // Helper function that retrieves the secret key from its shared preferences file and returns it
    private SecretKey getKey(){
        // Get the key from shared preferences
        String encodedKey = sharedPreferences.getString("encryption_key", null);
        // Regenerate SecretKey from retrieved encodedKey string
        byte[] decodedKey = Base64.decode(encodedKey, Base64.DEFAULT);
        return new SecretKeySpec(decodedKey, 0, decodedKey.length,
                "AES");
    }
}
