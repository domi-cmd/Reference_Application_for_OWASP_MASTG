# MASWE-0009: Improper Cryptographic Key Generation

The relevant code for this vulnerability can be seen in [maswe_0009/EncryptionHandler.java](https://github.com/domi-cmd/Reference_Application_for_OWASP_MASTG/blob/main/apps/masvs_crypto/src/main/java/com/dkronig/masvs_crypto/maswe_0009/EncryptionHandler.java).

## The vulnerability consists of:
All three modes of introduction for this vulnerability as described by OWASP. 
1. **Insufficient Entropy** - Implemented by using a very predictable seed and outdated PRNG, as seen in the lines here:
```java
// Very predictable seed for key generation, very low entropy
private static final String KEY_SEED = "01234567";
// SHA1PRNG is a very old pseudorandom number generator
private static final String ALGORITHM_PRNG = "SHA1PRNG";

private static SecretKey generateKey() throws Exception {
    byte[] keySeed = KEY_SEED.getBytes(StandardCharsets.UTF_8);

    SecureRandom random = SecureRandom.getInstance(ALGORITHM_PRNG);
    random.setSeed(keySeed);

    KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM_DES);
    keyGenerator.init(DES_KEY_SIZE, random);

    return keyGenerator.generateKey();
}
```
2. **Insufficient Key Length** - Implemented by using DES for encryption, which relies on a key with bit-length of only 56, instead of modern sizes such as 128, 192 or 256 bits, as seen here:
```java
// Use 56 bit DES key
private static final int DES_KEY_SIZE = 56;

private static SecretKey generateKey() throws Exception {
    // ... omitted ...

    KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM_DES);
    keyGenerator.init(DES_KEY_SIZE, random);

    return keyGenerator.generateKey();
}
```
3. **Using Risky or Broken Algorithms** - Implemented by using DES for encryption, which is heavily outdated and even considered broken by todays standards, seen in the lines here:
```java
private static final String ALGORITHM_DES = "DES";
private static final String CIPHER_TRANSFORMATION = "DES/ECB/PKCS5Padding";

private static SecretKey generateKey() throws Exception {
    // ... omitted ...

    KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM_DES);
    keyGenerator.init(DES_KEY_SIZE, random);

    return keyGenerator.generateKey();
}

public String encryptDataDES(String plaintext) throws Exception {
    Cipher cipher = Cipher.getInstance(CIPHER_TRANSFORMATION);
    cipher.init(Cipher.ENCRYPT_MODE, getKey());

    byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));
    return Base64.getEncoder().encodeToString(encryptedBytes);
}
```

## This vulnerability can be exploited by:
1. After decompiling the apk as described in my guide [here](https://github.com/domi-cmd/Reference_Application_for_OWASP_MASTG/wiki/Decompile-apk-file), any attacker will view the sourcecode and realize the incredibly weak encryption used here. DES is considered broken, and can be cracked by a bruteforce attack by any modern computer in a short timespan.



## The vulnerability can be fixed by:
1. Using a modern crypto algorithm, such as AES
2. Using a key of bit-length 256
3. Using a proper randomness source for the key generation
