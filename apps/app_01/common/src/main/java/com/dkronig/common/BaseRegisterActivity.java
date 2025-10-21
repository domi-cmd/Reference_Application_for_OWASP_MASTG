package com.dkronig.common;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;

public abstract class BaseRegisterActivity extends BaseActivityTemplate {

    protected EditText et_email, et_password;
    protected Button register_button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(getLayoutId());
        initRegisterForm();
    }

    /**
     * Initializes the common registration form:
     * - Finds email/password EditTexts and register button
     * - Sets the register button click to call registerUser()
     */
    protected void initRegisterForm() {
        et_email = findViewById(getEmailFieldId());
        et_password = findViewById(getPasswordFieldId());
        register_button = findViewById(getRegisterButtonId());

        if (register_button != null) {
            register_button.setOnClickListener(v -> registerUser());
        }
    }

    /** Extracts email/password and stores them in SharedPreferences */
    protected void registerUser() {
        String email = et_email.getText().toString().trim();
        String password = et_password.getText().toString().trim();

        userDataToSharedPreferences(email, password);
        onRegister(email, password);
    }

    /** Stores user credentials in SharedPreferences */
    protected void userDataToSharedPreferences(String email, String password) {
        SharedPreferences sharedPrefs = getSharedPreferences("my_app_prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString("user_email", email);
        editor.putString("user_password", password);
        editor.apply();
    }

    /**
     * Optional hook for child classes to perform additional actions after registration.
     * By default does nothing.
     */
    protected void onRegister(String email, String password) {
        // Child classes can override
    }

    // Abstract methods to provide the resource IDs of form elements
    protected abstract @LayoutRes int getLayoutId();
    protected abstract int getEmailFieldId();
    protected abstract int getPasswordFieldId();
    protected abstract int getRegisterButtonId();
}
