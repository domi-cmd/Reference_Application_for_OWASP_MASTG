# MASWE-0023: Risky Padding

The relevant code for this vulnerability can be seen in [maswe_0023/EncryptionHandler.java](https://github.com/domi-cmd/Reference_Application_for_OWASP_MASTG/blob/main/apps/maswe_crypto/src/main/java/com/dkronig/maswe_crypto/maswe_0023/EncryptionHandler.java).

## The vulnerability consists of:

1. Manually unpadding the PKCS7 ciphertext instead of using the PKCS7 cipher in decrypt_mode in the lines here:
```java
Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);
```
2. Making oracle attacks possible by leaking info about the ciphertext here:
```java
// Check for invalid padding
if (paddingLength < 1 || paddingLength > 16) {
    Log.e(TAG, "Invalid PKCS#7 padding length");
    return null;
}
```
3. Further improving the effectiveness of oracle attacks by leaking info about the ciphertext here:
```java
// Check if all padding bytes are equal to paddingLength
for (int i = decryptedBytesWithPadding.length - paddingLength;
     i < decryptedBytesWithPadding.length; i++) {
    // Padding bytes not consistent, resulting in oracle leaking info
    if ((decryptedBytesWithPadding[i] & 0xFF) != paddingLength) {
        Log.e(TAG, "Invalid PKCS#7 padding value");
        return null;
    }
}
```

## The vulnerability can be exploited by:
1. An attacker can first discover the vulnerability to oracle attacks by decompiling the apk, as outlined in my wiki [here](https://github.com/domi-cmd/Reference_Application_for_OWASP_MASTG/wiki/Decompile-apk-file).
2. Afterwards, they would need to gain access to the encrypted data.
3. Once they have the encrypted ciphertext, they can start tampering with it, and reconfigure the original plaintext out of the ciphertext, based on the error messages passed based on the tampered ciphertext.
