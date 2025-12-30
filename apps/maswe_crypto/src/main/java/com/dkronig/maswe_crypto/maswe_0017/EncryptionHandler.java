package com.dkronig.maswe_crypto.maswe_0017;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
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
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;

/**
 * Encryption Handler for MASWE-0017
 */
public class EncryptionHandler {
    private static final String PRIVATE_KEY_ALIAS = "maswe_0017_rsa_key";
    private static final String PUBLIC_KEY_FILENAME = "maswe_0017_public_key.der";
    private static final String CIPHER_TRANSFORMATION = "RSA/ECB/PKCS1Padding";
    private static final String KEYSTORE = "AndroidKeyStore";
    private static final int KEY_SIZE = 2048;

    private static Context encryptionContext;

    /**
     * Loads AndroidKeystore to manage RSA Key used for encryption and decryption.
     * Creates and stores said key in AndroidKeystore.
     *
     * Features:
     *  - Sets the applications context, which is needed for accessing shared storage.
     *  - Generates an RSA key
     *  - Skips generation if key already exists in keystore
     *
     * @throws Exception If key generation fails
     */
    public static void generateKey(Context context) throws Exception {
        encryptionContext = context;

        KeyStore keyStore = KeyStore.getInstance(KEYSTORE);
        keyStore.load(null);

        if (keyStore.containsAlias(PRIVATE_KEY_ALIAS)) {
            return;
        }
       createAndStoreKeyPair();
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
        cipher.init(Cipher.ENCRYPT_MODE, getImportedOrCreatePublicKey());
        byte[] encrypted = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));

        return Base64.encodeToString(encrypted, Base64.NO_WRAP);
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
     * Creates an RSA key pair for encryption and decryption.
     * Key is stored in android keystore.
     *
     * @throws Exception If key generation fails
     */
    private static void createAndStoreKeyPair() throws Exception{
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(
                KeyProperties.KEY_ALGORITHM_RSA,
                KEYSTORE);

        KeyGenParameterSpec spec = new KeyGenParameterSpec.Builder(
                PRIVATE_KEY_ALIAS,
                KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)
                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_RSA_PKCS1)
                .setKeySize(KEY_SIZE)
                .build();

        keyPairGenerator.initialize(spec);
        keyPairGenerator.generateKeyPair();
    }

    /**
     * Helper method that checks if there is a public key already stored in shared storage.
     * If a key is found in shared storage (MediaStore), it is imported and used as public key.
     * If no key is found, a new one is generated and exported to MediaStore.
     * @return
     * @throws Exception
     */
    private PublicKey getImportedOrCreatePublicKey() throws Exception {
        Uri existingPublicKeyURI = getPublicKeyUri();

        if (existingPublicKeyURI != null) {
            return importKey(existingPublicKeyURI);
        }
        return generateAndStorePublicKey();
    }

    /**
     * Get the URI of the potentially existing public key.
     *
     * @return Either the URI or null if no key is found
     */
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
        return null;
    }

    /**
     * Helper method that imports a public key given its URI. Reads the key as bytes and regenerates
     * it using X509 Encoding.
     *
     * @param keyUri The URI of the public key to be imported
     * @return The imported public key.
     * @throws Exception If imported or regeneration of key fails
     */
    private static PublicKey importKey(Uri keyUri) throws Exception {
        InputStream inputStream = encryptionContext.getContentResolver().openInputStream(keyUri);
        byte[] keyBytes = readAllBytes(inputStream);

        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        return KeyFactory.getInstance("RSA").generatePublic(spec);
    }

    /**
     * Helper method for reading bytes from input stream. Used for reading key bytes from shared
     * storage.
     *
     * @param inputStream The stream from which the bytes are read
     * @return The bytes of the read key
     * @throws IOException In case reading of bytes fails
     */
    private static byte[] readAllBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        byte[] data = new byte[4096];
        int n;
        while ((n = inputStream.read(data)) != -1) {
            buffer.write(data, 0, n);
        }
        return buffer.toByteArray();
    }

    /**
     * Helper method that takes the AndroidKeystore public key and stores it in shared Storage.
     * Reads the existing, trusted public key from Android Keystore and stores it in MediaStore.
     *
     * @return The public key that is stored
     * @throws Exception In case storing of key in MediaStore fails
     */
    private PublicKey generateAndStorePublicKey() throws Exception {
        PublicKey publicKey = getKeystorePublicKey();
        byte[] keyBytes = publicKey.getEncoded();

        ContentValues values = new ContentValues();
        values.put(MediaStore.MediaColumns.DISPLAY_NAME, PUBLIC_KEY_FILENAME);
        values.put(MediaStore.MediaColumns.MIME_TYPE, "application/octet-stream");
        values.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DOCUMENTS);

        Uri uri = encryptionContext.getContentResolver().insert(
                MediaStore.Files.getContentUri("external"), values);

        OutputStream outputStream = encryptionContext.getContentResolver().openOutputStream(uri);
        outputStream.write(keyBytes);
        outputStream.close();

        return publicKey;
    }

    /**
     * Helper method that retrieves the public key from AndroidKeystore.
     *
     * @return The public key
     * @throws Exception If retrieval of key fails
     */
    private PublicKey getKeystorePublicKey() throws Exception {
        KeyStore keyStore = KeyStore.getInstance(KEYSTORE);
        keyStore.load(null);

        return keyStore.getCertificate(PRIVATE_KEY_ALIAS).getPublicKey();
    }

    /**
     * Helper method that retrieves the private key from AndroidKeystore.
     *
     * @return The private key
     * @throws Exception If retrieval of key fails
     */
    private PrivateKey getPrivateKey() throws Exception {
        KeyStore keyStore = KeyStore.getInstance(KEYSTORE);
        keyStore.load(null);

        return (PrivateKey) keyStore.getKey(PRIVATE_KEY_ALIAS, null);
    }
}
