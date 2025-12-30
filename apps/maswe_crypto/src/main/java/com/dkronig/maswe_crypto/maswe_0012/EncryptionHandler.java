package com.dkronig.maswe_crypto.maswe_0012;

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

public class EncryptionHandler {
    private static final String rsaKeyAlias = "maswe_0012_rsa_key";
    private static final String CIPHER_TRANSFORMATION = "RSA/ECB/PKCS1Padding";
    private static final String SIGNING_ALGORITHM = "SHA1withRSA";
    private static final String KEYSTORE = "AndroidKeyStore";

    /**
     * Loads AndroidKeystore to manage RSA Key used for encryption and decryption.
     * Creates and stores said key in AndroidKeystore.
     *
     * Features:
     *  - Generates an RSA key
     *  - Key is Base64-encoded for storage
     *  - Skips generation if keystore is already loaded
     *
     * @throws Exception If key generation fails
     */
    public static void generateKey() throws Exception {
        KeyStore keyStore = KeyStore.getInstance(KEYSTORE);
        keyStore.load(null);

        // Key already exists, do nothing
        if (keyStore.containsAlias(rsaKeyAlias)) {
            return;
        }

        createAndStoreKey();
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
     * @throws Exception If verification process fails
     */
    public static boolean verify(BankCommand command) throws Exception {
        String payload = command.command + command.amountEuros + command.timestamp
                + command.nonce;
        Signature v = Signature.getInstance(SIGNING_ALGORITHM);

        v.initVerify(getPublicKey());
        v.update(payload.getBytes(StandardCharsets.UTF_8));

        return v.verify(Base64.decode(command.signature, Base64.NO_WRAP));
    }

    /**
     * Creates an RSA PKCS1 key for encryption and decryption.
     * Key is stored in android keystore.
     *
     * @throws Exception If key generation fails
     */
    private static void createAndStoreKey() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator
                .getInstance(KeyProperties.KEY_ALGORITHM_RSA, KEYSTORE);

        KeyGenParameterSpec spec = new KeyGenParameterSpec.Builder(
                rsaKeyAlias,
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
     * Helper method that retrieves the public key from AndroidKeystore.
     *
     * @return The public key
     * @throws Exception If retrieval of key fails
     */
    private static PublicKey getPublicKey() throws Exception {
        KeyStore ks = KeyStore.getInstance(KEYSTORE);
        ks.load(null);

        return ks.getCertificate(rsaKeyAlias).getPublicKey();
    }

    /**
     * Helper method that retrieves the private key from AndroidKeystore.
     *
     * @return The private key
     * @throws Exception If retrieval of key fails
     */
    private static PrivateKey getPrivateKey() throws Exception {
        KeyStore ks = KeyStore.getInstance(KEYSTORE);
        ks.load(null);

        return (PrivateKey) ks.getKey(rsaKeyAlias, null);
    }
}
