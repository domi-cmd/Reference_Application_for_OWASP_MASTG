package com.dkronig.masvs_crypto.maswe_0026;

import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.util.Base64;

import java.nio.charset.StandardCharsets;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import javax.crypto.Cipher;

/**
 * Encryption Handler for MASWE-0026
 */
public class EncryptionHandler {
    private static final String RSA_KEY_ALIAS = "maswe_0026_rsa_key";
    private static final String CIPHER_TRANSFORMATION = "RSA/ECB/PKCS1Padding";
    private static final String SIGNING_ALGORITHM = "SHA1withRSA";
    private static final String KEYSTORE = "AndroidKeyStore";

    /**
     * Loads AndroidKeyStore to manage RSA Key used for encryption and decryption.
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
        createAndStoreKey();
    }

    /**
     * Creates an RSA key for encryption and decryption.
     * Key is stored in android keystore.
     *
     * @throws Exception If key generation fails
     */
    private static void createAndStoreKey() throws Exception{
        KeyPairGenerator keyPairGenerator = KeyPairGenerator
                .getInstance(KeyProperties.KEY_ALGORITHM_RSA, KEYSTORE);

        KeyGenParameterSpec spec = new KeyGenParameterSpec
                .Builder(RSA_KEY_ALIAS,
                KeyProperties.PURPOSE_SIGN |
                        KeyProperties.PURPOSE_VERIFY |
                        KeyProperties.PURPOSE_ENCRYPT |
                        KeyProperties.PURPOSE_DECRYPT)
                .setDigests(KeyProperties.DIGEST_SHA1)
                .setSignaturePaddings(KeyProperties.SIGNATURE_PADDING_RSA_PKCS1)
                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_RSA_PKCS1)
                .setKeySize(2048)
                .build();

        keyPairGenerator.initialize(spec);
        keyPairGenerator.generateKeyPair();
    }

    /**
     * Encrypts plaintext data using RSA encryption.
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
     * Decrypts data (user password) using RSA.
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
     * Used for signing a string message which consists of a bank command.
     *
     * @param message The bank command to be signed
     * @return The signed bank command
     * @throws Exception If signing process fails
     */
    public static String sign(String message) throws Exception {
        Signature signature = Signature.getInstance(SIGNING_ALGORITHM);
        signature.initSign(getPrivateKey());
        signature.update(message.getBytes(StandardCharsets.UTF_8));

        byte[] signatureBytes = signature.sign();
        return Base64.encodeToString(signatureBytes, Base64.NO_WRAP);
    }

    /**
     * Used for verifying the signature of a bank command.
     *
     * @param command The command to be verified
     * @return Boolean, True if verification is successful
     */
    public static boolean verify(BankCommand command) {
        return true;
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
