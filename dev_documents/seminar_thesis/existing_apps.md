| App Name | # Vulnerabilites | Storage | Crypto | Auth | Network | Platform | Code | Resilience | Privacy | 
| - | - | - | - | - | - | - | - | - | - | 
| [Androgoat](https://github.com/satishpatnayak/AndroGoat) | 24 | 6 Storage | 1 Crypto | - | 4 Network | 6 Platform | 4 Code | 2 Resilience | 1 Privacy | 
| [DIVA](https://github.com/payatu/diva-android) | 13 | 4 Storage | 2 Crypto | 3 Atuh | - | - | 3 Code | - | 1 Privacy | 
| [InsecureBankv2](https://github.com/dineshshetty/Android-InsecureBankv2) | 25 | 3 Storage | 2 Crypto | 3 Auth | 1 Network | 6 Platform | 3 Code | 5 Resilience | 2 Privacy | 
| [Hacking Playground Android App](https://github.com/OWASP/MASTG-Hacking-Playground) | 15 | 6 Storage | 1 Crypto | 0 Auth | 1 Network | 2 Platform | 3 Code | 1 Resilience | 1 Privacy | 
| [OVAA](https://github.com/oversecured/ovaa) | 18 | 1 Storage | 1 Crypto | 0 Auth | 0 Network | 10 Platform | 3 Code | 3 Resilience | 0 Privacy | 
| - | # | - Storage | - Crypto | - Auth | - Network | - Platform | - Code | - Resilience | - Privacy | 




# Androgoat
Vulnerabilities covered in this app:
## Storage
- Insecure Data Storage – Shared Prefs - 1
- Insecure Data Storage - Shared Prefs - 2
- Insecure Data Storage - SQLite
- Insecure Data Storage – Temp Files
- Insecure Data Storage – SD Card
- Insecure Logging

## Crypto
- Broken Cryptography

## Auth

## Network
- Network intercepting – HTTP
- Network intercepting – HTTPS
- Network intercepting – Certificate Pinning
- Misconfigured Network_Security_Config.xml
    
## Platform
- Unprotected Android Components – Activity
- Unprotected Android Components –Service
- Unprotected Android Components – Broadcast Receivers
- AndroidDebuggable
- Android allowBackup
- Custom URL Scheme

## Code
- Input Validations – XSS
- Input Validations – SQLi
- Input Validations – WebView
- Hard coding issues

## Resilience
- Root Detection
- Emulator Detection

## Privacy
- Keyboard Cache






# Uncrackables L1-L4 
## -> No Documentation of Vulnerabilities, only code






# Digitalbank
## -> No Documentation of Vulnerabilities, only code
Vulnerabilities covered in this app:






# DIVA
Vulnerabilities covered in this app:
## Storage
- Insecure Data Storage – Part 1
- Insecure Data Storage – Part 2
- Insecure Data Storage – Part 3
- Insecure Data Storage – Part 4
- Insecure Logging

## Crypto
- Hardcoding Issues – Part 1
- Hardcoding Issues – Part 2

## Auth
- Access Control Issues – Part 1
- Access Control Issues – Part 2
- Access Control Issues – Part 3

## Network
- 
    
## Platform
-

## Code
- Input Validation Issues – Part 1
- Input Validation Issues – Part 2
- Input Validation Issues – Part 3

## Resilience
-

## Privacy
- 





# [InsecureBankv2](https://github.com/dineshshetty/Android-InsecureBankv2)
Vulnerabilities covered in this app:
### Storage
- Insecure SDCard storage
- Insecure Logging mechanism
- Sensitive Information in Memory

### Crypto
- Weak Cryptography implementation
- Local Encryption issues

### Auth
- Weak Authorization mechanism
- Weak change password implementation
- Username Enumeration issue

### Network
- Insecure HTTP connections
    
### Platform
- Insecure Content Provider access
- Vulnerable Activity Components
- Flawed Broadcast Receivers
- Android Backup vulnerability
- Application Debuggable
- Insecure Webview implementation

### Code
- Intent Sniffing and Injection
- Parameter Manipulation
- Hardcoded secrets

### Resilience
- Root Detection and Bypass
- Emulator Detection and Bypass
- Runtime Manipulation
- Developer Backdoors
- Application Patching


### Privacy
- Android keyboard cache issues
- Android Pasteboard vulnerability









# Hacking Playground Android App
Vulnerabilities covered in this app:
### Storage
- Sensitive data in internal storage
- Sensitive data in external storage
- Sensitive data in plaintext shared preferences
- Sensitive data in unencrypted SQLite database
- Sensitive data in logs
- Memory dumps leaking sensitive data (decrypted keys, etc)

### Crypto
- Weak Encryption (XOR)

### Auth
- 

### Network
- Using insecure channels for loading webpages (http instead of https)
    
### Platform
- Unsecure remote WebViews
- Unsecure local WebViews

### Code
- SQL injection
- SQL injections via content provider
- Dynamic code injection (DEXClassLoader)

### Resilience
- Key to encrypted SQLite database stored locally (retrievable)

### Privacy
- Keyboard cache remembers sensitive data





# OVAA
Vulnerabilities covered in this app:
### Storage
- Obtaining access to app logs via InsecureLoggerService. Leak of credentials in LoginActivity Log.d("ovaa", "Processing " + loginData). (Insecure logging of sensitive user data)

### Crypto
- Use of the hardcoded AES key in WeakCrypto. (crypto/key management failure)

### Auth
- 

### Network
- 
    
### Platform
- Arbitrary login url via deeplink leaking sensitive user data (Platform IPC/manifest concern/issue)
- Obtaining access to arbitrary content providers via deeplink (Misuse of content provider permissions semantics and intent flags)
- Vulnerable host validation when processing deeplink (Incorrect origin/host validation, WebView/deeplink platform integration issue)
- Opening arbitrary URLs via deeplink (Unsafe WebView configuration)
- Access to arbitrary activities and access to arbitrary content providers via arbitrary Intent object (Insecure Intent handling)
- Theft of arbitrary files in MainActivity by intercepting an activity launch from Intent.ACTION_PICK and passing the URI to any file as data. (broken intent/URI handling & insufficient URI validation when interacting with platform picker)
- Insecure broadcast to MainActivity containing credentials. The attacker can register a broadcast receiver with action oversecured.ovaa.action.UNPROTECTED_CREDENTIALS_DATA and obtain the user's data. (Unsafe broadcast IPC and exposed receiver -> platform ipc misconfiguration)
- Insecure activity launch in MainActivity with action oversecured.ovaa.action.WEBVIEW, containing the user's encrypted data in the query parameter token. (Sensitive data passed via intents/URIs — unsafe use of platform IPC)
- Obtaining read/write access to arbitrary files in TheftOverwriteProvider via path-traversal in the value uri.getLastPathSegment(). (ContentProvider implementation mistake (platform component) that allows directory traversal)
- Use of very wide file sharing declaration for oversecured.ovaa.fileprovider content provider in root entry. (Manifest/provider configuration that exposes files system-wide — platform config issue)

### Code
- Deletion of arbitrary files via the insecure DeleteFilesSerializable deserialization object. (Insecure Deserialization)
- Hardcoded credentials to a dev environment endpoint in strings.xml in test_url entry. (Hardcoded sensitive data)
- Arbitrary code execution via a DEX library located in a world-readable/writable directory. (Unsafe runtime code loading from writable locations enables tampering / arbitrary code injection)

### Resilience
- Memory corruption via the MemoryCorruptionParcelable object.
- Memory corruption via the MemoryCorruptionSerializable object.
- Arbitrary Code Execution in OversecuredApplication by launching code from third-party apps with no security checks. (Allowing external code execution undermines runtime integrity / tamper resistance)

### Privacy
- 







# AppName
Vulnerabilities covered in this app:
### Storage
- 

### Crypto
- 

### Auth
- 

### Network
- 
    
### Platform
-

### Code
-

### Resilience
-

### Privacy
- 
