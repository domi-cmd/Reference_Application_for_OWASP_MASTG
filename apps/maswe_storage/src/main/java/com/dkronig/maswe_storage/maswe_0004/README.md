# MASWE-0004: Sensitive Data Not Excluded From Backup

The relevant code for this vulnerability can be seen in [res/xml/backup_rules.xml](https://github.com/domi-cmd/Reference_Application_for_OWASP_MASTG/blob/main/apps/maswe_storage/src/main/res/xml/backup_rules.xml).

## The vulnerability consists of:

1. Explicitly including sensitive user data in the cloud backup in the lines [here](https://github.com/domi-cmd/Reference_Application_for_OWASP_MASTG/blob/main/apps/maswe_storage/src/main/res/xml/backup_rules.xml#L6-L8):

```xml
<cloud-backup disableIfNoEncryptionCapabilities="false">
        <!-- Backup user credentials stored in shared preferences -->
        <include domain="sharedpref" path="."/>
        <include domain="file" path="maswe_0001_user_credentials.txt"/>
        <include domain="file" path="maswe_0002_user_credentials.txt"/>
</cloud-backup>
```

2. Explicitly including saved user credentials in device to device transfers in the lines [here](https://github.com/domi-cmd/Reference_Application_for_OWASP_MASTG/blob/main/apps/maswe_storage/src/main/res/xml/backup_rules.xml#L11-L13):

```xml
<device-transfer>
        <include domain="sharedpref" path="."/>
</device-transfer>
```

## The vulnerability can be fixed by:
1. Removing any include statements which deal with sensitive data, as well as adding exclude statements to explicitly remove them from the backup as follows:
```xml
<device-transfer>
        <exclude domain="sharedpref" path="."/>
</device-transfer>
```
