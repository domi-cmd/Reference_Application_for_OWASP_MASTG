# MASWE-0055: Sensitive Data Leaked via Screenshots or Screen Recordings

The relevant code for this vulnerability can be seen in maswe_0055/LoginActivity.java and maswe_0055/RegisterActivity.java as well as the custom layouts for 
these two Activities.

## The vulnerability consists of:

1. Setting the password visibility in the layouts of both files to visible (android:inputType="textVisiblePassword") as seen in the lines here:
```xml
<EditText
    android:id="@+id/et_password"
    android:layout_width="@dimen/action_buttons"
    android:layout_height="wrap_content"
    android:hint="@string/password_hint"
    android:inputType="textVisiblePassword"
    app:layout_constraintTop_toBottomOf="@id/et_email"
    app:layout_constraintStart_toStartOf="parent"
    app:layout_constraintEnd_toEndOf="parent"
    android:layout_marginTop="12dp"/>
```
2. Disabling and clearing any lingering flags that would prevent screenshotting or screensharing of the two activities, as seen here:
```java
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    /**
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
            WindowManager.LayoutParams.FLAG_SECURE);
    **/
    // Clear any flags set that would prevent screenshots
    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_SECURE);
```

## The vulnerability can be exploited by:
1. The primary issue is that the user himself can accidentally leak his credentials, either through screenshots containing sensitive data, or screensharing of such.
2. An attacker can use tools such as Scrcpy to record the screen of the device via usb connection [1].

## Sources
- [Srcpy tool for screenrecording](https://github.com/Genymobile/scrcpy)
