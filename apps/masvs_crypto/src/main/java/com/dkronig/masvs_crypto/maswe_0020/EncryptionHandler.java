package com.dkronig.masvs_crypto.maswe_0020;

import android.util.Base64;

/**
 * Encryption Handler for MASWE-0020
 */
public class EncryptionHandler {
    /**
     * Method for encrypting a string (user password) by using Base64 encoding.
     *
     * @param plaintext The plaintext to be encoded
     * @return The base64-encoded plaintext
     * @throws Exception If encoding fails
     */
    public String encryptData(String plaintext) throws Exception {
        return Base64.encodeToString(plaintext.getBytes(), Base64.DEFAULT);
    }

    /**
     * Method for decrypting a string (user password) by using Base64 decoding
     *
     * @param encryptedData The string to be decrypted
     * @return The decrypted data
     * @throws Exception If decryption fails
     */
    public String decryptData(String encryptedData) throws Exception {
        return new String(Base64.decode(encryptedData, Base64.DEFAULT));
    }
}