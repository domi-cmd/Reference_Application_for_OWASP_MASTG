# Vulnerabilities Implemented: 1 / X

# Checklist

| Category | # | Description | Implementation-Status | MAS-Status |
|----------|----------|----------|----------|----------|
| STORAGE |  |  |  |  |
|  | [0001](https://mas.owasp.org/MASWE/MASVS-STORAGE/MASWE-0001/) | Insertion of Sensitive Data into Logs | ✅ | Beta |
|  | [0002](https://mas.owasp.org/MASWE/MASVS-STORAGE/MASWE-0002/) | Sensitive Data Stored With Insufficient Access Restrictions in Internal Locations |❌| Placeholder |
|  | [0003](https://mas.owasp.org/MASWE/MASVS-STORAGE/MASWE-0003/) | Backup Unencrypted |❌| Placeholder |
|  | [0004](https://mas.owasp.org/MASWE/MASVS-STORAGE/MASWE-0004/) | Sensitive Data Not Excluded From Backup |❌| Beta |
|  | [0006](https://mas.owasp.org/MASWE/MASVS-STORAGE/MASWE-0006/) | Sensitive Data Stored Unencrypted in Private Storage Locations |❌| Beta |
|  | [0007](https://mas.owasp.org/MASWE/MASVS-STORAGE/MASWE-0007/) | Sensitive Data Stored Unencrypted in Shared Storage Requiring No User Interaction |❌| Beta |
| CRYPTO |  |  |  |  |
|  | [0009](https://mas.owasp.org/MASWE/MASVS-CRYPTO/MASWE-0009/) | Improper Cryptographic Key Generation |❌| Beta |
|  | [0010](https://mas.owasp.org/MASWE/MASVS-CRYPTO/MASWE-0010/) | Improper Cryptographic Key Derivation |❌| Placeholder |
|  | [0011](https://mas.owasp.org/MASWE/MASVS-CRYPTO/MASWE-0011/) | Cryptographic Key Rotation Not Implemented |❌| Placeholder |
|  | [0012](https://mas.owasp.org/MASWE/MASVS-CRYPTO/MASWE-0012/) | Insecure or Wrong Usage of Cryptographic Key |❌| Placeholder |
|  | [0013](https://mas.owasp.org/MASWE/MASVS-CRYPTO/MASWE-0013/) | Hardcoded Cryptographic Keys in Use |❌| DEPRECATED |
|  | [0014](https://mas.owasp.org/MASWE/MASVS-CRYPTO/MASWE-0014/) | Cryptographic Keys Not Properly Protected at Rest |❌| Beta |
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
|  | [0053](https://mas.owasp.org/MASWE/MASVS-PLATFORM/MASWE-0053/) | Sensitive Data Leaked via the User Interface |❌| Placeholder |
|  | [0054](https://mas.owasp.org/MASWE/MASVS-PLATFORM/MASWE-0054/) | Sensitive Data Leaked via Notifications |❌| Placeholder |
|  | [0055](https://mas.owasp.org/MASWE/MASVS-PLATFORM/MASWE-0055/) | Sensitive Data Leaked via Screenshots |❌| Placeholder |
|  | [0056](https://mas.owasp.org/MASWE/MASVS-PLATFORM/MASWE-0056/) | Tapjacking Attacks |❌| Placeholder |
|  | [0057](https://mas.owasp.org/MASWE/MASVS-PLATFORM/MASWE-0057/) | StrandHogg Attack / Task Affinity Vulnerability |❌| Placeholder |
|  | [0058](https://mas.owasp.org/MASWE/MASVS-PLATFORM/MASWE-0058/) | Insecure Deep Links |❌| Placeholder |
|  | [0059](https://mas.owasp.org/MASWE/MASVS-PLATFORM/MASWE-0059/) | Use Of Unauthenticated Platform IPC |❌| Placeholder |
|  | [0060](https://mas.owasp.org/MASWE/MASVS-PLATFORM/MASWE-0060/) | Insecure Use of UIActivity |❌| Placeholder |
|  | [0061](https://mas.owasp.org/MASWE/MASVS-PLATFORM/MASWE-0061/) | Insecure Use of App Extensions |❌| Placeholder |
|  | [0062](https://mas.owasp.org/MASWE/MASVS-PLATFORM/MASWE-0062/) | Insecure Services |❌| Placeholder |
|  | [0063](https://mas.owasp.org/MASWE/MASVS-PLATFORM/MASWE-0063/) | Insecure Broadcast Receivers |❌| Placeholder |
|  | [0064](https://mas.owasp.org/MASWE/MASVS-PLATFORM/MASWE-0064/) | Insecure Content Providers |❌| Placeholder |
|  | [0065](https://mas.owasp.org/MASWE/MASVS-PLATFORM/MASWE-0065/) | Sensitive Data Permanently Shared with Other Apps |❌| Placeholder |
|  | [0066](https://mas.owasp.org/MASWE/MASVS-PLATFORM/MASWE-0066/) | Insecure Intents |❌| Placeholder |
|  | [0067](https://mas.owasp.org/MASWE/MASVS-PLATFORM/MASWE-0067/) | Debuggable Flag Not Disabled |❌| Beta |
|  | [0068](https://mas.owasp.org/MASWE/MASVS-PLATFORM/MASWE-0068/) | JavaScript Bridges in WebViews |❌| Placeholder |
|  | [0069](https://mas.owasp.org/MASWE/MASVS-PLATFORM/MASWE-0069/) | WebViews Allows Access to Local Resources |❌| Placeholder |
|  | [0070](https://mas.owasp.org/MASWE/MASVS-PLATFORM/MASWE-0070/) | JavaScript Loaded from Untrusted Sources |❌| Placeholder |
|  | [0071](https://mas.owasp.org/MASWE/MASVS-PLATFORM/MASWE-0071/) | WebViews Loading Content from Untrusted Sources |❌| Placeholder |
|  | [0072](https://mas.owasp.org/MASWE/MASVS-PLATFORM/MASWE-0072/) | Universal XSS |❌| Placeholder |
|  | [0073](https://mas.owasp.org/MASWE/MASVS-PLATFORM/MASWE-0073/) | Insecure WebResourceResponse Implementations |❌| Placeholder |
|  | [0074](https://mas.owasp.org/MASWE/MASVS-PLATFORM/MASWE-0074/) | Web Content Debugging Enabled |❌| Placeholder |
| CODE |  |  |  |  |
|  | [0075](https://mas.owasp.org/MASWE/MASVS-CODE/MASWE-0075/) | Enforced Updating Not Implemented |❌| Placeholder |
|  | [0076](https://mas.owasp.org/MASWE/MASVS-CODE/MASWE-0076/) | Dependencies with Known Vulnerabilities |❌| Beta |
|  | [0077](https://mas.owasp.org/MASWE/MASVS-CODE/MASWE-0077/) | Running on a recent Platform Version Not Ensured |❌| Placeholder |
|  | [0078](https://mas.owasp.org/MASWE/MASVS-CODE/MASWE-0078/) | Latest Platform Version Not Targeted |❌| Placeholder |
|  | [0079](https://mas.owasp.org/MASWE/MASVS-CODE/MASWE-0079/) | Unsafe Handling of Data from the Network |❌| Placeholder |
|  | [0080](https://mas.owasp.org/MASWE/MASVS-CODE/MASWE-0080/) | Unsafe Handling of Data from Backups |❌| Placeholder |
|  | [0081](https://mas.owasp.org/MASWE/MASVS-CODE/MASWE-0081/) | Unsafe Handling Of Data From External Interfaces |❌| Placeholder |
|  | [0082](https://mas.owasp.org/MASWE/MASVS-CODE/MASWE-0082/) | Unsafe Handling of Data From Local Storage |❌| Placeholder |
|  | [0083](https://mas.owasp.org/MASWE/MASVS-CODE/MASWE-0083/) | Unsafe Handling of Data From The User Interface |❌| Placeholder |
|  | [0084](https://mas.owasp.org/MASWE/MASVS-CODE/MASWE-0084/) | Unsafe Handling of Data from IPC |❌| Placeholder |
|  | [0085](https://mas.owasp.org/MASWE/MASVS-CODE/MASWE-0085/) | Unsafe Dynamic Code Loading |❌| Placeholder |
|  | [0086](https://mas.owasp.org/MASWE/MASVS-CODE/MASWE-0086/) | SQL Injection |❌| Placeholder |
|  | [0087](https://mas.owasp.org/MASWE/MASVS-CODE/MASWE-0087/) | Insecure Parsing and Escaping |❌| Placeholder |
|  | [0088](https://mas.owasp.org/MASWE/MASVS-CODE/MASWE-0088/) | Insecure Object Deserialization |❌| Placeholder |
|  | [0116](https://mas.owasp.org/MASWE/MASVS-CODE/MASWE-0116/) | Compiler Provided Security Features Not Used |❌| Placeholder |
| RESILIENCE |  |  |  |  |
|  | [0008](https://mas.owasp.org/MASWE/MASVS-RESILIENCE/MASWE-0008/) | Missing Device Secure Lock Verification Implementation |❌| Placeholder |
|  | [0089](https://mas.owasp.org/MASWE/MASVS-RESILIENCE/MASWE-0089/) | Code Obfuscation Not Implemented |❌| Placeholder |
|  | [0090](https://mas.owasp.org/MASWE/MASVS-RESILIENCE/MASWE-0090/) | Resource Obfuscation Not Implemented |❌| Placeholder |
|  | [0091](https://mas.owasp.org/MASWE/MASVS-RESILIENCE/MASWE-0091/) | Anti-Deobfuscation Techniques Not Implemented |❌| Placeholder |
|  | [0092](https://mas.owasp.org/MASWE/MASVS-RESILIENCE/MASWE-0092/) | Static Analysis Tools Not Prevented |❌| Placeholder |
|  | [0093](https://mas.owasp.org/MASWE/MASVS-RESILIENCE/MASWE-0093/) | Debugging Symbols Not Removed |❌| Placeholder |
|  | [0094](https://mas.owasp.org/MASWE/MASVS-RESILIENCE/MASWE-0094/) | Non-Production Resources Not Removed |❌| Placeholder |
|  | [0095](https://mas.owasp.org/MASWE/MASVS-RESILIENCE/MASWE-0095/) | Code That Disables Security Controls Not Removed |❌| Placeholder |
|  | [0096](https://mas.owasp.org/MASWE/MASVS-RESILIENCE/MASWE-0096/) | Data Sent Unencrypted Over Encrypted Connections |❌| Placeholder |
|  | [0097](https://mas.owasp.org/MASWE/MASVS-RESILIENCE/MASWE-0097/) | Root/Jailbreak Detection Not Implemented |❌| Placeholder |
|  | [0098](https://mas.owasp.org/MASWE/MASVS-RESILIENCE/MASWE-0098/) | App Virtualization Environment Detection Not Implemented |❌| Placeholder |
|  | [0099](https://mas.owasp.org/MASWE/MASVS-RESILIENCE/MASWE-0099/) | Emulator Detection Not Implemented |❌| Placeholder |
|  | [0100](https://mas.owasp.org/MASWE/MASVS-RESILIENCE/MASWE-0100/) | Device Attestation Not Implemented |❌| Placeholder |
|  | [0101](https://mas.owasp.org/MASWE/MASVS-RESILIENCE/MASWE-0101/) | Debugger Detection Not Implemented |❌| Placeholder |
|  | [0102](https://mas.owasp.org/MASWE/MASVS-RESILIENCE/MASWE-0102/) | Dynamic Analysis Tools Detection Not Implemented |❌| Placeholder |
|  | [0103](https://mas.owasp.org/MASWE/MASVS-RESILIENCE/MASWE-0103/) | RASP Techniques Not Implemented |❌| Placeholder |
|  | [0104](https://mas.owasp.org/MASWE/MASVS-RESILIENCE/MASWE-0104/) | App Integrity Not Verified |❌| Placeholder |
|  | [0105](https://mas.owasp.org/MASWE/MASVS-RESILIENCE/MASWE-0105/) | Integrity of App Resources Not Verified |❌| Placeholder |
|  | [0106](https://mas.owasp.org/MASWE/MASVS-RESILIENCE/MASWE-0106/) | Official Store Verification Not Implemented |❌| Placeholder |
|  | [0107](https://mas.owasp.org/MASWE/MASVS-RESILIENCE/MASWE-0107/) | Runtime Code Integrity Not Verified |❌| Placeholder |
| PRIVACY |  |  |  |  |
|  | [0108](https://mas.owasp.org/MASWE/MASVS-PRIVACY/MASWE-0108/) | Sensitive Data in Network Traffic |❌| Beta |
|  | [0109](https://mas.owasp.org/MASWE/MASVS-PRIVACY/MASWE-0109/) | Lack of Anonymization or Pseudonymisation Measures |❌| Beta |
|  | [0110](https://mas.owasp.org/MASWE/MASVS-PRIVACY/MASWE-0110/) |  Use of Unique Identifiers for User Tracking |❌| Beta |
|  | [0111](https://mas.owasp.org/MASWE/MASVS-PRIVACY/MASWE-0111/) | Inadequate Privacy Policy |❌| Beta |
|  | [0112](https://mas.owasp.org/MASWE/MASVS-PRIVACY/MASWE-0112/) | Inadequate Data Collection Declarations |❌| Beta |
|  | [0113](https://mas.owasp.org/MASWE/MASVS-PRIVACY/MASWE-0113/) | Lack of Proper Data Management Controls |❌| Beta |
|  | [0114](https://mas.owasp.org/MASWE/MASVS-PRIVACY/MASWE-0114/) | Inadequate Data Visibility Controls |❌| Beta |
|  | [0115](https://mas.owasp.org/MASWE/MASVS-PRIVACY/MASWE-0115/) | Inadequate or Ambiguous User Consent Mechanisms |❌| Beta |
|  | [0117](https://mas.owasp.org/MASWE/MASVS-PRIVACY/MASWE-0117/) | Inadequate Permission Management |❌| Beta |
