package com.dkronig.maswe_crypto.maswe_0016;

import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.util.Base64;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.KeyFactory;
import java.security.spec.X509EncodedKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import android.content.Context;
import javax.crypto.Cipher;

public class EncryptionHandler {
    private static final String PRIVATE_KEY_ALIAS = "maswe_0016_rsa_key";
    private static final String PUBLIC_KEY_FILENAME = "maswe_0016_public_key.der";
    private static Context encryptionContext;

    public static void generateKey(Context context) throws Exception {
        // Set the context
        encryptionContext = context;

        KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
        keyStore.load(null);

        // Key already exists, do nothing
        if (keyStore.containsAlias(PRIVATE_KEY_ALIAS)) {
            return;
        }

        KeyPairGenerator keyPairGenerator = KeyPairGenerator
                .getInstance(KeyProperties.KEY_ALGORITHM_RSA, "AndroidKeyStore");

        KeyGenParameterSpec spec = new KeyGenParameterSpec.Builder(
                PRIVATE_KEY_ALIAS,
                        KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)
                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_RSA_PKCS1)
                .setKeySize(2048)
                .build();

        keyPairGenerator.initialize(spec);
        keyPairGenerator.generateKeyPair();
    }

    public String encryptData(String plaintext) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");

        // The vulnerability: use an attacker-controlled imported public key
        cipher.init(Cipher.ENCRYPT_MODE, getImportedOrCreatePublicKey());

        byte[] encrypted = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));
        return Base64.encodeToString(encrypted, Base64.NO_WRAP);
    }

    public String decryptData(String encrypted) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, getPrivateKey());
        byte[] plaintext = cipher.doFinal(Base64.decode(encrypted, Base64.NO_WRAP));
        return new String(plaintext, StandardCharsets.UTF_8);
    }

    private PublicKey getImportedOrCreatePublicKey() throws Exception {
        // Check if there is a public key already stored in shared (untrusted) storage
        Uri existing = getPublicKeyUri();
        if (existing != null) {
            // If a key is found in mediastore, it is imported and used without any security checks
            return importKey(existing);
        }

        // If no key is found, a new one is generated and exported to untrusted storage
        return generateAndStorePublicKey();
    }

    private static Uri getPublicKeyUri(){
        Uri collection = MediaStore.Files.getContentUri("external");

        String selection = MediaStore.Files.FileColumns.DISPLAY_NAME + "=?";
        String[] args = new String[]{ PUBLIC_KEY_FILENAME };

        Cursor cursor = encryptionContext.getContentResolver().query(
                collection,
                new String[]{MediaStore.Files.FileColumns._ID},
                selection,
                args,
                null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                long id = cursor.getLong(
                        cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns._ID));
                cursor.close();
                return ContentUris.withAppendedId(collection, id);
            }
            cursor.close();
        }

        // Return null if no key is found
        return null;
    }

    private static PublicKey importKey(Uri keyUri) throws Exception {
        InputStream is = encryptionContext.getContentResolver().openInputStream(keyUri);
        byte[] keyBytes = readAllBytes(is);

        // This is unsafe, as we do no signature/identity/integrity checks
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        return KeyFactory.getInstance("RSA").generatePublic(spec);
    }

    private static byte[] readAllBytes(InputStream is) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        byte[] data = new byte[4096];
        int n;
        while ((n = is.read(data)) != -1) {
            buffer.write(data, 0, n);
        }
        return buffer.toByteArray();
    }

    private PublicKey generateAndStorePublicKey() throws Exception {
        // Read the existing, trusted public key from Android Keystore:
        PublicKey publicKey = getKeystorePublicKey();
        byte[] keyBytes = publicKey.getEncoded();

        ContentValues values = new ContentValues();
        values.put(MediaStore.MediaColumns.DISPLAY_NAME, PUBLIC_KEY_FILENAME);
        values.put(MediaStore.MediaColumns.MIME_TYPE, "application/octet-stream");
        values.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DOCUMENTS);

        Uri uri = encryptionContext.getContentResolver().insert(
                MediaStore.Files.getContentUri("external"), values);

        OutputStream os = encryptionContext.getContentResolver().openOutputStream(uri);
        os.write(keyBytes);
        os.close();

        return publicKey;
    }


    private PublicKey getKeystorePublicKey() throws Exception {
        KeyStore ks = KeyStore.getInstance("AndroidKeyStore");
        ks.load(null);
        return ks.getCertificate(PRIVATE_KEY_ALIAS).getPublicKey();
    }

    private PrivateKey getPrivateKey() throws Exception {
        KeyStore ks = KeyStore.getInstance("AndroidKeyStore");
        ks.load(null);
        return (PrivateKey) ks.getKey(PRIVATE_KEY_ALIAS, null);
    }
}
