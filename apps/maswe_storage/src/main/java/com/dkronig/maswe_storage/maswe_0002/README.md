# MASWE-0002: Sensitive Data Stored With Insufficient Access Restrictions in Internal Locations

Since setting read and write permissions to world is deprecated and impossible in newer Android versions and setting file permissions with runtime system commands does not allow for file access due to unchangable filedirectoy permissions, the approach shown here demonstrates a misconfigured FileProvider, which hands out URI permissions without checking the permissions of the receivers. 

The custom FileProvider prompts the user via intent chooser to choose an app who will be granted permission and to access the URI of the file were the user credentials are stored called "maswe_0002_user_credentials.txt".

<img width="184" height="168" alt="intent_chooser_popup" src="https://github.com/user-attachments/assets/d8efb9fb-dd13-4bb0-9e0c-403c41d729b0" />
