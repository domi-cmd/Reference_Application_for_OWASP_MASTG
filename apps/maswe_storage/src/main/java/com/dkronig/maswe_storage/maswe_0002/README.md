# MASWE-0002: Sensitive Data Stored With Insufficient Access Restrictions in Internal Locations

The relevant code for this vulnerability can be seen in maswe_storage's [AndroidManifest.xml](https://github.com/domi-cmd/Reference_Application_for_OWASP_MASTG/blob/main/apps/maswe_storage/src/main/AndroidManifest.xml) and [maswe_0002/RegisterActivity.java](https://github.com/domi-cmd/Reference_Application_for_OWASP_MASTG/blob/main/apps/maswe_storage/src/main/java/com/dkronig/maswe_storage/maswe_0002/RegisterActivity.java).

## The vulnerability consists of:

1. Using a FileProvider which grants URI permission without checking permissions of the requesting party in the lines here in the manifest:
```xml
<provider
    android:name="androidx.core.content.FileProvider"
    android:authorities="com.dkronig.maswe_storage.CustomFileProvider"
    android:exported="false"
    android:grantUriPermissions="true"
    android:permission="" >
    <meta-data
        android:name="android.support.FILE_PROVIDER_PATHS"
        android:resource="@xml/file_paths" />
</provider>
```
2. Using an implicit intent with the misconfigured FileProvider, attempting to share user credentials with other apps upon user registration in the lines here:
```java
@Override
  protected void onRegister(String email, String password) {
      Uri uri = FileProvider.getUriForFile(
              this,
              this.getPackageName() + ".CustomFileProvider",
              new File(this.getFilesDir(), "maswe_0002_user_credentials.txt")
      );

      Intent share = new Intent(Intent.ACTION_SEND);
      share.setType("text/plain");
      share.putExtra(Intent.EXTRA_STREAM, uri);
      share.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
      startActivity(Intent.createChooser(share, "Share file via"));
  }
```
3. The custom FileProvider then promts the user via intent chooser to select an app which will be granted permission to access the URI of the file called "maswe_0002_user_credentials.txt", where the user credentials are stored.

<img width="184" height="168" alt="intent_chooser_popup" src="https://github.com/user-attachments/assets/d8efb9fb-dd13-4bb0-9e0c-403c41d729b0" />


## The vulnerability can be fixed by:
1. Properly configuring the FileProvider by setting permissions.
2. Refraining from using implicit intents, as they can easily be abused.

## Further explanation for the reasoning behind how the vulnerability was implemented

Since setting read and write permissions to world is deprecated and impossible in newer Android versions and setting file permissions with runtime system commands does not allow for file access due to unchangable filedirectoy permissions, the approach shown here demonstrates a misconfigured FileProvider, which hands out URI permissions without checking the permissions of the receivers. 

## Interesting links and sources:
- [Android Developers discussing internal storage access restrictions](https://developer.android.com/privacy-and-security/security-tips#internal-storage)
- [Android Developers discussing deprecated insecure file reading permissions](https://developer.android.com/about/versions/nougat/android-7.0-changes#permfilesys)
