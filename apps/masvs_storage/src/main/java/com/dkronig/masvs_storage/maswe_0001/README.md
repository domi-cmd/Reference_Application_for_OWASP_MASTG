# MASWE-0001: Insertion of Sensitive Data into Logs

The relevant code for this vulnerability can be seen in maswe_0001/RegistryActivity.java.

## The vulnerability consists of:

1. Writing user credentials to system logs upon user registration in the lines here:
```java
private void userDataToSystemLogs(String user_email, String user_password){
        // Log user credentials to system logs
        Log.d(TAG, "New User registered");
        Log.d(TAG, "User E-Mail: "+ user_email);
        Log.d(TAG, "User Password: " + user_password);
}
```

2. Writing user credentials to app logs (apps data directory) upon user registration in the lines here:
```java
private void userDataToAppLogs(String user_email, String user_password){
        // Logging sensitive data to a file in app's data directory (App Logs)
        try {
            File logFile = new File(getFilesDir(), "maswe_0001_user_credentials.txt");
            FileWriter writer = new FileWriter(logFile, true);
            writer.append("Login - Username: " + user_email + ", Password: " + user_password + "\n");
            writer.close();
            Log.d(TAG, "Logged credentials to app logs");
        } catch (IOException e) {
            // System log incase the app logging did not work
            Log.e(TAG, "Error writing to log file: " + e.getMessage());
        }
}
```

## The vulnerability can be exploited by:
### 1. **Static code analysis**  
- Audit the apps source code for functions and classes used to create logs, such as:
```java
android.util.Log
Log.d | Log.e | Log.i | Log.v | Log.w | Log.wtf
Logger
```
   as well as key words and system output
```java
System.out.print | System.err.print
```
### 2. **Dynamic code analysis**
- Prerequsites are having ADB installed, as well as an android device (physical or emulator) with USB debugging enabled.
- First verify ADB working and search for your device by running the following in a terminal:
```shell
adb devices
```
- The output should look something like this:
```shell
List of devices attached
emulator-5554   device
```
- (OPIONAL) Clear any existing logs using:
```shell
adb logcat -c
```
- Search for your target app by either showing all installed packages:
```shell
adb shell pm list packages
```
- Or by directly searching for it by querying for its name:
```shell
adb shell pm list packages | findstr -i "appname"
```
- Concrete example:
```shell
C:\Users\Domi>adb shell pm list packages | findstr -i maswe_storage
package:com.dkronig.maswe_storage
```
- Now run the app and use all possibly relevant functions, which could leader to sensitive data being logged (register user, login, etc.)
- After this you can now start querying for potential logs
- All logs can be viewed by running:
```shell
adb logcat
```
- To query for logs including the package name of the app, run:
```shell
adb logcat | findstr "com.example.myapp"
```
- To filter for certain words in the logs, you can for example:
```shell
adb logcat | findstr /I "password email token jwt"
```

- To store matching strings in a txt file, you can run:
```shell
adb logcat | findstr /I "password email user token jwt auth secret key pass pwd login register" > leaks.txt
```

- To exploit the vulnerability in my app maswe_storage, you can for example run:
```shell
C:\Users\Domi>adb logcat | findstr /I "password email token jwt"
11-11 14:06:06.553  5648  5648 D [REGISTER ACTIVITY]: User E-Mail: my_email3
11-11 14:06:06.554  5648  5648 D [REGISTER ACTIVITY]: User Password: my_password
11-11 14:06:09.210   778  2721 W NotificationService: Toast already killed. pkg=com.dkronig.maswe_storage token=android.os.BinderProxy@d1cc6bb
```

## The vulnerability can be fixed by:
Removing any logging whatsoever when deploying/publishing it. Using logs should only be used in the apps closed development.

## Interesting links and sources
- [Android Developers discussing this type of vulnerability](https://developer.android.com/privacy-and-security/risks/log-info-disclosure)
- [Article on how to exploit insecure logs](https://medium.com/@princeoffl/exploring-common-vulnerabilities-in-android-insecure-logging-hardcoded-credentials-insecure-data-a3c9e0cb2611)
- [OWASP's Mobile Top 10 Security Risks discussing unprotected logging](https://owasp.org/www-project-mobile-top-10/2023-risks/m9-insecure-data-storage)
