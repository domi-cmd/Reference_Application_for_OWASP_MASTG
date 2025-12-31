# Coding Style Guide
[Android Developers](https://source.android.com/docs/setup/contribute/code-style#follow-field-naming-conventions) itself says:
"Note: These rules are intended for the Android platform and aren't required of Android app developers. App developers may follow the standard of their choosing, such as the Google Java Style Guide."

This is why my coding style is based on the android guideslines as layed out in [this repository](https://github.com/ribot/android-guidelines/blob/master/project_and_code_guidelines.md), while also making use of the [google standard for java](https://google.github.io/styleguide/javaguide.html).

---

# Most Important Guidelines
## Variable naming for .java files
- We use camelCase in accordance to the Google Java Style guide.:
```java
private EncryptionHandler encryptionHandler;
```
- For final variables we use all caps:
```java
private static final String SCREEN_TITLE = "Login Page";
```

## Variable naming for .xml variables
- All variables are prefixed with their type, followed by an underscore.
- camelCase is used for the naming.
- Examples:
```xml
<Button btn_loginPage>
<EditText et_bankBalanceChange>
```
- Short names of major components:
```
Button - btn  
EditText - et  
TextView - tv  
ProgressBar - pb  
Checkbox - chk  
RadioButton - rb  
ToggleButton - tb  
Spinner - spn  
Menu - mnu  
ListView - lv  
GalleryView - gv  
LinearLayout -ll  
RelativeLayout - rl  
```

## Import order
- The ordering of import statements is:
1. Android imports
2. Imports from third parties (com, junit, net, org)
3. java and javax
4. Same project imports
- Example:
```java
package com.dkronig.maswe_storage.maswe_0007;

import android.os.Bundle;

import java.util.Map;

import com.dkronig.common.BaseActivityTemplate;
import com.dkronig.maswe_storage.R;

```


