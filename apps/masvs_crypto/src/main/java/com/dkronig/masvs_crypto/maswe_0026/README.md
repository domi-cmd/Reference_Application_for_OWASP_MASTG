# MASWE-0026: Improper Verification of Cryptographic Signature

The relevant code for this vulnerability can be seen in maswe_0026/EncryptionHandler.java.

## The vulnerability consists of:

1. Not properly verifying the passed digital signature and instead returning true in the lines here:
```java
public static boolean verify(BankCommand command) {
      return true;
}
```
2. Additionally to the ignored passed signature, the verifying method also fails to make use of the passed timestamp and nonce of the message, which are part of every sent BankCommand:
```java
private void sendCommand(String command, int amountEuros) throws Exception {
    BankCommand bankCommand = new BankCommand();
    bankCommand.command = command;
    bankCommand.amountEuros = amountEuros;
    bankCommand.timestamp = System.currentTimeMillis();
    bankCommand.nonce = UUID.randomUUID().toString();

    String payload = bankCommand.command + bankCommand.amountEuros
            + bankCommand.timestamp + bankCommand.nonce;
    bankCommand.signature = EncryptionHandler.sign(payload);

    Intent intent = new Intent(this, BankAccountManagerService.class);
    intent.putExtra("command", new Gson().toJson(bankCommand));
    startService(intent);
}
```

## The vulnerability can be exploited by:
1. By reading the sourcecode after decompiling the apps apk, any attacker will see that the signature passed does not matter. Hence any forged signature will be verified
   by the service managing the bank account.
2. Additionally, since the timestamp and nonce too are not part of the verification process, any attacker can easily send forged commands to the service managing the
   users bank account.
3. Since the service is exported, as the attacker will see when inspecting the decompiled AndroidManifest, they can easily send fake commands to the service, potentially
   withdrawing currency from the account. This represents a huge security liability.
