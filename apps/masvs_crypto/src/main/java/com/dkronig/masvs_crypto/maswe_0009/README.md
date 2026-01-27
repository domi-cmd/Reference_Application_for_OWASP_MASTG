# MASWE-0009: Improper Cryptographic Key Generation

The relevant code for this vulnerability can be seen in [maswe_0009/EncryptionHandler.java](https://github.com/domi-cmd/Reference_Application_for_OWASP_MASTG/blob/main/apps/masvs_crypto/src/main/java/com/dkronig/masvs_crypto/maswe_0009/EncryptionHandler.java).

## The vulnerability consists of:
All three modes of introduction for this vulnerability as described by OWASP. 
1. **Insufficient Entropy** - Implemented by using a very predictable seed and outdated PRNG, as seen in the lines here:
```java
// Very predictable seed for key generation, very low entropy
byte[] keySeed = "01234567".getBytes(StandardCharsets.UTF_8);
// SHA1PRNG is a very old pseudorandom number generator
SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
random.setSeed(keySeed);
```
2. **Insufficient Key Length** - Implemented by using DES for encryption, which relies on a key with bit-length of only 56, instead of modern sizes such as 128, 192 or 256 bits, as seen here:
```java
// Use 56 bit DES key
keyGenerator.init(56, random);
secretKey = keyGenerator.generateKey();
```
3. **Using Risky or Broken Algorithms** - Implemented by using DES for encryption, which is heavily outdated and even considered broken by todays standards, seen in the lines here:
```java
// DES is considered broken
KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
```

## This vulnerability can be exploited by:
1. After decompiling the apk as described in my guide [here](https://github.com/domi-cmd/Reference_Application_for_OWASP_MASTG/wiki/Decompile-apk-file), any attacker will view the sourcecode and realize the incredibly weak encryption used here. DES is considered broken, and can be cracked by a bruteforce attack by any modern computer in a short timespan.



## The vulnerability can be fixed by:
1. Using a modern crypto algorithm, such as AES
2. Using a key of bit-length 256
3. Using a proper randomness source for the key generation
