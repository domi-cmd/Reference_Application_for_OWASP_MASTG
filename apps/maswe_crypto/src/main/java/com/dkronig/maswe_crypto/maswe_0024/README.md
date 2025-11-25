# MASWE-0024: Improper Use of Message Authentication Code (MAC)

The relevant code for this vulnerability can be seen in [IntegrityVerifier.java](https://github.com/domi-cmd/Reference_Application_for_OWASP_MASTG/blob/main/apps/maswe_crypto/src/main/java/com/dkronig/maswe_crypto/maswe_0024/IntegrityVerifier.java), 
[BankAccountManagerService.java](https://github.com/domi-cmd/Reference_Application_for_OWASP_MASTG/blob/main/apps/maswe_crypto/src/main/java/com/dkronig/maswe_crypto/maswe_0024/BankAccountManagerService.java) 
and [ProfileActivity.java](https://github.com/domi-cmd/Reference_Application_for_OWASP_MASTG/new/main/apps/maswe_crypto/src/main/java/com/dkronig/maswe_crypto/maswe_0024).

## The vulnerability consists of:

1. Creating manual, non-cryptographic checksums in the lines here:
```java
public static long crc32(String data) {
      java.util.zip.CRC32 crc = new java.util.zip.CRC32();
      crc.update(data.getBytes(StandardCharsets.UTF_8));
      return crc.getValue();               // ← 32-bit non-crypto checksum
}
```
2. Using said checksum for verifying integrity of sensitive banking information here:
```java
// Use crc32 as a checksum
String payload = bankCommand.command + bankCommand.amountEuros
        + bankCommand.timestamp + bankCommand.nonce;
bankCommand.hmac = String.valueOf(IntegrityVerifier.crc32(payload));
```
3. And then also using it for verifying integrity of received data in the bankaccount manager service here:
```java
private boolean verifyChecksum(BankCommand cmd) {
String payload = cmd.command + cmd.amountEuros + cmd.timestamp + cmd.nonce;
long expected = IntegrityVerifier.crc32(payload);
try {
    // Need to parse the stored crc32 string to long
    long received = Long.parseLong(cmd.hmac);
    return expected == received;
} catch (Exception e) {
    return false;
}
}
```

## The vulnerability can be exploited by:
1. First decompiling the apk, which allows for anybody to see plainly that a bad non-cryptographic checksum is being used.
2. 32 bit crc has limited possible values, and is not designed to be safe. Modern computers can easily force collisions as to figure out the checksum, and thus forge
arbitrary messages to the banking service, without it realizing that the integrity of the commands has been destroyed
