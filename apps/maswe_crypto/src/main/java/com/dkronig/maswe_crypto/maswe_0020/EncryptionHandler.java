package com.dkronig.maswe_crypto.maswe_0020;

import android.util.Base64;

public class EncryptionHandler {

    // Method for encrypting a string by using Base64 encoding
    public String encryptData(String plaintext) throws Exception {
        return Base64.encodeToString(plaintext.getBytes(), Base64.DEFAULT);
    }

    // Method for decrypting a string by using Base64 decoding
    public String decryptData(String encrypted) throws Exception {
        return new String(Base64.decode(encrypted, Base64.DEFAULT));
    }
}