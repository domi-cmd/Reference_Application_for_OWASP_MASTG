package com.dkronig.common;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;
import org.json.JSONObject;

/**
 * Abstract template for Login-like activities.
 * Handles:
 *  - Wiring email/password EditTexts and login button
 *  - Basic input validation
 *  - SharedPreferences check for stored credentials
 */
public abstract class BaseLoginActivity extends BaseActivityTemplate {

    protected EditText et_email, et_password;
    protected Button login_button;
    private static String PREFS_FILE;
    private static final String USERS_KEY = "users_json";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PREFS_FILE = getCredentialFileName();

        // Child class must set its layout before calling initLoginForm
        setContentView(getLayoutId());

        // Initialize login form
        initLoginForm();
    }

    // Optionally allow for name setting of credentials file
    protected String getCredentialFileName() {
        // Default: class name
        return "secure_users_credentials";
    }

    /**
     * Initialize UI elements and wire login button
     */
    protected void initLoginForm() {
        et_email = findViewById(getEmailFieldId());
        et_password = findViewById(getPasswordFieldId());
        login_button = findViewById(getLoginButtonId());

        if (login_button != null) {
            login_button.setOnClickListener(v -> loginUser());
        }
    }

    protected void loginUser() {
        String email = et_email.getText().toString().trim();
        String password = et_password.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Email and password cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        String storedPassword;

        try {
            // Get user password from database
            storedPassword = retrieveUserData(email, password);

            // Assert it being not null
            assert storedPassword != null;

            // Check if the stored password matches the users input
            if (verifyLogin(password, storedPassword)) {
                onLoginSuccess(email);
            } else {
                onLoginFailure(email);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Login error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private String retrieveUserData(String email, String password) throws Exception {
        MasterKey masterKey = new MasterKey.Builder(this)
                .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                .build();

        EncryptedSharedPreferences prefs = (EncryptedSharedPreferences) EncryptedSharedPreferences
                .create(
                this,
                PREFS_FILE,
                masterKey,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        );

        String json = prefs.getString(USERS_KEY, "{}");
        JSONObject users = new JSONObject(json);

        if (!users.has(email)) return null; // user not found

        JSONObject userObj = users.getJSONObject(email);
        String storedPassword = userObj.getString("password");

        // Do potential decryption of password
        storedPassword = decrypt(storedPassword);

        return storedPassword;
    }

    // Can be overridden if necessary (for hashing, for example)
    protected boolean verifyLogin(String inputPassword, String storedPassword){
        return storedPassword.equals(inputPassword);
    }

    // Optional method, which can be overridden to add decryption
    protected String decrypt(String encryptedText) {
        // Default: return the string as passed
        return encryptedText;
    }

    /**
     * Called when login succeeds. Child classes define navigation/behavior.
     */
    protected abstract void onLoginSuccess(String email);

    /**
     * Called when login fails. Child classes can override for custom behavior.
     */
    protected void onLoginFailure(String email) {
        Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show();
    }

    // Abstract methods to provide UI IDs
    protected abstract @LayoutRes int getLayoutId();
    protected abstract int getEmailFieldId();
    protected abstract int getPasswordFieldId();
    protected abstract int getLoginButtonId();
}
