package com.dkronig.maswe_crypto.maswe_0019;

import android.util.Base64;

public class EncryptionHandler {

    // Method for encrypting a string by using circular bit shifting
    public String encryptData(String plaintext) throws Exception {
        byte[] textBytes = plaintext.getBytes();

        for(int i = 0; i < textBytes.length; i++){
            // & 0xFF is to make sure the byte is unsigned
            // Only keep the first 8 bits
            // Use xor to add the 2 bytes lost on the left back on to the right end
            textBytes[i] = (byte)((textBytes[i] << 2) | ((textBytes[i] & 0xFF) >>> (6)));
        }

        return Base64.encodeToString(textBytes, Base64.DEFAULT);
    }

    // Method for decrypting a string by using circular bit shifting
    public String decryptData(String encrypted) throws Exception {
        byte[] textBytes = Base64.decode(encrypted, Base64.DEFAULT);

        for(int i = 0; i < textBytes.length; i++){
            textBytes[i] = (byte)(((textBytes[i] & 0xFF) >>> 2) | (textBytes[i] << (6)));
        }
        
        return new String(textBytes);
    }
}