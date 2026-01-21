# MASWE-0064: Insecure Content Providers

The relevant code for this vulnerability can be seen in [maswe_0064/CustomContentProvider.java](https://github.com/domi-cmd/Reference_Application_for_OWASP_MASTG/blob/main/apps/maswe_platform/src/main/java/com/dkronig/maswe_platform/maswe_0064/CustomContentProvider.java).

## The vulnerability consists of:

1. Using granting reading permissions to any requesting service without checking their permissions in the lines here:
```java
 @Override
    public ParcelFileDescriptor openFile(Uri uri, String mode) throws FileNotFoundException {
        // extracts "login_data_readable.txt"
        String fileName = uri.getLastPathSegment();

        if (fileName == null) {
            throw new FileNotFoundException("No file name specified in URI");
        }

        File file = new File(getContext().getFilesDir(), fileName);
        return ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY);
    }
```

## The vulnerability can be exploited by:
1. The badly configured content provider can easily be detected when decompiling the apk as to view the sourcecode. Any attacker can do this by following the 3 apk decompiling steps outlined in my wiki [here](https://github.com/domi-cmd/Reference_Application_for_OWASP_MASTG/wiki/Decompile-apk-file).
2. To then exploit the poorly configured content provider as to read the users login data via terminal, simply run
```shell
adb shell content read --uri content://com.dkronig.root.CustomContentProvider/maswe_0064_user_credentials.txt
```
in a terminal with adb installed, and the content provider will provide you with the sensitive user credentials.

## Interesting links and sources:
- [Medium Articles discussing a real-world data leak due to content provider](https://cyberweapons.medium.com/critical-android-bug-insecure-exported-components-content-leak-a-real-world-writeup-dada800f7ee6)