# MASWE-0014: Cryptographic Keys Not Properly Protected at Rest

The relevant code for this vulnerability can be seen in [maswe_0014/EncryptionHandler.java](https://github.com/domi-cmd/Reference_Application_for_OWASP_MASTG/blob/main/apps/maswe_crypto/src/main/java/com/dkronig/maswe_crypto/maswe_0014/EncryptionHandler.java).

## The vulnerability consists of:

1. Storing unencrypted secret key and initialization vector in shared preferences in the lines [here](https://github.com/domi-cmd/Reference_Application_for_OWASP_MASTG/blob/main/apps/maswe_crypto/src/main/java/com/dkronig/maswe_crypto/maswe_0014/EncryptionHandler.java#L36-L45):
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


## The vulnerability can be fixed by:
1. Using EncryptedSharedPreferences instead.
2. Generate and store the key in Android KeyStore instead of doing so manually.
