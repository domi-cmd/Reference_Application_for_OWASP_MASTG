package com.dkronig.maswe_crypto.maswe_0021;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class EncryptionHandler {

    public String hashData(String plaintext) throws Exception {
        // Get the algorithm used for hashing (SHA-1)
        MessageDigest digestAlgorithm = MessageDigest.getInstance("SHA-1");

        // Calculate the message digest of the plaintext String
        byte[] messageDigest = digestAlgorithm.digest(plaintext.getBytes(StandardCharsets.UTF_8));

        // Convert it to signum representation
        BigInteger no = new BigInteger(1, messageDigest);

        // Convert it to hex value
        StringBuilder hashText = new StringBuilder(no.toString(16));

        // Add preceding 0s to make it 40 digits long
        while (hashText.length() < 40) {
            hashText.insert(0, "0");
        }

        // return the HashText
        return hashText.toString();
    }

}