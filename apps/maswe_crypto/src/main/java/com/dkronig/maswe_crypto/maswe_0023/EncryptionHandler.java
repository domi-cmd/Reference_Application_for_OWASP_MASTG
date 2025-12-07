package com.dkronig.maswe_crypto.maswe_0023;

import android.content.SharedPreferences;
import android.util.Base64;
import android.util.Log;
import android.content.Context;
import java.nio.charset.StandardCharsets;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class EncryptionHandler {
    private static final String TAG = "EncryptionHandler";
    private static SharedPreferences sharedPreferences;

    public static void generateAESKey(Context context) throws Exception {
        // Check if there is a key already generated
        sharedPreferences = context.getApplicationContext()
                .getSharedPreferences("maswe_0023_secret_key", Context.MODE_PRIVATE);
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
        // USE AES in CBC mode for safe and modern encryption
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7PADDING");
        // zero IV to match decryptData()
        byte[] iv = new byte[16];
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        // Get the secret key from shared preferences
        SecretKey secretKey = getKey();

        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);
        byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));
        return Base64.encodeToString(encryptedBytes, Base64.DEFAULT);
    }

    // Method for decrypting a string using strong AES with CBC
    public String decryptData(String encrypted) throws Exception {
        byte[] encryptedBytes = Base64.decode(encrypted, Base64.DEFAULT);

        // Check for length of the encrypted bytes. They should be padded to a length of at least 16.
        if (encryptedBytes.length < 16) {
            Log.e(TAG, "Invalid ciphertext format or length");
            return null;
        }

        // Create a zero IV (16 bytes)
        byte[] iv = new byte[16];
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        // Get the secret key from shared preferences
        SecretKey secretKey = getKey();

        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);

        byte[] decryptedBytesWithPadding = cipher.doFinal(encryptedBytes);

        // Manually unpad the PKCS7
        int paddingLength = decryptedBytesWithPadding[decryptedBytesWithPadding.length - 1] & 0xFF;

        // Check for invalid padding
        if (paddingLength < 1 || paddingLength > 16) {
            Log.e(TAG, "Invalid PKCS#7 padding length");
            return null;
        }

        // Check if all padding bytes are equal to paddingLength
        for (int i = decryptedBytesWithPadding.length - paddingLength;
             i < decryptedBytesWithPadding.length; i++) {
            // Padding bytes not consistent, resulting in oracle leaking info
            if ((decryptedBytesWithPadding[i] & 0xFF) != paddingLength) {
                Log.e(TAG, "Invalid PKCS#7 padding value");
                return null;
            }
        }

        // Remove padding
        byte[] plaintext = new byte[decryptedBytesWithPadding.length - paddingLength];
        System.arraycopy(decryptedBytesWithPadding, 0, plaintext, 0, plaintext.length);

        return new String(plaintext, StandardCharsets.UTF_8);
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
