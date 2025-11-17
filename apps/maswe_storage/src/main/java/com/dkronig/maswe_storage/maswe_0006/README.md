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

## The vulnerability can be exploited by:
1. Since the secret key is hardcoded, anyone with the apk can decompile it as to view the key unencrypted in the source code. One way to do so is to first download jadx-gui to decompile the apk and view the sourcecode visually. Download a stable version of jadx-gui from here
```
https://github.com/skylot/jadx/releases/tag/v1.5.3
```

2. Get the .apk filepath of the installed app by using
```shell
C:\Users\Domi>adb shell pm path com.dkronig.maswe_storage
package:/data/app/~~JwJM_JZXJ90cqDgrGV4mwA==/com.dkronig.maswe_storage-vn02Az_nSTFj-sH8nXvJaA==/base.apk
```

3. Pull the apk using the returned path:
```shell
C:\Users\Domi>adb pull /data/app/~~JwJM_JZXJ90cqDgrGV4mwA==/com.dkronig.maswe_storage-vn02Az_nSTFj-sH8nXvJaA==/base.apk my_app.apk
/data/app/~~JwJM_JZXJ90cqDgrGV4mwA==/com.dkronig.maswe_sto...e pulled, 0 skipped. 117.1 MB/s (23268200 bytes in 0.190s)
```

4. You can then decompile the apk by running:
```shell
C:\Program Files\jadx_gui>jadx-gui-1.5.3.exe "C:\Users\Domi\my_app.apk"
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
