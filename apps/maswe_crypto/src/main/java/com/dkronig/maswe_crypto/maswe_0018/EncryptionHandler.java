package com.dkronig.maswe_crypto.maswe_0018;

import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.util.Base64;

import java.nio.charset.StandardCharsets;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;

/**
 * Encryption Handler for MASWE-0018
 */
public class EncryptionHandler {
    private static final String RSA_KEY_ALIAS = "maswe_0018_rsa_key";
    private static final String CIPHER_TRANSFORMATION = "RSA/ECB/PKCS1Padding";
    private static final String KEYSTORE = "AndroidKeyStore";
    private static final int KEY_SIZE = 2048;

    /**
     * Loads AndroidKeystore to manage RSA Key used for encryption and decryption.
     * Creates and stores said key in AndroidKeystore.
     *
     * Features:
     *  - Generates an RSA key
     *  - Skips generation if key already exists in keystore
     *
     * @throws Exception If key generation fails
     */
    public static void generateKey() throws Exception {
        KeyStore keyStore = KeyStore.getInstance(KEYSTORE);
        keyStore.load(null);

        if (keyStore.containsAlias(RSA_KEY_ALIAS)) {
            return;
        }

        createAndStoreKeyPair();
    }

    /**
     * Creates an RSA key for encryption and decryption.
     * Key is stored in android keystore.
     *
     * @throws Exception If key generation fails
     */
    private static void createAndStoreKeyPair() throws Exception{
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(
                KeyProperties.KEY_ALGORITHM_RSA,
                KEYSTORE);

        KeyGenParameterSpec spec = new KeyGenParameterSpec
                .Builder(RSA_KEY_ALIAS,
                KeyProperties.PURPOSE_ENCRYPT |
                        KeyProperties.PURPOSE_DECRYPT)
                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_RSA_PKCS1)
                .setKeySize(KEY_SIZE)
                .setUserAuthenticationRequired(false)
                .setUnlockedDeviceRequired(false)
                .setIsStrongBoxBacked(false)
                .setUserAuthenticationValidWhileOnBody(true)
                .build();

        keyPairGenerator.initialize(spec);
        keyPairGenerator.generateKeyPair();
    }

    /**
     * Encrypts plaintext data using RSA with ECB encryption.
     *
     * @param plaintext The data to encrypt
     * @return Base64-encoded encrypted data
     * @throws Exception If encryption fails
     */
    public String encryptData(String plaintext) throws Exception {
        Cipher cipher = Cipher.getInstance(CIPHER_TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, getPublicKey());
        byte[] encryptedData = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));

        return Base64.encodeToString(encryptedData, Base64.NO_WRAP);
    }

    /**
     * Decrypts data (user password) using RSA with ECB.
     *
     * @param encryptedData The data to decrypt
     * @return The decrypted plaintext
     * @throws Exception If decryption fails
     */
    public String decryptData(String encryptedData) throws Exception {
        Cipher cipher = Cipher.getInstance(CIPHER_TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, getPrivateKey());
        byte[] plaintext = cipher.doFinal(Base64.decode(encryptedData, Base64.NO_WRAP));

        return new String(plaintext, StandardCharsets.UTF_8);
    }

    /**
     * Helper method that retrieves the public key from AndroidKeystore.
     *
     * @return The public key
     * @throws Exception If retrieval of key fails
     */
    private static PublicKey getPublicKey() throws Exception {
        KeyStore keyStore = KeyStore.getInstance(KEYSTORE);
        keyStore.load(null);

        return keyStore.getCertificate(RSA_KEY_ALIAS).getPublicKey();
    }

    /**
     * Helper method that retrieves the private key from AndroidKeystore.
     *
     * @return The private key
     * @throws Exception If retrieval of key fails
     */
    private static PrivateKey getPrivateKey() throws Exception {
        KeyStore keyStore = KeyStore.getInstance(KEYSTORE);
        keyStore.load(null);

        return (PrivateKey) keyStore.getKey(RSA_KEY_ALIAS, null);
    }
}
