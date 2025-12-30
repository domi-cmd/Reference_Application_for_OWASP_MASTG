package com.dkronig.maswe_crypto.maswe_0019;

import android.util.Base64;

/**
 * Encryption Handler for MASWE-0019
 */
public class EncryptionHandler {
    /**
     * Encrypts a string (user password) by using circular bit shifting.
     * Converts the string to unsigned bytes, then shifts them by two bytes to the left.
     *
     * @param plaintext The string to be encrypted
     * @return Returns the encrypted plaintext
     * @throws Exception If encryption logic fails
     */
    public String encryptData(String plaintext) throws Exception {
        byte[] textBytes = plaintext.getBytes();

        for(int i = 0; i < textBytes.length; i++){
            textBytes[i] = (byte)((textBytes[i] << 2) | ((textBytes[i] & 0xFF) >>> (6)));
        }
        return Base64.encodeToString(textBytes, Base64.DEFAULT);
    }

    /**
     * Decrypts a string (user password) by using circular bit shifting.
     * Cryptographic logic is identical to the reverse of encrypt method above.
     * Shifts the bytes of the encryptedData two bytes to the right.
     *
     * @param encryptedData The encrypted string to be decrypted
     * @return Returns the decrypted plaintext
     * @throws Exception If decryption logic fails
     */
    public String decryptData(String encryptedData) throws Exception {
        byte[] textBytes = Base64.decode(encryptedData, Base64.DEFAULT);

        for(int i = 0; i < textBytes.length; i++){
            textBytes[i] = (byte)(((textBytes[i] & 0xFF) >>> 2) | (textBytes[i] << (6)));
        }
        return new String(textBytes);
    }
}