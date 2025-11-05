# MASWE-0003: Backup Unencrypted

The relevant code for this vulnerability can be seen in res/xml/backup_rules.xml

The issues lie in **complete absence of encryption** both **for the cloud backup**, as well as **for device to device transfer**.


# Inspecting the backup
To inspect the backup, one can change the backup transporter from cloud to local, run a backup and extract its files as follows:
### 1. Switch to local backup instead of cloud backup
adb shell bmgr transport com.android.localtransport/.LocalTransport

(Switch back to cloud backup afterwards:
adb shell bmgr transport com.google.android.gms/.backup.BackupTransportService)

### 2. To create backup:
adb backup com.dkronig.maswe_storage -f myapp_backup.ab 

### 3. unpack into a .tar file:
(get extractor from here: https://github.com/nelenkov/android-backup-extractor/releases/tag/latest)
java -jar C:\Users\Domi\Tools\abe.jar unpack myapp_backup.ab myapp_backup.tar    

output: 

``
40% 80% 98%
6656 bytes written to myapp_backup.tar.
``

### 4. check the contents of the jar file:
tar -tvf myapp_backup.tar

output:

``
-rw-------  0 1000   1000     1527 Jan 01  1970 apps/com.dkronig.maswe_storage/_manifest
``

``
drwx------  0 10225  10225       0 Nov 05 11:20 apps/com.dkronig.maswe_storage/r/.agent-logs
``

``
-rw-------  0 10225  10225     225 Nov 05 12:37 apps/com.dkronig.maswe_storage/f/maswe_0001_user_credentials.txt 
``

``
-rw-------  0 10225  10225      24 Nov 05 13:00 apps/com.dkronig.maswe_storage/f/profileInstalled  
``

``
-rw-rw----  0 10225  10225     161 Nov 05 12:37 apps/com.dkronig.maswe_storage/sp/my_app_prefs.xml  
``

### 5. unzip backup to actually view the contents of the file
(get 7zip here: https://www.7-zip.org/)
