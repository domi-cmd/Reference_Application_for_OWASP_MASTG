package com.dkronig.maswe_crypto.maswe_0015;

import android.util.Base64;
import android.content.Context;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;
import javax.crypto.Cipher;

/**
 * Encryption Handler for MASWE-0015
 */
public class EncryptionHandler {
    private static final String RSA_KEY_ALIAS = "maswe_0015_rsa_key";
    private static final String SIGNING_ALGORITHM = "SHA1withRSA";
    private static final String CIPHER_TRANSFORMATION = "RSA/ECB/PKCS1Padding";
    private static final String KEYSTORE_PASSWORD = "Xk9$wR2!dF7pLq4Z";
    private static final String KEY_PASSWORD = "S7v!Tz8#uK2qRj5M";
    // placed in /assets/
    private static final String KEYSTORE_FILE = "maswe_0015_keystore.bks";

    private static KeyStore keyStore;

    // Load BouncyCastle once
    static {
        if (Security.getProvider("BC") == null) {
            Security.addProvider(new BouncyCastleProvider());
        }
    }

    /**
     * Loads the BKS Keystore from the BC provider.
     * First checks if a keystore has already been loaded.
     *
     * @param context Application context for accessing SharedPreferences
     * @throws Exception If loading of keystore fails
     */
    public static void loadBksKeystore(Context context) throws Exception {
        if(keyStore != null){
            return;
        }

        keyStore = KeyStore.getInstance("BKS", "BC");

        try(InputStream inputStream = context.getAssets().open(KEYSTORE_FILE)) {
            keyStore.load(inputStream, KEYSTORE_PASSWORD.toCharArray());
        }
    }

    /**
     * Encrypts plaintext data using RSA encryption in ECB mode.
     *
     * @param plaintext The data to encrypt
     * @return Base64-encoded encrypted data
     * @throws Exception If encryption fails
     */
    public String encryptData(String plaintext) throws Exception {
        Cipher cipher = Cipher.getInstance(CIPHER_TRANSFORMATION, "BC");
        cipher.init(Cipher.ENCRYPT_MODE, getPublicKey());

        byte[] encrypted = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));

        return Base64.encodeToString(encrypted, Base64.NO_WRAP);
    }

    /**
     * Decrypts Base64-encoded encrypted data using RSA decryption.
     *
     * @param encryptedData Base64-encoded encrypted data
     * @return Decrypted plaintext
     * @throws Exception If decryption fails
     */
    public String decryptData(String encryptedData) throws Exception {
        Cipher cipher = Cipher.getInstance(CIPHER_TRANSFORMATION, "BC");
        cipher.init(Cipher.DECRYPT_MODE, getPrivateKey());

        byte[] decoded = Base64.decode(encryptedData, Base64.NO_WRAP);
        byte[] plain = cipher.doFinal(decoded);

        return new String(plain, StandardCharsets.UTF_8);
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

        byte[] sigBytes = signature.sign();

        return Base64.encodeToString(sigBytes, Base64.NO_WRAP);
    }

    /**
     * Used for verifying the signature of a bank command.
     *
     * @param command The command to be verified
     * @return Boolean, True if verification is successful
     * @throws Exception If verification process fails
     */
    public static boolean verify(BankCommand command) throws Exception {
        String payload = command.command + command.amountEuros + command.timestamp + command.nonce;

        Signature signature = Signature.getInstance(SIGNING_ALGORITHM);
        signature.initVerify(getPublicKey());
        signature.update(payload.getBytes(StandardCharsets.UTF_8));

        return signature.verify(Base64.decode(command.signature, Base64.NO_WRAP));
    }

    /**
     * Helper method that retrieves the RSA public key from keystore.
     *
     * @return The RSA public key
     * @throws Exception If retrieval of the key fails
     */
    private static PublicKey getPublicKey() throws Exception {
        if (keyStore == null) throw new IllegalStateException("Keystore not loaded");
        return keyStore.getCertificate(RSA_KEY_ALIAS).getPublicKey();
    }

    /**
     * Helper method that retrieves the RSA private key from keystore.
     *
     * @return The RSA private key
     * @throws Exception If retrieval of the key fails
     */
    private static PrivateKey getPrivateKey() throws Exception {
        if (keyStore == null) throw new IllegalStateException("Keystore not loaded");
        return (PrivateKey) keyStore.getKey(RSA_KEY_ALIAS, KEY_PASSWORD.toCharArray());
    }

}
