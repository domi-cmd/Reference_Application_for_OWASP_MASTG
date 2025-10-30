# How to exploit the vulnerability 

Since setting read and write permissions to world is deprecated and impossible in newer Android versions, the approach shown here requires the user to uprooot their device 
(or for our intents AVD), to then easily access the sensitive user data stored in the internal files.

This can be done by running 

``
adb root
``

to uproot the device, followed by 

``
adb shell cat /data/data/com.dkronig.root/files/user_login_data.txt
``

to easily access the sensitive data stored with insufficient access restrictions in our internal storage. This can be done in any terminal with adb installed.
