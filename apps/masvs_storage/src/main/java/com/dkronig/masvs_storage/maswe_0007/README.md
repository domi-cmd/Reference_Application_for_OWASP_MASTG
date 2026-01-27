# MASWE-0007: Sensitive Data Stored Unencrypted in Shared Storage Requiring No User Interaction

The relevant code for this vulnerability can be seen in [maswe_0007/RegisterActivity.java](https://github.com/domi-cmd/Reference_Application_for_OWASP_MASTG/blob/main/apps/masvs_storage/src/main/java/com/dkronig/masvs_storage/maswe_0007/RegisterActivity.java).

## The vulnerability consists of:
Using MediaStore to store user credentials upon user registration in shared storage space requiring no user interaction.
1. The Mediastore entry is created in the lines here:
```java
private static final String FILENAME = "maswe_0007_user_credentials.txt";
private Uri fileUri;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // Create a new MediaStore entry in shared documents directory to store user credentials in
    ContentValues values = new ContentValues();
    values.put(MediaStore.MediaColumns.DISPLAY_NAME, FILENAME);
    values.put(MediaStore.MediaColumns.MIME_TYPE, "text/plain");
    values.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DOCUMENTS);

    fileUri = getContentResolver().insert(MediaStore.Files.
            getContentUri("external"), values);
}
```
2. Upon user registration, this function writing user credentials to shared storage is called in the lines here:
```java
private void writeToSharedStorage(String content) {
    // Write content using OutputStream wrapped in BufferedWriter
    try (OutputStream out = getContentResolver().openOutputStream(fileUri)) {
        assert out != null;
        out.write(content.getBytes());
        out.flush();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
```

## The vulnerability can be exploited by:
1. While running the app, any data stored to the chosen shared storage space can be accessed by running in a terminal:
```shell
C:\Users\Domi>adb shell cat /storage/emulated/0/Documents/maswe_0007_user_credentials.txt
Email: email Password: password
```

## The vulnerability can be fixed by:
Not storing sensitive in such a way. Instead, developers can:
1. Save data in internal storage.
2. Make use of app specific external storage, which is sandboxed per-app.
3. Instead of MediaStore use SAF, which requires user interacion to access.

## Interestings links and sources:
- [Android Developers discussing importante of encrypting shared storage data](https://developer.android.com/privacy-and-security/risks/sensitive-data-external-storage)