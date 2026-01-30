# MASWE-0027: Improper Random Number Generation

The relevant code for this vulnerability can be seen in [maswe_0027/EncryptionHandler.java](https://github.com/domi-cmd/Reference_Application_for_OWASP_MASTG/blob/main/apps/masvs_crypto/src/main/java/com/dkronig/masvs_crypto/maswe_0027/EncryptionHandler.java).

## The vulnerability consists of:

1. Using java.util.Random API for generating source of randomness in the line here. This API uses a linear congruential formula, and is hence considered insecure.
```java
import java.util.Random;

private static String createIV(){
    byte[] iv = new byte[16];
    Random javaRandom = new Random(System.currentTimeMillis());
    javaRandom.nextBytes(iv);

    // ... omitted ...
}
```
2. Using the systems current time (non random source) to create "supposedly random" values in the same line here:
```java
private static String createIV(){
    byte[] iv = new byte[16];
    Random javaRandom = new Random(System.currentTimeMillis());
    javaRandom.nextBytes(iv);

    // ... omitted ...
}
```

## The vulnerability can be exploited by:
1. Any attacker can easily see the weak randomness used here once they decompiled the apk, as explained in 3 steps in my wiki [here](https://github.com/domi-cmd/Reference_Application_for_OWASP_MASTG/wiki/Decompile-apk-file).
2. Using linear congruential formula for random number generation, or LCG for short, is a topic that has been throughly researched and has many studies and other sources of documentation on why they are insecure.

## Interesting links and sources:
- [Blogpost where many papers are linked on why LCG is insecure and how to exploit it](https://crypto.stackexchange.com/questions/20495/how-brittle-are-lcg-cracking-techniques)