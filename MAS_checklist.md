# Tests Implemented: 0 / 93

# Checklist

| Category | # | Description | Implementation-Status | MAS-Status |
|----------|----------|----------|----------|----------|
| STORAGE |  |  |  |  |
|  | [0004](https://mas.owasp.org/MASTG/tests/android/MASVS-STORAGE/MASTG-TEST-0004/) | Determining Whether Sensitive Data Is Shared with Third Parties via Embedded Services | ❌ | Updated Soon |
|  | [0005](https://mas.owasp.org/MASTG/tests/android/MASVS-STORAGE/MASTG-TEST-0005/) | Determining Whether Sensitive Data Is Shared with Third Parties via Notifications |❌| Updated Soon |
|  | [0011](https://mas.owasp.org/MASTG/tests/android/MASVS-STORAGE/MASTG-TEST-0011/) | Testing Memory for Sensitive Data |❌| Updated Soon |
|  | [0200](https://mas.owasp.org/MASTG/tests/android/MASVS-STORAGE/MASTG-TEST-0200/) | Files Written to External Storage |❌| Normal |
|  | [0201](https://mas.owasp.org/MASTG/tests/android/MASVS-STORAGE/MASTG-TEST-0201/) | Runtime Use of APIs to Access External Storage |❌| Normal |
|  | [0202](https://mas.owasp.org/MASTG/tests/android/MASVS-STORAGE/MASTG-TEST-0202/) | References to APIs and Permissions for Accessing External Storage |❌| Normal |
|  | [0203](https://mas.owasp.org/MASTG/tests/android/MASVS-STORAGE/MASTG-TEST-0203/) | Runtime Use of Logging APIs |❌| Normal |
|  | [0200](https://mas.owasp.org/MASTG/tests/android/MASVS-STORAGE/MASTG-TEST-0200/) | Files Written to External Storage |❌| Normal |
|  | [0207](https://mas.owasp.org/MASTG/tests/android/MASVS-STORAGE/MASTG-TEST-0207/) | Data Stored in the App Sandbox at Runtime |❌| Normal |
|  | [0216](https://mas.owasp.org/MASTG/tests/android/MASVS-STORAGE/MASTG-TEST-0216/) | Sensitive Data Not Excluded From Backup |❌| Normal |
|  | [0231](https://mas.owasp.org/MASTG/tests/android/MASVS-STORAGE/MASTG-TEST-0231/) | References to Logging APIs |❌| Normal |
|  | [0262](https://mas.owasp.org/MASTG/tests/android/MASVS-STORAGE/MASTG-TEST-0262/) | References to Backup Configurations Not Excluding Sensitive Data |❌| Normal |
|  | [0287](https://mas.owasp.org/MASTG/tests/android/MASVS-STORAGE/MASTG-TEST-0287/) | Sensitive Data Stored Unencrypted via the SharedPreferences API to the App Sandbox |❌| MISSING IMPLEMENTATION |
| CRYPTO |  |  |  |  |
|  | [0014](https://mas.owasp.org/MASTG/tests/android/MASVS-CRYPTO/MASTG-TEST-0014/) | Testing the Configuration of Cryptographic Standard Algorithms |❌| Updated Soon |
|  | [0015](https://mas.owasp.org/MASTG/tests/android/MASVS-CRYPTO/MASTG-TEST-0015/) | Testing the Purposes of Keys |❌| Updated Soon |
|  | [0204](https://mas.owasp.org/MASTG/tests/android/MASVS-CRYPTO/MASTG-TEST-0204/) | Insecure Random API Usage |❌| Normal |
|  | [0205](https://mas.owasp.org/MASTG/tests/android/MASVS-CRYPTO/MASTG-TEST-0205/) | Non-random Sources Usage |❌| Normal |
|  | [0208](https://mas.owasp.org/MASTG/tests/android/MASVS-CRYPTO/MASTG-TEST-0208/) | Insufficient Key Sizes |❌| Normal |
|  | [0208](https://mas.owasp.org/MASTG/tests/android/MASVS-CRYPTO/MASTG-TEST-0212/) | Use of Hardcoded Cryptographic Keys in Code |❌| Normal |
|  | [0221](https://mas.owasp.org/MASTG/tests/android/MASVS-CRYPTO/MASTG-TEST-0221/) | Broken Symmetric Encryption Algorithms |❌| Normal |
|  | [0232](https://mas.owasp.org/MASTG/tests/android/MASVS-CRYPTO/MASTG-TEST-0232/) | Broken Symmetric Encryption Modes |❌| Normal |
| AUTH |  |  |  |  |
|  | [0017](https://mas.owasp.org/MASTG/tests/android/MASVS-AUTH/MASTG-TEST-0017/) | Testing Confirm Credentials |❌| Updated Soon |
|  | [0018](https://mas.owasp.org/MASTG/tests/android/MASVS-AUTH/MASTG-TEST-0018/) | Testing Biometric Authentication |❌| Updated Soon |
| NETWORK |  |  |  |  |
|  | [0217](https://mas.owasp.org/MASTG/tests/android/MASVS-NETWORK/MASTG-TEST-0217/) | Insecure TLS Protocols Explicitly Allowed in Code |❌| Normal |
|  | [0218](https://mas.owasp.org/MASTG/tests/android/MASVS-NETWORK/MASTG-TEST-0218/) | Insecure TLS Protocols in Network Traffic |❌| Normal |
|  | [0233](https://mas.owasp.org/MASTG/tests/android/MASVS-NETWORK/MASTG-TEST-0233/) | Hardcoded HTTP URLs |❌| Normal |
|  | [0234](https://mas.owasp.org/MASTG/tests/android/MASVS-NETWORK/MASTG-TEST-0234/) | Missing Implementation of Server Hostname Verification with SSLSockets |❌| Normal |
|  | [0235](https://mas.owasp.org/MASTG/tests/android/MASVS-NETWORK/MASTG-TEST-0235/) | Android App Configurations Allowing Cleartext Traffic |❌| Normal |
|  | [0236](https://mas.owasp.org/MASTG/tests/android/MASVS-NETWORK/MASTG-TEST-0236/) | Cleartext Traffic Observed on the Network |❌| Normal |
|  | [0237](https://mas.owasp.org/MASTG/tests/android/MASVS-NETWORK/MASTG-TEST-0237/) | Cross-Platform Framework Configurations Allowing Cleartext Traffic |❌| MISSING IMPLEMENTATION |
|  | [0238](https://mas.owasp.org/MASTG/tests/android/MASVS-NETWORK/MASTG-TEST-0238/) | Runtime Use of Network APIs Transmitting Cleartext Traffic |❌| MISSING IMPLEMENTATION |
|  | [0239](https://mas.owasp.org/MASTG/tests/android/MASVS-NETWORK/MASTG-TEST-0239/) | Using low-level APIs (e.g. Socket) to set up a custom HTTP connection |❌| MISSING IMPLEMENTATION |
|  | [0242](https://mas.owasp.org/MASTG/tests/android/MASVS-NETWORK/MASTG-TEST-0242/) | Missing Certificate Pinning in Network Security Configuration |❌| Normal |
|  | [0243](https://mas.owasp.org/MASTG/tests/android/MASVS-NETWORK/MASTG-TEST-0243/) | Expired Certificate Pins in the Network Security Configuration |❌| Normal |
|  | [0244](https://mas.owasp.org/MASTG/tests/android/MASVS-NETWORK/MASTG-TEST-0244/) | Missing Certificate Pinning in Network Traffic |❌| Normal |
|  | [0282](https://mas.owasp.org/MASTG/tests/android/MASVS-NETWORK/MASTG-TEST-0282/) | Unsafe Custom Trust Evaluation |❌| Normal |
|  | [0283](https://mas.owasp.org/MASTG/tests/android/MASVS-NETWORK/MASTG-TEST-0283/) | Incorrect Implementation of Server Hostname Verification |❌| Normal |
|  | [0284](https://mas.owasp.org/MASTG/tests/android/MASVS-NETWORK/MASTG-TEST-0284/) | Incorrect SSL Error Handling in WebViews |❌| Normal |
|  | [0285](https://mas.owasp.org/MASTG/tests/android/MASVS-NETWORK/MASTG-TEST-0285/) | Outdated Android Version Allowing Trust in User-Provided CAs |❌| Normal |
|  | [0286](https://mas.owasp.org/MASTG/tests/android/MASVS-NETWORK/MASTG-TEST-0286/) | Network Security Configuration Allowing Trust in User-Provided CAs |❌| Normal |
|  | [0295](https://mas.owasp.org/MASTG/tests/android/MASVS-NETWORK/MASTG-TEST-0295/) | GMS Security Provider Not Updated |❌| Normal |
| PLATFORM |  |  |  |  |
|  | [0007](https://mas.owasp.org/MASTG/tests/android/MASVS-PLATFORM/MASTG-TEST-0007/) | Determining Whether Sensitive Stored Data Has Been Exposed via IPC Mechanisms |❌| Normal |
|  | [0008](https://mas.owasp.org/MASTG/tests/android/MASVS-PLATFORM/MASTG-TEST-0008/) | Checking for Sensitive Data Disclosure Through the User Interface |❌| Normal |
|  | [0028](https://mas.owasp.org/MASTG/tests/android/MASVS-PLATFORM/MASTG-TEST-0028/) | Testing Deep Links |❌| Updated Soon |
|  | [0029](https://mas.owasp.org/MASTG/tests/android/MASVS-PLATFORM/MASTG-TEST-0029/) | Testing for Sensitive Functionality Exposure Through IPC |❌| Updated Soon |
|  | [0030](https://mas.owasp.org/MASTG/tests/android/MASVS-PLATFORM/MASTG-TEST-0030/) | Testing for Vulnerable Implementation of PendingIntent |❌| Updated Soon |
|  | [0033](https://mas.owasp.org/MASTG/tests/android/MASVS-PLATFORM/MASTG-TEST-0033/) | Testing for Java Objects Exposed Through WebViews |❌| Updated Soon |
|  | [0035](https://mas.owasp.org/MASTG/tests/android/MASVS-PLATFORM/MASTG-TEST-0035/) | Testing for Overlay Attacks |❌| Updated Soon |
|  | [0037](https://mas.owasp.org/MASTG/tests/android/MASVS-PLATFORM/MASTG-TEST-0037/) | Testing WebViews Cleanup |❌| Updated Soon |
|  | [0250](https://mas.owasp.org/MASTG/tests/android/MASVS-PLATFORM/MASTG-TEST-0250/) | References to Content Provider Access in WebViews |❌| Normal |
|  | [0251](https://mas.owasp.org/MASTG/tests/android/MASVS-PLATFORM/MASTG-TEST-0251/) | Runtime Use of Content Provider Access APIs in WebViews |❌| Normal |
|  | [0252](https://mas.owasp.org/MASTG/tests/android/MASVS-PLATFORM/MASTG-TEST-0252/) | References to Local File Access in WebViews |❌| Normal |
|  | [0253](https://mas.owasp.org/MASTG/tests/android/MASVS-PLATFORM/MASTG-TEST-0253/) | Runtime Use of Local File Access APIs in WebViews |❌| Normal |
|  | [0258](https://mas.owasp.org/MASTG/tests/android/MASVS-PLATFORM/MASTG-TEST-0258/) | References to Keyboard Caching Attributes in UI Elements |❌| Normal |
|  | [0289](https://mas.owasp.org/MASTG/tests/android/MASVS-PLATFORM/MASTG-TEST-0289/) | Runtime Verification of Sensitive Content Exposure in Screenshots During App Backgrounding |❌| Normal |
|  | [0291](https://mas.owasp.org/MASTG/tests/android/MASVS-PLATFORM/MASTG-TEST-0291/) | References to Screen Capturing Prevention APIs |❌| Normal |
|  | [0292](https://mas.owasp.org/MASTG/tests/android/MASVS-PLATFORM/MASTG-TEST-0292/) | `setRecentsScreenshotEnabled` Not Used to Prevent Screenshots When Backgrounded |❌| MISSING IMPLEMENTATION |
|  | [0293](https://mas.owasp.org/MASTG/tests/android/MASVS-PLATFORM/MASTG-TEST-0293/) | `setSecure` Not Used to Prevent Screenshots in SurfaceViews |❌| MISSING IMPLEMENTATION |
|  | [0294](https://mas.owasp.org/MASTG/tests/android/MASVS-PLATFORM/MASTG-TEST-0294/) | `SecureOn` Not Used to Prevent Screenshots in Compose Dialogs |❌| MISSING IMPLEMENTATION |
| CODE |  |  |  |  |
|  | [0002](https://mas.owasp.org/MASTG/tests/android/MASVS-CODE/MASTG-TEST-0002/) | Testing Local Storage for Input Validation |❌| Updated Soon |
|  | [0025](https://mas.owasp.org/MASTG/tests/android/MASVS-CODE/MASTG-TEST-0025/) | Testing for Injection Flaws |❌| Updated Soon |
|  | [0026](https://mas.owasp.org/MASTG/tests/android/MASVS-CODE/MASTG-TEST-0026/) | Testing Implicit Intents |❌| Updated Soon |
|  | [0027](https://mas.owasp.org/MASTG/tests/android/MASVS-CODE/MASTG-TEST-0027/) | Testing for URL Loading in WebViews |❌| Updated Soon |
|  | [0034](https://mas.owasp.org/MASTG/tests/android/MASVS-CODE/MASTG-TEST-0034/) | Testing Object Persistence |❌| Updated Soon |
|  | [0036](https://mas.owasp.org/MASTG/tests/android/MASVS-CODE/MASTG-TEST-0036/) | Testing Enforced Updating |❌| Updated Soon |
|  | [0043](https://mas.owasp.org/MASTG/tests/android/MASVS-CODE/MASTG-TEST-0043/) | Memory Corruption Bugs |❌| Updated Soon |
|  | [0222](https://mas.owasp.org/MASTG/tests/android/MASVS-CODE/MASTG-TEST-0222/) | Position Independent Code (PIC) Not Enabled |❌| Normal |
|  | [0223](https://mas.owasp.org/MASTG/tests/android/MASVS-CODE/MASTG-TEST-0223/) | Stack Canaries Not Enabled |❌| Normal |
|  | [0245](https://mas.owasp.org/MASTG/tests/android/MASVS-CODE/MASTG-TEST-0245/) | References to Platform Version APIs |❌| Normal |
|  | [0272](https://mas.owasp.org/MASTG/tests/android/MASVS-CODE/MASTG-TEST-0272/) | Identify Dependencies with Known Vulnerabilities in the Android Project |❌| Normal |
|  | [0274](https://mas.owasp.org/MASTG/tests/android/MASVS-CODE/MASTG-TEST-0274/) | Dependencies with Known Vulnerabilities in the App's SBOM |❌| Normal |
| RESILIENCE |  |  |  |  |
|  | [0045](https://mas.owasp.org/MASTG/tests/android/MASVS-RESILIENCE/MASTG-TEST-0045/) | Testing Root Detection |❌| Updated Soon |
|  | [0046](https://mas.owasp.org/MASTG/tests/android/MASVS-RESILIENCE/MASTG-TEST-0046/) | Testing Anti-Debugging Detection |❌| Updated Soon |
|  | [0047](https://mas.owasp.org/MASTG/tests/android/MASVS-RESILIENCE/MASTG-TEST-0047/) | Testing File Integrity Checks |❌| Updated Soon |
|  | [0048](https://mas.owasp.org/MASTG/tests/android/MASVS-RESILIENCE/MASTG-TEST-0048/) | Testing Reverse Engineering Tools Detection |❌| Updated Soon |
|  | [0049](https://mas.owasp.org/MASTG/tests/android/MASVS-RESILIENCE/MASTG-TEST-0049/) | Testing Emulator Detection |❌| Updated Soon |
|  | [0050](https://mas.owasp.org/MASTG/tests/android/MASVS-RESILIENCE/MASTG-TEST-0050/) | Testing Runtime Integrity Checks |❌| Updated Soon |
|  | [0051](https://mas.owasp.org/MASTG/tests/android/MASVS-RESILIENCE/MASTG-TEST-0051/) | Testing Obfuscation |❌| Updated Soon |
|  | [0224](https://mas.owasp.org/MASTG/tests/android/MASVS-RESILIENCE/MASTG-TEST-0224/) | Usage of Insecure Signature Version |❌| Normal |
|  | [0225](https://mas.owasp.org/MASTG/tests/android/MASVS-RESILIENCE/MASTG-TEST-0225/) | Usage of Insecure Signature Key Size |❌| Normal |
|  | [0226](https://mas.owasp.org/MASTG/tests/android/MASVS-RESILIENCE/MASTG-TEST-0226/) | Debuggable Flag Enabled in the AndroidManifest |❌| Normal |
|  | [0227](https://mas.owasp.org/MASTG/tests/android/MASVS-RESILIENCE/MASTG-TEST-0227/) | Debugging Enabled for WebViews |❌| Normal |
|  | [0247](https://mas.owasp.org/MASTG/tests/android/MASVS-RESILIENCE/MASTG-TEST-0247/) | References to APIs for Detecting Secure Screen Lock |❌| Normal |
|  | [0249](https://mas.owasp.org/MASTG/tests/android/MASVS-RESILIENCE/MASTG-TEST-0249/) | Runtime Use of Secure Screen Lock Detection APIs |❌| Normal |
|  | [0263](https://mas.owasp.org/MASTG/tests/android/MASVS-RESILIENCE/MASTG-TEST-0263/) | Logging of StrictMode Violations |❌| Normal |
|  | [0264](https://mas.owasp.org/MASTG/tests/android/MASVS-RESILIENCE/MASTG-TEST-0264/) | Runtime Use of StrictMode APIs |❌| Normal |
|  | [0265](https://mas.owasp.org/MASTG/tests/android/MASVS-RESILIENCE/MASTG-TEST-0265/) | References to StrictMode APIs |❌| Normal |
|  | [0288](https://mas.owasp.org/MASTG/tests/android/MASVS-RESILIENCE/MASTG-TEST-0288/) | Debugging Symbols in Native Binaries |❌| Normal |
| PRIVACY |  |  |  |  |
|  | [0206](https://mas.owasp.org/MASTG/tests/android/MASVS-PRIVACY/MASTG-TEST-0206/) | Sensitive Data in Network Traffic Capture |❌| Normal |
|  | [0254](https://mas.owasp.org/MASTG/tests/android/MASVS-PRIVACY/MASTG-TEST-0254/) | Dangerous App Permissions |❌| Normal |
|  | [0255](https://mas.owasp.org/MASTG/tests/android/MASVS-PRIVACY/MASTG-TEST-0255/) | Permission Requests Not Minimized |❌| MISSING IMPLEMENTATION |
|  | [0256](https://mas.owasp.org/MASTG/tests/android/MASVS-PRIVACY/MASTG-TEST-0256/) | Missing Permission Rationale |❌| MISSING IMPLEMENTATION |
|  | [0257](https://mas.owasp.org/MASTG/tests/android/MASVS-PRIVACY/MASTG-TEST-0257/) | Not Resetting Unused Permissions |❌| MISSING IMPLEMENTATION |
