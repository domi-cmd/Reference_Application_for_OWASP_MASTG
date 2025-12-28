package com.dkronig.maswe_storage.maswe_0001;

import android.util.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.dkronig.common.BaseRegisterActivity;
import com.dkronig.maswe_storage.R;

/**
 * Register Activity for MASWE-0001
 */
public class RegisterActivity extends BaseRegisterActivity {
    private static final String SCREEN_TITLE = "Register Page";
    private static final String CREDENTIALS_FILE_NAME = "maswe_0001_user_credentials";
    private static final String LOG_TAG = "[REGISTER ACTIVITY]";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register_template;
    }

    @Override
    protected int getEmailFieldId() {
        return R.id.et_email;
    }

    @Override
    protected int getPasswordFieldId() {
        return R.id.et_password;
    }

    @Override
    protected int getRegisterButtonId() {
        return R.id.btn_register;
    }

    @Override
    protected String getScreenTitle() {
        return SCREEN_TITLE;
    }

    @Override
    protected String getCredentialFileName() {
        return CREDENTIALS_FILE_NAME;
    }


    /**
     * Called after successful user registration.
     * Writes user data to system and app logs upon registration
     *
     * @param email The registered email address
     * @param password The password
     */
    @Override
    protected void onRegister(String email, String password) {
        userDataToSystemLogs(email, password);
        userDataToAppLogs(email, password);
    }

    /**
     * Logs user credentials to Android system logs Logcat.
     *
     * @param email The user's email address
     * @param password The user's plaintext password
     */
    private void userDataToSystemLogs(String email, String password){
        Log.d(LOG_TAG, "New User registered");
        Log.d(LOG_TAG, "User E-Mail: "+ email);
        Log.d(LOG_TAG, "User Password: " + password);
    }

    /**
     * Writes user credentials to a log file in app's private directory (App logs).
     *
     * @param email The user's email address
     * @param password The user's password
     */
    private void userDataToAppLogs(String email, String password){
        try {
            File logFile = new File(getFilesDir(), CREDENTIALS_FILE_NAME + ".txt");
            FileWriter writer = new FileWriter(logFile, true);
            writer.append("Login - Username: ")
                    .append(email)
                    .append(", Password: ")
                    .append(password)
                    .append("\n");
            writer.close();
            Log.d(LOG_TAG, "Logged credentials to app logs");
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error writing to log file: " + e.getMessage());
        }
    }
}
