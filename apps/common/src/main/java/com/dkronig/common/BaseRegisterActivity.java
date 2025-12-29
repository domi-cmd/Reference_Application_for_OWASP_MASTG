package com.dkronig.common;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Abstract base template for registration activities with SharedPreferences-based storage.
 *
 * Features:
 *  - Automatic wiring of email/password fields and register button
 *  - Basic input validation
 *  - SharedPreferences credential storage
 *  - Duplicate user detection
 *  - Optional password encryption hooks
 *  - Customizable post-registration behavior
 *
 * Subclasses must implement abstract methods to provide:
 *  - Layout resource ID
 *  - View resource IDs for email, password, and register button
 */
public abstract class BaseRegisterActivity extends BaseActivityTemplate {
    private static final String SCREEN_TITLE = "Register Page";
    private static final String USERS_KEY = "users_json";
    private static final String DEFAULT_SHARED_PREFERENCES_FILE = "secure_users_credentials";

    protected EditText et_email;
    protected EditText et_password;
    protected Button btn_register;

    private String credentialsFile;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        credentialsFile = getCredentialFileName();
        setContentView(getLayoutId());
        initRegisterForm();
    }

    @Override
    protected String getScreenTitle(){
        return SCREEN_TITLE;
    }

    /**
     * Returns the SharedPreferences file in which the user credentials are to be stored.
     * Hook method that can be overridden to provide custom filename.
     *
     * @return The preferences file name (default: "secure_users_credentials")
     */
    protected String getCredentialFileName() {
        return DEFAULT_SHARED_PREFERENCES_FILE;
    }

    /**
     * Initializes the UI elements and sets the register button click listener.
     */
    protected void initRegisterForm() {
        et_email = findViewById(getEmailFieldId());
        et_password = findViewById(getPasswordFieldId());
        btn_register = findViewById(getRegisterButtonId());

        if (btn_register != null) {
            btn_register.setOnClickListener(v -> handleRegistration());
        }
    }

    /**
     * Handles the registration process: validates input, encrypts password,
     * and stores credentials.
     */
    protected void handleRegistration() {
        String email = et_email.getText().toString().trim();
        String password = et_password.getText().toString().trim();

        if (!validateInput(email, password)){
            return;
        }

        String encryptedPassword = encryptPassword(password);

        try {
            if (saveUser(email, encryptedPassword)) {
                onRegistrationSuccess(email, password);
            } else {
                onUserAlreadyExists(email);
            }
        } catch (Exception e) {
            handleRegistrationError(e);
        }
    }

    /**
     * Validates that email and password fields are not empty.
     *
     * @param email The email input
     * @param password The password input
     * @return true if both fields are non-empty, false otherwise
     */
    private boolean validateInput(String email, String password) {
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Email and password cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    /**
     * Encrypts the user's password before storage.
     * Override this method to implement custom encryption logic.
     *
     * @param plaintext The plaintext password
     * @return The encrypted password (default: returns password as is)
     */
    protected String encryptPassword(String plaintext) {
        return plaintext;
    }

    /**
     * Saves user credentials to SharedPreferences.
     *
     * @param email The user's email address
     * @param password The encrypted password
     * @return true if user was saved successfully, false if user already exists
     * @throws JSONException If JSON parsing or creation fails
     */
    private boolean saveUser(String email, String password)
            throws Exception {
        SharedPreferences sharedPreferences = getSharedPreferences(credentialsFile, MODE_PRIVATE);

        String json = sharedPreferences.getString(USERS_KEY, "{}");
        JSONObject users = new JSONObject(json);

        if (users.has(email)) {
            return false;
        }

        JSONObject userObj = new JSONObject();
        userObj.put("password", password);

        users.put(email, userObj);
        sharedPreferences.edit().putString(USERS_KEY, users.toString()).apply();
        return true;
    }

    /**
     * Called when registration succeeds.
     *
     * @param email The registered email address
     * @param password The plaintext password (before encryption)
     */
    private void onRegistrationSuccess(String email, String password) {
        Toast.makeText(this, "User Registered!", Toast.LENGTH_SHORT).show();
        onRegister(email, password);
    }

    /**
     * Called when a user with the given email already exists.
     *
     * @param email The duplicate email address
     */
    private void onUserAlreadyExists(String email) {
        Toast.makeText(this, "User already exists!", Toast.LENGTH_SHORT).show();
    }

    /**
     * Handles errors that occur during the registration process.
     *
     * @param e The exception that occurred
     */
    private void handleRegistrationError(Exception e) {
        e.printStackTrace();
        Toast.makeText(this, "Registration failed: " + e.getMessage(),
                Toast.LENGTH_SHORT).show();
    }

    /**
     * Hook for subclasses to perform additional actions after successful registration.
     * Override to implement custom post-registration behavior (e.g., navigation).
     *
     * @param email The registered email address
     * @param password The plaintext password (before encryption)
     */
    protected void onRegister(String email, String password) {
        // Default: no additional action
    }

    // Abstract methods to provide the resource IDs of form elements
    protected abstract @LayoutRes int getLayoutId();
    protected abstract int getEmailFieldId();
    protected abstract int getPasswordFieldId();
    protected abstract int getRegisterButtonId();
}