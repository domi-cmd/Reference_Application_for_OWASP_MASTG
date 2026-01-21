# MASWE-0014: Cryptographic Keys Not Properly Protected at Rest

The relevant code for this vulnerability can be seen in [maswe_0014/EncryptionHandler.java](https://github.com/domi-cmd/Reference_Application_for_OWASP_MASTG/blob/main/apps/maswe_crypto/src/main/java/com/dkronig/maswe_crypto/maswe_0014/EncryptionHandler.java).

## The vulnerability consists of:

1. Storing unencrypted secret key and initialization vector in shared preferences in the lines here:
```java
// Get access to the shared preferences of the calling activity
sharedPreferences = context.getApplicationContext()
        .getSharedPreferences("maswe_0014_secret_key", Context.MODE_PRIVATE);
SharedPreferences.Editor editor = sharedPreferences.edit();
// Make sure no duplicate or multiple keys are stored
editor.clear();
// Add key and initialization vector to shared preferences
editor.putString("encryption_key", encodedKey);
editor.putString("IV", encodedIV);
editor.apply();
```

## This vulnerability can be exploited by:
1. First decompiling the apk to get the sourcecode, as described in my wiki [here](https://github.com/domi-cmd/Reference_Application_for_OWASP_MASTG/wiki/Decompile-apk-file).
2. After this, any attacker can see that the key and the initialization vector are simply base64 encoded stored in the shared preferences file.
3. Once an attacker gets access to said xml file, they can easily base64 decode both key and IV, thus obtaining the raw AES key.
4. This allows them to decrypt anything that is stored within the context of this class.

## The vulnerability can be fixed by:
1. Using EncryptedSharedPreferences instead.
2. Generate and store the key in Android KeyStore instead of doing so manually.
