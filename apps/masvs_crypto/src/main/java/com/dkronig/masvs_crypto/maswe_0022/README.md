# MASWE-0022: Predictable Initialization Vectors (IVs)

The relevant code for this vulnerability can be seen in [maswe_0022/EncryptionHandler.java](https://github.com/domi-cmd/Reference_Application_for_OWASP_MASTG/blob/main/apps/masvs_crypto/src/main/java/com/dkronig/masvs_crypto/maswe_0022/EncryptionHandler.java).

## The vulnerability consists of:

1. Using a hardcoded, static IV here:
```java
private static byte[] iv = {47, -98, 3, 120, 14, -55, 89, 6, -12, 33, 9, -44, 63, -1, 77, 22};
```
2. Reusing said IV for every en- and decryption, as seen here:
```java
private static final String CIPHER_TRANSFORMATION = "AES/CBC/PKCS5PADDING";

// Method for encrypting a string using strong AES with CBC
public String encryptData(String plaintext) throws Exception {
    // Generate ivSpec from iv
    IvParameterSpec ivSpec = new IvParameterSpec(iv);

    Cipher cipher = Cipher.getInstance(CIPHER_TRANSFORMATION);
    cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);
    
    byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));
    return Base64.encodeToString(encryptedBytes, Base64.DEFAULT);
}
```

## The vulnerability can be exploited by:
1. First, an attacker would decompile the apk by following the steps as described in my wiki [here](https://github.com/domi-cmd/Reference_Application_for_OWASP_MASTG/wiki/Decompile-apk-file). After this, any attacker will see the risky reuse of a hardcoded IV and the usage of AES in CBC mode, which can both be exploited.
2. Since AES in CBC mode is deterministic, using an hardcoded IV means that identical plaintext will result in identical ciphertext. This is a huge security vulnerability, and can be further exploited. There are many sources on this, such as: https://almightysec.com/hardcoded-crypto/
