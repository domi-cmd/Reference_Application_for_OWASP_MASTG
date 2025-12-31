# MASWE-0006: Sensitive Data Stored Unencrypted in Private Storage Locations

The relevant code for this vulnerability can be seen in [maswe_0006/EncryptionHandler.java](https://github.com/domi-cmd/Reference_Application_for_OWASP_MASTG/blob/main/apps/maswe_storage/src/main/java/com/dkronig/maswe_storage/maswe_0006/EncryptionHandler.java).

## The vulnerability consists of:
1. Utilizing a hardcoded encryption key in the lines here:

```java
private static final String ENCRYPTION_KEY = "EncryptK";
```

2. Specifying the encryption algorithm to be DES, which is considered outdated and even broken, in the line here:

```java
private static final String ALGORITHM = "DES/ECB/PKCS5Padding";
```

3. Using the hardcoded key and broken algorithm to handle the encryption and decryption of sensitive user data in the lines here:

```java
SecretKeySpec keySpec = new SecretKeySpec(ENCRYPTION_KEY.getBytes(StandardCharsets.UTF_8), ALGORITHM);
Cipher cipher = Cipher.getInstance(ALGORITHM);
cipher.init(Cipher.ENCRYPT_MODE, keySpec);
byte[] encryptedBytes = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
return Base64.getEncoder().encodeToString(encryptedBytes);
```

## The vulnerability can be exploited by:
1. Since the secret key is hardcoded, anyone with the apk can decompile it as to view the key unencrypted in the source code. A method for doing so is documented in this repositories wiki [here](https://github.com/domi-cmd/Reference_Application_for_OWASP_MASTG/wiki/Decompile-apk-file). The secret key is then viewable in its unencrypted form for anybody to see:
<img width="1749" height="780" alt="image" src="https://github.com/user-attachments/assets/034c9279-62ed-4002-b069-4dcbe8353403" />
With the secret key obtained and since DES, which is considered completely broken, is used for encryption in ECB mode, meaning there is no IV (initialization vector), any modern laptop can break this encryption implementation with little effort.


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
