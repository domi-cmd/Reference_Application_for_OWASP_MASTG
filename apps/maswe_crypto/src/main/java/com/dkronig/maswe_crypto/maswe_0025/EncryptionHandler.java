package com.dkronig.maswe_crypto.maswe_0025;

import android.content.Context;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.util.Base64;
import java.security.Signature;
import java.nio.charset.StandardCharsets;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;

public class EncryptionHandler {
    private static final String rsaKeyAlias = "maswe_0025_rsa_key";

    public static void generateKey(Context context) throws Exception {
        KeyStore ks = KeyStore.getInstance("AndroidKeyStore");
        ks.load(null);

        // Key already exists, do nothing
        if (ks.containsAlias(rsaKeyAlias)) {
            return;
        }

        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KeyProperties.KEY_ALGORITHM_RSA, "AndroidKeyStore");

        KeyGenParameterSpec spec = new KeyGenParameterSpec.Builder(
                rsaKeyAlias,
                KeyProperties.PURPOSE_SIGN |
                        KeyProperties.PURPOSE_VERIFY |
                        KeyProperties.PURPOSE_ENCRYPT |
                        KeyProperties.PURPOSE_DECRYPT
        )
                .setDigests(KeyProperties.DIGEST_SHA1) // or SHA-256
                .setSignaturePaddings(KeyProperties.SIGNATURE_PADDING_RSA_PKCS1)
                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_RSA_PKCS1)
                .setKeySize(2048)
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

    public static String sign(String message) throws Exception {
        Signature s = Signature.getInstance("SHA1withRSA");
        s.initSign(getPrivateKey());
        s.update(message.getBytes("UTF-8"));
        byte[] sig = s.sign();
        return Base64.encodeToString(sig, Base64.NO_WRAP);
    }

    public static boolean verify(BankCommand command) throws Exception {
        String payload = command.command + command.amountEuros + command.timestamp
                + command.nonce;
        Signature v = Signature.getInstance("SHA1withRSA");
        v.initVerify(getPublicKey());
        v.update(payload.getBytes("UTF-8"));
        return v.verify(Base64.decode(command.signature, Base64.NO_WRAP));
    }

    private static PublicKey getPublicKey() throws Exception {
        KeyStore ks = KeyStore.getInstance("AndroidKeyStore");
        ks.load(null);
        return ks.getCertificate(rsaKeyAlias).getPublicKey();
    }
    private static PrivateKey getPrivateKey() throws Exception {
        KeyStore ks = KeyStore.getInstance("AndroidKeyStore");
        ks.load(null);
        return (PrivateKey) ks.getKey(rsaKeyAlias, null);
    }

}
