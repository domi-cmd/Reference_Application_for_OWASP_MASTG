# MASWE-0010: Improper Cryptographic Key Derivation

The relevant code for this vulnerability can be seen in maswe_0010/EncryptionHandler.java.

## The vulnerability consists of:

1. Using PBKDF2 for deriving the AES key, but using insufficient salt (16 zero-bytes), a hardcoded, readable string as source for PBKDF2 derivation, and using only 10
   PBKDF2 iterations for deriving the AES key, which is way too small and easily brute-forceable: 
```java
private static final int ENCRYPTION_ITERATIONS = 10;
private static final int KEY_LENGTH = 128;
private static final byte[] SALT = new byte[16];
private static final int IV_LENGTH = 16;
private static SecretKey secretKey;
private static final String PASSWORD_FOR_KEY_DERIVATION = "password";


public static void setupEncryption() throws Exception {
    PBEKeySpec secretKeySpec = new PBEKeySpec(PASSWORD_FOR_KEY_DERIVATION.toCharArray(), SALT,
            ENCRYPTION_ITERATIONS, KEY_LENGTH);
    SecretKeyFactory secretKeyFactory = SecretKeyFactory
            .getInstance("PBKDF2WithHmacSHA256");
    byte[] keyBytes = secretKeyFactory.generateSecret(secretKeySpec).getEncoded();

    // Generate the secret key
    secretKey = new SecretKeySpec(keyBytes, "AES");
}
```

## The vulnerability can be exploited by:
- After decompiling the apk, any attacker will see the bad cryptographic key derivation used here. 
- Since the source string, the salt and the amount of iterations is plainly readable, anybody can then easily recreate the 
key derivation with the same parameters, thus recreating the secret AES key themselves. 
- This grants any attacker the ability to read all encrypted user data at free will, granted that they get hold of the encrypted user data, which is stored as such in Shared Preferences.