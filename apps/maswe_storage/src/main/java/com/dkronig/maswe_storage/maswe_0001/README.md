# MASWE-0001: Insertion of Sensitive Data into Logs

The relevant code for this vulnerability can be seen in [maswe_0001/RegistryActivity.java](https://github.com/domi-cmd/Reference_Application_for_OWASP_MASTG/blob/main/apps/maswe_storage/src/main/java/com/dkronig/maswe_storage/maswe_0001/RegisterActivity.java)

## The vulnerability consists of:

1. Writing user credentials to system logs upon user registration in the lines [here](https://github.com/domi-cmd/Reference_Application_for_OWASP_MASTG/blob/main/apps/maswe_storage/src/main/java/com/dkronig/maswe_storage/maswe_0001/RegisterActivity.java#L48-L53):
```java
private void userDataToSystemLogs(String user_email, String user_password){
        // Log user credentials to system logs
        Log.d(TAG, "New User registered");
        Log.d(TAG, "User E-Mail: "+ user_email);
        Log.d(TAG, "User Password: " + user_password);
}
```

2. Writing user credentials to app logs (apps data directory) upon user registration in the lines [here](https://github.com/domi-cmd/Reference_Application_for_OWASP_MASTG/blob/main/apps/maswe_storage/src/main/java/com/dkronig/maswe_storage/maswe_0001/RegisterActivity.java#L55-L67):
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


## The vulnerability can be fixed by:
1. Removing any logging whatsoever when deploying/publishing it. Using logs should only be used in the apps closed development.
