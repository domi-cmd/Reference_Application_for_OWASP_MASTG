# Vulnerabilities Implemented: 0 / 93

# Checklist

| Category | # | Description | Implementation-Status | MAS-Status |
|----------|----------|----------|----------|----------|
| STORAGE |  |  |  |  |
|  | [0001](https://mas.owasp.org/MASWE/MASVS-STORAGE/MASWE-0001/) | Insertion of Sensitive Data into Logs | ❌ | Exists |
|  | [0002](https://mas.owasp.org/MASWE/MASVS-STORAGE/MASWE-0002/) | Sensitive Data Stored With Insufficient Access Restrictions in Internal Locations |❌| Placeholder |
|  | [0003](https://mas.owasp.org/MASWE/MASVS-STORAGE/MASWE-0003/) | Backup Unencrypted |❌| Placeholder |
|  | [0004](https://mas.owasp.org/MASWE/MASVS-STORAGE/MASWE-0004/) | Sensitive Data Not Excluded From Backup |❌| Exists |
|  | [0006](https://mas.owasp.org/MASWE/MASVS-STORAGE/MASWE-0006/) | Sensitive Data Stored Unencrypted in Private Storage Locations |❌| Exists |
|  | [0007](https://mas.owasp.org/MASWE/MASVS-STORAGE/MASWE-0007/) | Sensitive Data Stored Unencrypted in Shared Storage Requiring No User Interaction |❌| Exists |
| CRYPTO |  |  |  |  |
|  | [0009](https://mas.owasp.org/MASWE/MASVS-CRYPTO/MASWE-0009/) | Improper Cryptographic Key Generation |❌| Exists |
|  | [0010](https://mas.owasp.org/MASWE/MASVS-CRYPTO/MASWE-0010/) | Improper Cryptographic Key Derivation |❌| Placeholder |
|  | [0011](https://mas.owasp.org/MASWE/MASVS-CRYPTO/MASWE-0011/) | Cryptographic Key Rotation Not Implemented |❌| Placeholder |
|  | [0012](https://mas.owasp.org/MASWE/MASVS-CRYPTO/MASWE-0012/) | Insecure or Wrong Usage of Cryptographic Key |❌| Placeholder |
|  | [0013](https://mas.owasp.org/MASWE/MASVS-CRYPTO/MASWE-0013/) | Hardcoded Cryptographic Keys in Use |❌| DEPRECATED |
|  | [0014](https://mas.owasp.org/MASWE/MASVS-CRYPTO/MASWE-0014/) | Cryptographic Keys Not Properly Protected at Rest |❌| Normal |
|  | [0015](https://mas.owasp.org/MASWE/MASVS-CRYPTO/MASWE-0015/) | Deprecated Android KeyStore Implementations |❌| Placeholder |
|  | [0016](https://mas.owasp.org/MASWE/MASVS-CRYPTO/MASWE-0016/) | Unsafe Handling of Imported Cryptographic Keys |❌| Placeholder |
|  | [0017](https://mas.owasp.org/MASWE/MASVS-CRYPTO/MASWE-0017/) | Cryptographic Keys Not Properly Protected on Export |❌| Placeholder |
|  | [0018](https://mas.owasp.org/MASWE/MASVS-CRYPTO/MASWE-0018/) | Cryptographic Keys Access Not Restricted |❌| Placeholder |
|  | [0019](https://mas.owasp.org/MASWE/MASVS-CRYPTO/MASWE-0019/) | Risky Cryptography Implementations |❌| Normal |
|  | [0020](https://mas.owasp.org/MASWE/MASVS-CRYPTO/MASWE-0020/) | Improper Encryption |❌| Beta |
|  | [0021](https://mas.owasp.org/MASWE/MASVS-CRYPTO/MASWE-0021/) | Improper Hashing |❌| Placeholder |
|  | [0022](https://mas.owasp.org/MASWE/MASVS-CRYPTO/MASWE-0022/) | Predictable Initialization Vectors (IVs) |❌| Placeholder |
|  | [0023](https://mas.owasp.org/MASWE/MASVS-CRYPTO/MASWE-0023/) | Risky Padding |❌| Beta |
|  | [0024](https://mas.owasp.org/MASWE/MASVS-CRYPTO/MASWE-0024/) | Improper Use of Message Authentication Code (MAC) |❌| Placeholder |
|  | [0025](https://mas.owasp.org/MASWE/MASVS-CRYPTO/MASWE-0025/) | Improper Generation of Cryptographic Signatures |❌| Placeholder |
|  | [0026](https://mas.owasp.org/MASWE/MASVS-CRYPTO/MASWE-0026/) | Improper Verification of Cryptographic Signature |❌| Placeholder |
|  | [0027](https://mas.owasp.org/MASWE/MASVS-CRYPTO/MASWE-0027/) | Improper Random Number Generation |❌| Beta |
| AUTH |  |  |  |  |
|  | [0005](https://mas.owasp.org/MASWE/MASVS-AUTH/MASWE-0005/) | API Keys Hardcoded in the App Package |❌| Beta |
|  | [0028](https://mas.owasp.org/MASWE/MASVS-AUTH/MASWE-0028/) | MFA Implementation Best Practices Not Followed |❌| Placeholder |
|  | [0029](https://mas.owasp.org/MASWE/MASVS-AUTH/MASWE-0029/) | Step-Up Authentication Not Implemented After Login |❌| Placeholder |
|  | [0030](https://mas.owasp.org/MASWE/MASVS-AUTH/MASWE-0030/) | Re-Authenticates Not Triggered On Contextual State Changes |❌| Placeholder |
|  | [0031](https://mas.owasp.org/MASWE/MASVS-AUTH/MASWE-0031/) | Insecure use of Android Protected Confirmation |❌| Placeholder |
|  | [0032](https://mas.owasp.org/MASWE/MASVS-AUTH/MASWE-0032/) | Platform-provided Authentication APIs Not Used |❌| Placeholder |
|  | [0033](https://mas.owasp.org/MASWE/MASVS-AUTH/MASWE-0033/) | Authentication or Authorization Protocol Security Best Practices Not Followed |❌| Placeholder |
|  | [0034](https://mas.owasp.org/MASWE/MASVS-AUTH/MASWE-0034/) | Insecure Implementation of Confirm Credentials |❌| DEPRECATED |
|  | [0035](https://mas.owasp.org/MASWE/MASVS-AUTH/MASWE-0035/) | Passwordless Authentication Not Implemented |❌| Placeholder |
|  | [0036](https://mas.owasp.org/MASWE/MASVS-AUTH/MASWE-0036/) | Authentication Material Stored Unencrypted on the Device |❌| Placeholder |
|  | [0037](https://mas.owasp.org/MASWE/MASVS-AUTH/MASWE-0037/) | Authentication Material Sent over Insecure Connections |❌| Placeholder |
|  | [0038](https://mas.owasp.org/MASWE/MASVS-AUTH/MASWE-0038/) | Authentication Tokens Not Validated |❌| Placeholder |
|  | [0039](https://mas.owasp.org/MASWE/MASVS-AUTH/MASWE-0039/) | Shared Web Credentials and Website-association Not Implemented |❌| Placeholder |
|  | [0040](https://mas.owasp.org/MASWE/MASVS-AUTH/MASWE-0040/) | Insecure Authentication in WebViews |❌| Placeholder |
|  | [0041](https://mas.owasp.org/MASWE/MASVS-AUTH/MASWE-0041/) | Authentication Enforced Only Locally Instead of on the Server-side |❌| Placeholder |
|  | [0042](https://mas.owasp.org/MASWE/MASVS-AUTH/MASWE-0042/) | Authorization Enforced Only Locally Instead of on the Server-side |❌| Placeholder |
|  | [0043](https://mas.owasp.org/MASWE/MASVS-AUTH/MASWE-0043/) | App Custom PIN Not Bound to Platform KeyStore |❌| Placeholder |
|  | [0044](https://mas.owasp.org/MASWE/MASVS-AUTH/MASWE-0044/) | Biometric Authentication Can Be Bypassed |❌| Placeholder |
|  | [0045](https://mas.owasp.org/MASWE/MASVS-AUTH/MASWE-0045/) | Fallback to Non-biometric Credentials Allowed for Sensitive Transactions |❌| Placeholder |
|  | [0046](https://mas.owasp.org/MASWE/MASVS-AUTH/MASWE-0046/) | Crypto Keys Not Invalidated on New Biometric Enrollment |❌| Placeholder |
| NETWORK |  |  |  |  |
|  | [0047](https://mas.owasp.org/MASWE/MASVS-NETWORK/MASWE-0047/) | Insecure Identity Pinning |❌| Beta |
|  | [0048](https://mas.owasp.org/MASWE/MASVS-NETWORK/MASWE-0048/) | Insecure Machine-to-Machine Communication |❌| Placeholder |
|  | [0049](https://mas.owasp.org/MASWE/MASVS-NETWORK/MASWE-0049/) | Proven Networking APIs Not used |❌| Beta |
|  | [0050](https://mas.owasp.org/MASWE/MASVS-NETWORK/MASWE-0050/) | Cleartext Traffic |❌| Beta |
|  | [0051](https://mas.owasp.org/MASWE/MASVS-NETWORK/MASWE-0051/) | Unprotected Open Ports |❌| Beta |
|  | [0052](https://mas.owasp.org/MASWE/MASVS-NETWORK/MASWE-0052/) | Insecure Certificate Validation |❌| Beta |
| PLATFORM |  |  |  |  |
|  | Number | Description |❌| Placeholder |
|  | Number | Description |❌| Placeholder |
|  | Number | Description |❌| Placeholder |
|  | Number | Description |❌| Placeholder |
|  | Number | Description |❌| Placeholder |
|  | Number | Description |❌| Placeholder |
|  | Number | Description |❌| Placeholder |
|  | Number | Description |❌| Placeholder |
| CODE |  |  |  |  |
|  | Number | Description |❌| Placeholder |
|  | Number | Description |❌| Placeholder |
|  | Number | Description |❌| Placeholder |
|  | Number | Description |❌| Placeholder |
|  | Number | Description |❌| Placeholder |
|  | Number | Description |❌| Placeholder |
|  | Number | Description |❌| Placeholder |
|  | Number | Description |❌| Placeholder |
| RESILIENCE |  |  |  |  |
|  | Number | Description |❌| Placeholder |
|  | Number | Description |❌| Placeholder |
|  | Number | Description |❌| Placeholder |
|  | Number | Description |❌| Placeholder |
|  | Number | Description |❌| Placeholder |
|  | Number | Description |❌| Placeholder |
|  | Number | Description |❌| Placeholder |
|  | Number | Description |❌| Placeholder |
| PRIVACY |  |  |  |  |
|  | Number | Description |❌| Placeholder |
|  | Number | Description |❌| Placeholder |
|  | Number | Description |❌| Placeholder |
|  | Number | Description |❌| Placeholder |
|  | Number | Description |❌| Placeholder |
|  | Number | Description |❌| Placeholder |
|  | Number | Description |❌| Placeholder |
|  | Number | Description |❌| Placeholder |
|  | Number | Description |❌| Normal |
|  | Number | Description |❌| Normal |
