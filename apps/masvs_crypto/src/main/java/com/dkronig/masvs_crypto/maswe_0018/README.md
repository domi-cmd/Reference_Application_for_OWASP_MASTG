# MASWE-0018: Cryptographic Keys Access Not Restricted

The relevant code for this vulnerability can be seen in maswe_0018/EncryptionHandler.java.

## The vulnerability consists of:

1. Setting various flags in a way that greatly undermines the keys access restrictions here:
```java
public static void generateKey() throws Exception {
    KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
    keyStore.load(null);

    // Key already exists, do nothing
    if (keyStore.containsAlias(RSA_KEY_ALIAS)) {
        return;
    }

    createAndStoreKeyPair();
}

private static void createAndStoreKeyPair() throws Exception{
    KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(
      KeyProperties.KEY_ALGORITHM_RSA, 
      KEYSTORE);

    KeyGenParameterSpec spec = new KeyGenParameterSpec.Builder(
            RSA_KEY_ALIAS,
                    KeyProperties.PURPOSE_ENCRYPT |
                    KeyProperties.PURPOSE_DECRYPT)
            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_RSA_PKCS1)
            .setKeySize(KEY_SIZE)
            .setUserAuthenticationRequired(false)
            .setUnlockedDeviceRequired(false)
            .setIsStrongBoxBacked(false)
            .setUserAuthenticationValidWhileOnBody(true)
            .build();

    keyPairGenerator.initialize(spec);
    keyPairGenerator.generateKeyPair();
}
```

## The vulnerability can be exploited by:
1. The combination of flags set here means that:
- the key remains usable even when the device is locked
- the key remains usable without PIN / password / biometrics
- the key is usable by background processes
- the key does not require secure hardware
- the key remains “authenticated” as long as the device is on-body   
  this is itself a huge vulnerability.
2. If an attacker would steal or otherwise access the locked device, the key would be fully usable by the attacker, even if the device remains locked.
  
### The approach below might not work due to Android sandboxing and per-app restrictions:
3. Any malicious app on the same device could simply run:
```java
// Attacker's app (no special permissions!)
KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
keyStore.load(null);
KeyStore.PrivateKeyEntry entry = (KeyStore.PrivateKeyEntry) keyStore.getEntry("maswe_0018_rsa_key", null);
PrivateKey stolenKey = entry.getPrivateKey();

// Now export or use it directly
```
  this allows for access to the rsa key, completely without rooting the device. This is possible because of the missing access controls of the key.
