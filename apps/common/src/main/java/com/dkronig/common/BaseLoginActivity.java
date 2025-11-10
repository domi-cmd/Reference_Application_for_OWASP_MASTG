package com.dkronig.common;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;

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
    private static final String PREFS_FILE = "secure_users_credentials";
    private static final String USERS_KEY = "users_json";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Child class must set its layout before calling initLoginForm
        setContentView(getLayoutId());

        // Initialize login form
        initLoginForm();
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

        try {
            if (verifyLogin(email, password)) {
                onLoginSuccess(email);
            } else {
                onLoginFailure(email);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Login error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private boolean verifyLogin(String email, String password) throws Exception {
        MasterKey masterKey = new MasterKey.Builder(this)
                .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                .build();

        var prefs = EncryptedSharedPreferences.create(
                this,
                PREFS_FILE,
                masterKey,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        );

        String json = prefs.getString(USERS_KEY, "{}");
        JSONObject users = new JSONObject(json);

        if (!users.has(email)) return false; // user not found

        JSONObject userObj = users.getJSONObject(email);
        String storedPassword = userObj.getString("password");

        return storedPassword.equals(password);
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
