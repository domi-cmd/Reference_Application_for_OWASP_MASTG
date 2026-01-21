# MASWE-0015: Deprecated Android KeyStore Implementations

The relevant code for this vulnerability can be seen in maswe_0015/EncryptionHandler.java.

## The vulnerability consists of:

1. Using the deprecated KeyStore BouncyCastle (BKS) and the BouncyCastleProvider (BC) for storing and managing of the rsa key in the lines here:
```java
// Load BouncyCastle once
static {
    if (Security.getProvider("BC") == null) {
        Security.addProvider(new BouncyCastleProvider());
    }
}
```
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
1. The issue with BKS is that the keystore itself (the bks file in assets) is shipped with the apk, meaning the bks is extremely vulnerable because it is filebased
   and recoverable.
2. To exploit this, an attacker would decompile the apk, and quickly see the keystore implementation at play.
3. Next, they would unzip the apk, recovering the bks file and thus getting the keystore in doing so.
4. Since the key and keystore password are hardcoded and the keystore has been recovered, the attacker has access to the private key itself.
5. This grants them the ability to freely decrypt data, forge signatures, impersonate the banking service, ect.
6. This is of course a massive security threat.
