# MASWE-0006: Sensitive Data Stored Unencrypted in Private Storage Locations

The relevant code for this vulnerability can be seen in [maswe_0006/EncryptionHandler.java](https://github.com/domi-cmd/Reference_Application_for_OWASP_MASTG/blob/main/apps/maswe_storage/src/main/java/com/dkronig/maswe_storage/maswe_0006/EncryptionHandler.java).

## The vulnerability consists of:
1. Utilizing a hardcoded encryption key in the lines [here](https://github.com/domi-cmd/Reference_Application_for_OWASP_MASTG/blob/main/apps/maswe_storage/src/main/java/com/dkronig/maswe_storage/maswe_0006/EncryptionHandler.java#L11):

```java
private static final String ENCRYPTION_KEY = "EncryptK";
```

2. Specifying the encryption algorithm to be DES, which is considered outdated and even broken, in the line [here](https://github.com/domi-cmd/Reference_Application_for_OWASP_MASTG/blob/main/apps/maswe_storage/src/main/java/com/dkronig/maswe_storage/maswe_0006/EncryptionHandler.java#L12C5-L12C68):

```java
private static final String ALGORITHM = "DES/ECB/PKCS5Padding";
```

3. Using the hardcoded key and broken algorithm to handle the encryption and decryption of sensitive user data in the lines [here](https://github.com/domi-cmd/Reference_Application_for_OWASP_MASTG/blob/main/apps/maswe_storage/src/main/java/com/dkronig/maswe_storage/maswe_0006/EncryptionHandler.java#L15-L20):

```java
SecretKeySpec keySpec = new SecretKeySpec(ENCRYPTION_KEY.getBytes(StandardCharsets.UTF_8), ALGORITHM);
Cipher cipher = Cipher.getInstance(ALGORITHM);
cipher.init(Cipher.ENCRYPT_MODE, keySpec);
byte[] encryptedBytes = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
return Base64.getEncoder().encodeToString(encryptedBytes);
```

## The vulnerability can be fixed by:
1. Switching to a good encryption algorithm, such as AES, instead of DES:
```java
private static final String ALGORITHM = "AES/GCM/NoPadding";
private static final int AES_KEY_SIZE = 256;
```
2. Implementing proper key derivation instead of a hardcoded key:

```java
private final SecretKey secretKey;

public EncryptionHandler() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(AES_KEY_SIZE);
        this.secretKey = keyGen.generateKey();
}
```
