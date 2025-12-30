package com.dkronig.maswe_crypto.maswe_0021;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * Encryption Handler for MASWE-0021
 */
public class EncryptionHandler {
    private static final String HASHING_ALGORITHM = "SHA-1";

    /**
     * Method used for both encryption and decryption of user passwords.
     * Upon login attempts, the login password is hashed and compared to the stored password.
     * SHA-1 is used for hashing.
     *
     * @param plaintext The string (user password) to be hashed
     * @return A hash of the plaintext
     * @throws Exception If hashing fails
     */
    public String hashData(String plaintext) throws Exception {
        // Get the algorithm used for hashing
        MessageDigest digestAlgorithm = MessageDigest.getInstance(HASHING_ALGORITHM);

        // Calculate the message digest of the plaintext String
        byte[] messageDigest = digestAlgorithm.digest(plaintext.getBytes(StandardCharsets.UTF_8));

        // Convert it to signum representation
        BigInteger signumDigest = new BigInteger(1, messageDigest);

        // Convert it to hex value
        StringBuilder hashText = new StringBuilder(signumDigest.toString(16));

        // Add preceding 0s to make it 40 digits long
        while (hashText.length() < 40) {
            hashText.insert(0, "0");
        }
        return hashText.toString();
    }
}