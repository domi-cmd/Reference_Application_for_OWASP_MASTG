package com.dkronig.maswe_crypto.maswe_0011;

import android.content.Context;
import android.util.Base64;

import com.dkronig.maswe_crypto.maswe_0015.BankCommand;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;

import javax.crypto.Cipher;

public class EncryptionHandler {
    private static final String RSA_KEY_ALIAS = "maswe_0011_rsa_key";
    private static final String KEYSTORE_PASSWORD = "Xk9$wR2!dF7pLq4Z";
    private static final String KEY_PASSWORD = "S7v!Tz8#uK2qRj5M";
    // placed in /assets/
    private static final String KEYSTORE_FILE = "maswe_0011_keystore.bks";
    private static KeyStore keyStore;

    // Load BouncyCastle once
    static {
        if (Security.getProvider("BC") == null) {
            Security.addProvider(new BouncyCastleProvider());
        }
    }

    public static void loadBksKeystore(Context context) throws Exception {
        // If keystore has already been loaded, do nothing and return
        if(keyStore != null){
            return;
        }

        keyStore = KeyStore.getInstance("BKS", "BC");

        try(InputStream inputStream = context.getAssets().open(KEYSTORE_FILE)) {
            keyStore.load(inputStream, KEYSTORE_PASSWORD.toCharArray());
        }
    }



    public String encryptData(String plaintext) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding", "BC");
        cipher.init(Cipher.ENCRYPT_MODE, getPublicKey());
        byte[] encrypted = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));
        return Base64.encodeToString(encrypted, Base64.NO_WRAP);
    }


    public String decryptData(String encrypted) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding", "BC");
        cipher.init(Cipher.DECRYPT_MODE, getPrivateKey());
        byte[] decoded = Base64.decode(encrypted, Base64.NO_WRAP);
        byte[] plain = cipher.doFinal(decoded);
        return new String(plain, StandardCharsets.UTF_8);
    }


    private static PublicKey getPublicKey() throws Exception {
        if (keyStore == null) throw new IllegalStateException("Keystore not loaded");
        return keyStore.getCertificate(RSA_KEY_ALIAS).getPublicKey();
    }
    private static PrivateKey getPrivateKey() throws Exception {
        if (keyStore == null) throw new IllegalStateException("Keystore not loaded");
        return (PrivateKey) keyStore.getKey(RSA_KEY_ALIAS, KEY_PASSWORD.toCharArray());
    }

}
