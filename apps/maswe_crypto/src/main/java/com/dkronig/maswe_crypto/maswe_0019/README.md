# MASWE-0019: Risky Cryptography Implementations

The relevant code for this vulnerability can be seen in [maswe_0019/EncryptionHandler.java](https://github.com/domi-cmd/Reference_Application_for_OWASP_MASTG/blob/maswe_0019/apps/maswe_crypto/src/main/java/com/dkronig/maswe_crypto/maswe_0019/EncryptionHandler.java).

## The vulnerability consists of:

1. Use of low-level mathematical operations - By using circular bitshifting for en- and decryption in the lines here:
```java
// Method for encrypting a string by using circular bit shifting
    public String encryptData(String plaintext) throws Exception {
        byte[] textBytes = plaintext.getBytes();

        for(int i = 0; i < textBytes.length; i++){
            // & 0xFF is to make sure the byte is unsigned
            // Only keep the first 8 bits
            // Use xor to add the 2 bytes lost on the left back on to the right end
            textBytes[i] = (byte)((textBytes[i] << 2) | ((textBytes[i] & 0xFF) >>> (6)));
        }

        return Base64.encodeToString(textBytes, Base64.DEFAULT);
    }
```

## The vulnerability can be exploited by:
1. Once the apk has been decompiled as described in my wiki [here](https://github.com/domi-cmd/Reference_Application_for_OWASP_MASTG/wiki/Decompile-apk-file), any attacker will see the risky cryptographic implementation used here.
2. The sensitive user data is protected by being stored in encrypted shared preferences, but this is only true at rest. By using Frida or similar dynamic instrumentation toolkits, this data can be accessed without having to decrypt the encrypted shared preferences, and then easily read once the bitshift encryption has been reversed.
