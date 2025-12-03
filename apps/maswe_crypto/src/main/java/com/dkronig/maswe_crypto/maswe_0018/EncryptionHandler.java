package com.dkronig.maswe_crypto.maswe_0018;

import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.util.Base64;

import com.dkronig.maswe_crypto.maswe_0026.BankCommand;

import java.nio.charset.StandardCharsets;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

import javax.crypto.Cipher;

public class EncryptionHandler {
    private static final String RSA_KEY_ALIAS = "maswe_0018_rsa_key";

    public static void generateKey() throws Exception {
        KeyStore ks = KeyStore.getInstance("AndroidKeyStore");
        ks.load(null);

        // Key already exists, do nothing
        if (ks.containsAlias(RSA_KEY_ALIAS)) {
            return;
        }

        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KeyProperties.KEY_ALGORITHM_RSA, "AndroidKeyStore");

        KeyGenParameterSpec spec = new KeyGenParameterSpec.Builder(
                RSA_KEY_ALIAS,
                        KeyProperties.PURPOSE_ENCRYPT |
                        KeyProperties.PURPOSE_DECRYPT)
                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_RSA_PKCS1)
                .setKeySize(2048)
                .setUserAuthenticationRequired(false)
                .setUnlockedDeviceRequired(false)
                .setIsStrongBoxBacked(false)
                .setUserAuthenticationValidWhileOnBody(true)
                .build();

        keyPairGenerator.initialize(spec);
        keyPairGenerator.generateKeyPair();
    }


    public String encryptData(String plaintext) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, getPublicKey());
        byte[] encryptedData = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));
        return Base64.encodeToString(encryptedData, Base64.NO_WRAP);
    }


    public String decryptData(String encrypted) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, getPrivateKey());
        byte[] plaintext = cipher.doFinal(Base64.decode(encrypted, Base64.NO_WRAP));
        return new String(plaintext, "UTF-8");
    }

    private static PublicKey getPublicKey() throws Exception {
        KeyStore ks = KeyStore.getInstance("AndroidKeyStore");
        ks.load(null);
        return ks.getCertificate(RSA_KEY_ALIAS).getPublicKey();
    }
    private static PrivateKey getPrivateKey() throws Exception {
        KeyStore ks = KeyStore.getInstance("AndroidKeyStore");
        ks.load(null);
        return (PrivateKey) ks.getKey(RSA_KEY_ALIAS, null);
    }
}
