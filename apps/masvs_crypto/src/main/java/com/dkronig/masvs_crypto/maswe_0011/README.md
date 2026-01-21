# MASWE-0011: Cryptographic Key Rotation Not Implemented

The relevant code for this vulnerability can be seen in maswe_0011/EncryptionHandler.java.

## The vulnerability consists of:
OWASP MASWE describes this vulnerability as using a long-lived key, typically an asymmetric one, without implementation of key rotation. If the key is compromised, 
this leads to huge problems.
1. Using a long lived asymmetric key (RSA) for both encryption and decryption, without any key rotation:
```java
public String encryptData(String plaintext) throws Exception {
    Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding", "BC");
    cipher.init(Cipher.ENCRYPT_MODE, getPublicKey());
    byte[] encrypted = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));
    return Base64.encodeToString(encrypted, Base64.NO_WRAP);
}
```
2. Using BKS as a keystore, which is deprecated and compromises the passwords secrecy in the lines here:
```java
public static void loadBksKeystore(Context context) throws Exception {
    // If keystore has already been loaded, do nothing and return
    if(keyStore != null){
        return;
    }

    keyStore = KeyStore.getInstance("BKS", "BC");

    try(InputStream inputStream = context.getAssets().open(KEYSTORE_FILE)) {
        keyStore.load(inputStream, KEYSTORE_PASSWORD.toCharArray());
    }
}
```
## The vulnerability can be exploited by:
1. After decompiling the apk, the attacker will spot the deprecated keystore implementation, as well as the lack of key rotation implemented.
2. After unzipping the bks file which is stored within the apk, and taking notice of the hardcoded key alias, the attacker is in posession of the secret key.
3. Since there is no key rotation implemented, this allows for the attacker to decrypt sensitive data indefinitely.