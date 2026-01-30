# MASWE-0017: Cryptographic Keys Not Properly Protected on Export

The relevant code for this vulnerability can be seen in maswe_0017/EncryptionHandler.java.

## The vulnerability consists of:

1. Exporting the key in its raw DER format form, with no wrapping of the key in another key, no signing of the key, and no access control to who can read it in MediaStore:
```java
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

    OutputStream outputStream = encryptionContext.getContentResolver().openOutputStream(uri);
    // Export the public key without wrapping it in another key, nor signing it
    outputStream.write(keyBytes);
    outputStream.close();

    return publicKey;
}
```

## The vulnerability can be exploited by:
1. By first decompiling the app, then finding the key in the MediaStore, an attacker can learn sensitive information about the encryption and decryption process of the app:
- What cryptographic algorithm is used by the app.
- The size of the key used.
- Whether the encryption is modern and valid, or weak or maybe even considered broken.
- The RSA modulus and the exponent
- Potential random structure bias
- How to potentially attack the encryption of the app.
  In short, while some of these things can be learned by decompiling the apk and looking at the sourcecode, it is far more dangerous to export the real, at
  runtime-generated key.
  
In this app, the encryption used contains:
```java
Cipher.getInstance("RSA/ECB/PKCS1Padding")
```
Which is considered weak and deprecated. An attacker can learn this by reading the raw exported key. 
