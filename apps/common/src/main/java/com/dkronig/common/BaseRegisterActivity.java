package com.dkronig.common;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.security.crypto.MasterKey;
import androidx.security.crypto.EncryptedSharedPreferences;
import org.json.JSONException;
import java.io.IOException;
import org.json.JSONObject;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public abstract class BaseRegisterActivity extends BaseActivityTemplate {

    protected EditText et_email, et_password;
    protected Button register_button;
    private static String PREFS_FILE;
    private static final String USERS_KEY = "users_json";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PREFS_FILE = getCredentialFileName();
        
        setContentView(getLayoutId());
        initRegisterForm();
    }

    // Optionally allow for name setting of credentials file
    protected String getCredentialFileName() {
        // Default: class name
        return "secure_users_credentials";
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

        // Do potential encryption of input
        email = encrypt(email);
        password = encrypt(password);

        // Check if email or password is empty
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Email and password cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            if (saveUserSecurely(email, password)) {
                Toast.makeText(this, "User Registered!", Toast.LENGTH_SHORT).show();
                onRegister(email, password);
            } else {
                Toast.makeText(this, "User already exists!", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Registration failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    // Optional method, which can be overridden to add encryption
    protected String encrypt(String plaintext) {
        // Default: Return the string as passed
        return plaintext;
    }

    private boolean saveUserSecurely(String email, String password)
            throws Exception {

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

        // Check if user already exists
        if (users.has(email)) return false;

        JSONObject userObj = new JSONObject();
        userObj.put("password", password);

        users.put(email, userObj);
        prefs.edit().putString(USERS_KEY, users.toString()).apply();
        return true;
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
