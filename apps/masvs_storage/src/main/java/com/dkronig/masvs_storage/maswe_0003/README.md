# MASWE-0003: Backup Unencrypted

The relevant code for this vulnerability can be seen in [res/xml/backup_rules.xml](https://github.com/domi-cmd/Reference_Application_for_OWASP_MASTG/blob/main/apps/masvs_storage/src/main/res/xml/backup_rules.xml).

## The vulnerability consists of:

1. Enabling backups to be made even if no encryption of said backups is in place, by setting the flag to false in the line here:

```xml
<cloud-backup disableIfNoEncryptionCapabilities="false">
```

2. Not forcing client side encryption of the backup by excluding the following flag for each include statement for both cloud backup as well as device to device transfer:
```xml
<[...] requireFlags="clientSideEncryption"/>
```

## How to exploit the vulnerability
To inspect the backup, one can change the backup transporter from cloud to local, run a backup and extract its files as documented in my wiki [here](https://github.com/domi-cmd/Reference_Application_for_OWASP_MASTG/wiki/Run-and-decompile-backup).

The files will then be readable in their completely unencrypted state:
```shell
-rw-------  0 1000   1000     1527 Jan 01  1970 apps/com.dkronig.masvs_storage/_manifest
drwx------  0 10225  10225       0 Nov 05 11:20 apps/com.dkronig.masvs_storage/r/.agent-logs
-rw-------  0 10225  10225     225 Nov 05 12:37 apps/com.dkronig.masvs_storage/f/maswe_0001_user_credentials.txt 
-rw-------  0 10225  10225      24 Nov 05 13:00 apps/com.dkronig.masvs_storage/f/profileInstalled  
-rw-rw----  0 10225  10225     161 Nov 05 12:37 apps/com.dkronig.masvs_storage/sp/my_app_prefs.xml  
```


## The vulnerability can be fixed by:
1. Setting the boolean in the line here to true:

```xml
<cloud-backup disableIfNoEncryptionCapabilities="true">
```

This prevents any backup happening if no encryption is in place.

2. Adding the following flag for each include:

```xml
<[...] requireFlags="clientSideEncryption"/>
```

## Interesting links and sources:
- [Medium Article discussing backup vulnerabilities](https://bevijaygupta.medium.com/backup-vulnerabilities-in-android-mobile-applications-fd1e7f79617c)
- [Android Developers discussing backup vulnerabilities and best practices](https://developer.android.com/privacy-and-security/risks/backup-best-practices)