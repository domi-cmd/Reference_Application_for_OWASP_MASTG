# MASWE-0025: Improper Generation of Cryptographic Signatures

The relevant code for this vulnerability can be seen in maswe_0025/EncryptionHandler.java.

## The vulnerability consists of:

1. Using SHA1withRSA for creating signatures in the lines here:
```java
public static String sign(String message) throws Exception {
    Signature s = Signature.getInstance("SHA1withRSA");
    s.initSign(getPrivateKey());
    s.update(message.getBytes("UTF-8"));
    byte[] sig = s.sign();
    return Base64.encodeToString(sig, Base64.NO_WRAP);
}
```
2. Using SHA1withRSA for verifying digital signatures in the lines here:
```java
public static boolean verify(BankCommand command) throws Exception {
    String payload = command.command + command.amountEuros + command.timestamp
            + command.nonce;
    Signature v = Signature.getInstance("SHA1withRSA");
    v.initVerify(getPublicKey());
    v.update(payload.getBytes("UTF-8"));
    return v.verify(Base64.decode(command.signature, Base64.NO_WRAP));
}
```

## The vulnerability can be exploited by:
TO BE ADDED
1. Removing any XYZ as follows:
```

```
