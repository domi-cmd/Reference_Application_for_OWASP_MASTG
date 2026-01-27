# MASWE-0020: Improper Encryption

The relevant code for this vulnerability can be seen in [maswe_0020/EncryptionHandler.java](https://github.com/domi-cmd/Reference_Application_for_OWASP_MASTG/blob/main/apps/masvs_crypto/src/main/java/com/dkronig/masvs_crypto/maswe_0020/EncryptionHandler.java).

## The vulnerability consists of:

1. Using only Base64 encoding for encryption of user credentials in the lines here:
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


## The vulnerability can be exploited by:
1. Once the apk has been decompiled as described in my wiki [here](https://github.com/domi-cmd/Reference_Application_for_OWASP_MASTG/wiki/Decompile-apk-file), any attacker will see the risky cryptographic implementation used here.
2. Since the sensitive user data is stored in a plain shared preferences file, any attacker obtaining access to said file (via exported providers, rooting, etc.) can then easily read any sensitive user data encrypted by this class, once the Base64 encoding has been decoded.


## The vulnerability can be fixed by:
1. Implementing proper modern cryptography, such as AES with CBC.