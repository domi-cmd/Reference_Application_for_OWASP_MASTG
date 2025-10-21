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

    /**
     * Main login logic: checks SharedPreferences for stored credentials
     */
    protected void loginUser() {
        String email = et_email.getText().toString().trim();
        String password = et_password.getText().toString().trim();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Email and password cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        SharedPreferences prefs = getSharedPreferences("my_app_prefs", MODE_PRIVATE);
        String storedEmail = prefs.getString("user_email", null);
        String storedPassword = prefs.getString("user_password", null);

        if (email.equals(storedEmail) && password.equals(storedPassword)) {
            onLoginSuccess(email);
        } else {
            onLoginFailure(email);
        }
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
