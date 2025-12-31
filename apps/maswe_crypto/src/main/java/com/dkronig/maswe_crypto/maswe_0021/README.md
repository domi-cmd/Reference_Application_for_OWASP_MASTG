# MASWE-0021: Improper Hashing

The relevant code for this vulnerability can be seen in [maswe_0021/EncryptionHandler.java](https://github.com/domi-cmd/Reference_Application_for_OWASP_MASTG/blob/main/apps/maswe_crypto/src/main/java/com/dkronig/maswe_crypto/maswe_0021/EncryptionHandler.java).

## The vulnerability consists of:

1. Using the broken algorithm SHA-1 for hashing of user credentials in the lines here:
```java
 public String hashData(String plaintext) throws Exception {
    // Get the algorithm used for hashing (SHA-1)
    MessageDigest digestAlgorithm = MessageDigest.getInstance("SHA-1");

    // Calculate the message digest of the plaintext String
    byte[] messageDigest = digestAlgorithm.digest(plaintext.getBytes(StandardCharsets.UTF_8));

    // Convert it to signum representation
    BigInteger no = new BigInteger(1, messageDigest);

    // Convert it to hex value
    StringBuilder hashText = new StringBuilder(no.toString(16));

    // Add preceding 0s to make it 40 digits long
    while (hashText.length() < 40) {
        hashText.insert(0, "0");
    }

    // return the HashText
    return hashText.toString();
}
```

## The vulnerability can be fixed by:
1. Replacing SHA-1 with a more modern hashing algorithm, such as SHA-256.

## Interesting Links and Sources
- [The SHAttered project](https://shattered.io/)  
- [HashClash GitHub Repository](https://github.com/cr-marcstevens/hashclash)  
- [Reddit Discussion on SHA-1 broken](https://www.reddit.com/r/linux/comments/eqy1kh/sha1_is_now_fully_broken/)