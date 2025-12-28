package com.dkronig.maswe_storage.maswe_0006;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Encryption Handler for MASWE-0006
 */
public class EncryptionHandler {
    private static final String ENCRYPTION_KEY = "EncryptK";
    private static final String ALGORITHM = "DES/ECB/PKCS5Padding";
    private static final String KEY_ALGORITHM = "DES";

    /**
     * Encrypts user data (password)
     * Uses base64-encoding for encryption
     *
     * @param plaintext The data to be encrypted
     * @return The base64-encoded data
     * @throws Exception In case of encryption failing
     */
    public String encryptData(String plaintext) throws Exception {
        SecretKeySpec keySpec = createKeySpec();
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);

        byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    /**
     * Decrypts data (user password)
     *
     * @param encryptedData Base64-encoded user password
     * @return Decrypted plaintext
     * @throws Exception In case of decryption failing
     */
    public String decryptData(String encryptedData) throws Exception {
        SecretKeySpec keySpec = createKeySpec();
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, keySpec);

        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

    /**
     * Creates a secret key specification from the hardcoded key.
     *
     * @return SecretKeySpec for DES encryption
     */
    private SecretKeySpec createKeySpec() {
        return new SecretKeySpec(
                ENCRYPTION_KEY.getBytes(StandardCharsets.UTF_8),
                KEY_ALGORITHM
        );
    }
}