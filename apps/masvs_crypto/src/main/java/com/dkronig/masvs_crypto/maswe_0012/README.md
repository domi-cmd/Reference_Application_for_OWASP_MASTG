# MASWE-0012: Insecure or Wrong Usage of Cryptographic Key

The relevant code for this vulnerability can be seen in maswe_0012/EncryptionHandler.java.

## The vulnerability consists of:

1. Using the same cryptographic key for multiple purposes, them being encryption, decryption, as well as signing and verifying of messages, as seen in the lines here:
```java
private static void createAndStoreKey() throws Exception {
   KeyPairGenerator keyPairGenerator = KeyPairGenerator
            .getInstance(KeyProperties.KEY_ALGORITHM_RSA, KEYSTORE);

   KeyGenParameterSpec spec = new KeyGenParameterSpec.Builder(
            RSA_KEY_ALIAS,
            KeyProperties.PURPOSE_SIGN |
                  KeyProperties.PURPOSE_VERIFY |
                  KeyProperties.PURPOSE_ENCRYPT |
                  KeyProperties.PURPOSE_DECRYPT)
            .setDigests(KeyProperties.DIGEST_SHA1)
            .setSignaturePaddings(KeyProperties.SIGNATURE_PADDING_RSA_PKCS1)
            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_RSA_PKCS1)
            .setKeySize(2048)
            .build();

   keyPairGenerator.initialize(spec);
   keyPairGenerator.generateKeyPair();
}
```
2. Using a weak SHA1 key and SHA1withRSA for all of this. Especially using SHA1 for hashing of digital signatures is a great security liability, as seen in the lines here:
```java
private static final String SIGNING_ALGORITHM = "SHA1withRSA";

public static String sign(String message) throws Exception {
   Signature signature = Signature.getInstance(SIGNING_ALGORITHM);
   signature.initSign(getPrivateKey());
   signature.update(message.getBytes(StandardCharsets.UTF_8));

   byte[] signatureBytes = signature.sign();
   return Base64.encodeToString(signatureBytes, Base64.NO_WRAP);
}
```

## The vulnerability can be exploited by:
1. First decompiling the apk, which then grants knowledge to this security flaw
2. SHA1 is an old cryptographic algorithm, and has long been replaced by newer ones, such as SHA256. Android themselves calls SHA1 a "weak or broken cryptographic hash
   function" [1]. Since the hashes created here with SHA1 are unsalted, one can use huge lookup-tables available in the internet to crack the passwords (see exemplary source
   below [2]).
4. A huge part of this vulnerability consists of the rsa key being leaked. If this were the case, this would not only completely break the encryption, but also grant easy
   access to forging signatures. As of this commit, SHA1 is only considered weak, and allows for passwords being extracted if hashed unsalted, but is NOT broken in the sense
   of the rsa key being retrievable.
5. Another big security vulnerability that can be exploited here is the use of SHA1 for hashing the digital signature. Due to chosen-prefix-collision attacks, it is nowadays
   very easy for an attacker to produce two different messages with the same SHA1 hash, meaning the digital signature would be valid for both of them. Source on this is
   linked below [3].

## Interesting links and sources:
- [Android dev site itself discussing SHA1 as weak.](https://developer.android.com/privacy-and-security/risks/broken-cryptographic-algorithm)
- [Huge pre-computed lookup table for cracking passwords hashed unsalted with SHA1.](https://crackstation.net/)
- [Chosen-prefix-collision tool for SHA1](https://sha-mbles.github.io/)