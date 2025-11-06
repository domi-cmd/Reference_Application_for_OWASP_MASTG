# MASWE-0020: Improper Encryption

The relevant code for this vulnerability can be seen in [maswe_0020/EncryptionHandler.java](https://github.com/domi-cmd/Reference_Application_for_OWASP_MASTG/blob/main/apps/maswe_crypto/src/main/java/com/dkronig/maswe_crypto/maswe_0020/EncryptionHandler.java).

## The vulnerability consists of:

1. Using only Base64 encoding for encryption of user credentials in the lines [here](https://github.com/domi-cmd/Reference_Application_for_OWASP_MASTG/blob/main/apps/maswe_crypto/src/main/java/com/dkronig/maswe_crypto/maswe_0020/EncryptionHandler.java#L7-L15):
```java
// Method for encrypting a string by using Base64 encoding
public String encryptData(String plaintext) throws Exception {
    return Base64.encodeToString(plaintext.getBytes(), Base64.DEFAULT);
}

// Method for decrypting a string by using Base64 decoding
public String decryptData(String encrypted) throws Exception {
    return new String(Base64.decode(encrypted, Base64.DEFAULT));
}
```



## The vulnerability can be fixed by:
1. Implementing proper modern cryptography, such as AES with CBC.
