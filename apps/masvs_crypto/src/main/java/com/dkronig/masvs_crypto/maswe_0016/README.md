# MASWE-0016: Unsafe Handling of Imported Cryptographic Keys

The relevant code for this vulnerability can be seen in maswe_0016/EncryptionHandler.java

## The vulnerability consists of:

1. When importing the public key for data encryption, there are no checks in place for checking the keys integrity, neither in the calling method here:
```java
private PublicKey getImportedOrCreatePublicKey() throws Exception {
    // Check if there is a public key already stored in shared (untrusted) storage
    Uri existing = getPublicKeyUri();
    if (existing != null) {
        // If a key is found in mediastore, it is imported and used without any security checks
        return importKey(existing);
    }

    // If no key is found, a new one is generated and exported to untrusted storage
    return generateAndStorePublicKey();
}
```
2. Nor in the method doing the importing of the public key here:
```java
private static PublicKey importKey(Uri keyUri) throws Exception {
    InputStream inputStream = encryptionContext.getContentResolver().openInputStream(keyUri);
    byte[] keyBytes = readAllBytes(inputStream);

    // This is unsafe, as we do no signature/identity/integrity checks
    X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
    return KeyFactory.getInstance("RSA").generatePublic(spec);
}
```

## The vulnerability can be exploited by:
1. Since there is no sanitization of the imported public key in place, any attacker can replace the public key with a public key of their own. The sensitive user data
   encrypted with this malicious public key can then be decrypted with their own private key, matching the public key (assuming the attacker gets hold of the user data).
   Since the user data here is stored in an encrypted state in Shared Preferences, rooting the device or otherwise compromising it will lead to the attacker being able to
   read the encrypted user data, making this attack very feasible.
2. The attacker can intentionally create crashes and app failures by disrupting the login process through inserting malicious public keys, not matching the private key as
   expected by the system.
