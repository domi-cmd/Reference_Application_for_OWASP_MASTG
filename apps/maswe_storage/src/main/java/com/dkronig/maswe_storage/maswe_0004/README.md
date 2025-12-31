# MASWE-0004: Sensitive Data Not Excluded From Backup

The relevant code for this vulnerability can be seen in [res/xml/backup_rules.xml](https://github.com/domi-cmd/Reference_Application_for_OWASP_MASTG/blob/main/apps/maswe_storage/src/main/res/xml/backup_rules.xml).

## The vulnerability consists of:

1. Explicitly including sensitive user data in the cloud backup in the lines [here](https://github.com/domi-cmd/Reference_Application_for_OWASP_MASTG/blob/main/apps/maswe_storage/src/main/res/xml/backup_rules.xml#L6-L7):

```xml
<cloud-backup disableIfNoEncryptionCapabilities="false">
        <!-- Backup user credentials stored in shared preferences -->
        <include domain="sharedpref" path="."/>
        <include domain="file" path="maswe_0001_user_credentials.txt"/>
</cloud-backup>
```

2. Explicitly including saved user credentials in device to device transfers in the lines [here](https://github.com/domi-cmd/Reference_Application_for_OWASP_MASTG/blob/main/apps/maswe_storage/src/main/res/xml/backup_rules.xml#L11-L13):

```xml
<device-transfer>
        <include domain="sharedpref" path="."/>
</device-transfer>
```

## How to exploit the vulnerability
To inspect the backup, one can change the backup transporter from cloud to local, run a backup and extract its files as documented in my wiki [here](https://github.com/domi-cmd/Reference_Application_for_OWASP_MASTG/wiki/Run-and-decompile-backup).

The files containing sensitive data will then be readable in their completely unencrypted state:
```shell
-rw-------  0 1000   1000     1527 Jan 01  1970 apps/com.dkronig.maswe_storage/_manifest
drwx------  0 10225  10225       0 Nov 05 11:20 apps/com.dkronig.maswe_storage/r/.agent-logs
-rw-------  0 10225  10225     225 Nov 05 12:37 apps/com.dkronig.maswe_storage/f/maswe_0002_user_credentials.txt 
-rw-------  0 10225  10225      24 Nov 05 13:00 apps/com.dkronig.maswe_storage/f/profileInstalled  
-rw-rw----  0 10225  10225     161 Nov 05 12:37 apps/com.dkronig.maswe_storage/sp/my_app_prefs.xml  
```

## The vulnerability can be fixed by:
1. Removing any include statements which deal with sensitive data, as well as adding exclude statements to explicitly remove them from the backup as follows:
```xml
<device-transfer>
        <exclude domain="sharedpref" path="."/>
</device-transfer>
```

## Interesting links and sources:
- [Medium Article discussing backup vulnerabilities](https://bevijaygupta.medium.com/backup-vulnerabilities-in-android-mobile-applications-fd1e7f79617c)
- [Android Developers discussing backup vulnerabilities and best practices](https://developer.android.com/privacy-and-security/risks/backup-best-practices)