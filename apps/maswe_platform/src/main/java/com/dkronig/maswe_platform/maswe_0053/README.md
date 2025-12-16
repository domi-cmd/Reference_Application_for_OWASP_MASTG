# MASWE-0053: Sensitive Data Leaked via the User Interface

The relevant code for this vulnerability can be seen in maswe_0053/LoginActivity.java and RegistryActivity.java.

## The vulnerability consists of:

In both files, the vulnerability is implemented symmetrically.

1. Removing password obfuscation, as well as adding autocomplete and autocorrect flags for the password field in the lines here:
```java
password_field.setInputType(InputType.TYPE_CLASS_TEXT |
        InputType.TYPE_TEXT_FLAG_AUTO_COMPLETE |
        InputType.TYPE_TEXT_FLAG_AUTO_CORRECT);
```
2. Adding the same autocorrect and autocomplete flags for the email field here:
```java
email_field.setInputType(InputType.TYPE_TEXT_FLAG_AUTO_COMPLETE |
        InputType.TYPE_TEXT_FLAG_AUTO_CORRECT);
```

3. Making sure that copy and paste functionalities are enabled for the password field here:
```java
password_field.setCustomSelectionActionModeCallback(null);
```

## The vulnerability can be exploited by:
 TO BE ADDED
1. Removing any XYZ as follows:
```

```
