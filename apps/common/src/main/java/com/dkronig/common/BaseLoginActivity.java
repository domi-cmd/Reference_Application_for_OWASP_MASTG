package com.dkronig.common;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;

import org.json.JSONObject;
import org.json.JSONException;

import java.io.File;

/**
 * Abstract template for all Login activities which do not require extra encryption.
 *
 * Features:
 *  - Wiring email/password EditTexts and login button
 *  - Basic input validation
 *  - SharedPreferences credential storage and retrieval
 *  - Optional password encryption/decryption hooks
 *  - Customizable login success/failure behavior
 *
 * Subclasses must implement abstract methods to provide:
 *  - Layout resource ID
 *  - View resource IDs for email, password, and login button
 *  - Login success behavior
 */
public abstract class BaseLoginActivity extends BaseActivityTemplate {
    private static final String USERS_KEY = "users_json";
    private static final String DEFAULT_CREDENTIALS_FILE = "secure_users_credentials";
    private static final String SHARED_PREFERENCES_PATH = "/shared_prefs/";
    private static final String XML_EXTENSION = ".xml";

    protected EditText et_email;
    protected EditText et_password;
    protected Button btn_login;

    private String credentialsFile;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        credentialsFile = getCredentialFileName();
        setContentView(getLayoutId());
        initLoginForm();
    }

    /**
     * Returns the SharedPreferences file in which the user credentials are to be stored.
     * Hook method that can be overridden to provide custom filename.
     *
     * @return The preferences file name (default: "secure_users_credentials")
     */
    protected String getCredentialFileName() {
        return DEFAULT_CREDENTIALS_FILE;
    }

    /**
     * Initializes the UI elements and sets the login button click listener.
     */
    protected void initLoginForm() {
        et_email = findViewById(getEmailFieldId());
        et_password = findViewById(getPasswordFieldId());
        btn_login = findViewById(getLoginButtonId());

        if (btn_login != null) {
            btn_login.setOnClickListener(v -> handleLogin());
        }
    }

    /**
     * Handles the login process: validates input, retrieves stored credentials,
     * and verifies the password.
     */
    protected void handleLogin() {
        String email = et_email.getText().toString().trim();
        String password = et_password.getText().toString().trim();
        if (!validateInput(email, password)){
            return;
        }

        try {
            String storedPassword = retrieveUserPassword(email);

            if(storedPassword == null){
                onLoginFailure(email);
                return;
            }

            if (verifyPassword(password, storedPassword)) {
                onLoginSuccess(email);
            } else {
                onLoginFailure(email);
            }
        } catch (Exception e) {
            handleLoginError(e);
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
            Toast.makeText(this, "Email and password cannot be empty",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    /**
     * Retrieves the stored password for the given email from SharedPreferences.
     *
     * @param email The user's email address
     * @return The stored password (decrypted if applicable), or null if user not found
     * @throws JSONException If JSON parsing fails
     */
    private String retrieveUserPassword(String email) throws Exception {
        if(!preferencesFileExists()){
            return null;
        }

        SharedPreferences sharedPreferences = getSharedPreferences(credentialsFile, MODE_PRIVATE);
        String json = sharedPreferences.getString(USERS_KEY, "{}");
        JSONObject users = new JSONObject(json);

        if(!users.has(email)) {
            return null;
        }

        JSONObject userObj = users.getJSONObject(email);
        String storedPassword = userObj.getString("password");

        return decryptPassword(storedPassword);
    }

    /**
     * Checks if the SharedPreferences file exists.
     *
     * @return true if the preferences file exists, false otherwise
     */
    private boolean preferencesFileExists() {
        String prefsPath = getApplicationContext().getFilesDir().getParent()
                + SHARED_PREFERENCES_PATH
                + credentialsFile
                + XML_EXTENSION;
        File prefsFile = new File(prefsPath);
        return prefsFile.exists();
    }

    /**
     * Verifies that the input password matches the stored password.
     * Override this method to implement custom verification logic (e.g., hashing).
     *
     * @param inputPassword The password entered by the user
     * @param storedPassword The stored password to verify against
     * @return true if passwords match, false otherwise
     */
    protected boolean verifyPassword(String inputPassword, String storedPassword){
        return storedPassword.equals(inputPassword);
    }

    /**
     * Decrypts the stored password.
     * Override this method to implement custom decryption logic.
     *
     * @param encryptedPassword The encrypted password from storage
     * @return The decrypted password (default: returns password as-is)
     */
    protected String decryptPassword(String encryptedPassword) {
        // Default: return the string as passed
        return encryptedPassword;
    }

    /**
     * Handles errors that occur during the login process.
     *
     * @param e The exception that occurred
     */
    private void handleLoginError(Exception e) {
        e.printStackTrace();
        Toast.makeText(this, "Login error: " + e.getMessage(),
                Toast.LENGTH_SHORT).show();
    }

    /**
     * Called when login succeeds.
     * Subclasses must implement this to define post-login navigation/behavior.
     *
     * @param email The email of the successfully logged-in user
     */
    protected abstract void onLoginSuccess(String email);

    /**
     * Called when login fails due to invalid credentials.
     * Override to customize failure behavior.
     *
     * @param email The email that failed to authenticate
     */
    protected void onLoginFailure(String email) {
        Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show();
    }

    /**
     * Returns the layout resource ID for this activity.
     *
     * @return The layout resource ID
     */
    protected abstract @LayoutRes int getLayoutId();

    /**
     * Returns the resource ID for the email input field.
     *
     * @return The email EditText resource ID
     */
    protected abstract int getEmailFieldId();

    /**
     * Returns the resource ID for the password input field.
     *
     * @return The password EditText resource ID
     */
    protected abstract int getPasswordFieldId();

    /**
     * Returns the resource ID for the login button.
     *
     * @return The login Button resource ID
     */
    protected abstract int getLoginButtonId();
}
