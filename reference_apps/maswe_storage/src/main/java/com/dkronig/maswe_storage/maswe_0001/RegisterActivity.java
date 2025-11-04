package com.dkronig.maswe_storage.maswe_0001;

import android.os.Bundle;
import com.dkronig.common.BaseRegisterActivity;
import com.dkronig.maswe_storage.R;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import android.util.Log;

public class RegisterActivity extends BaseRegisterActivity {

    private static final String TAG = "[REGISTER ACTIVITY]";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected String getScreenTitle() {
        return "Register";
    }

    // Provide IDs for BaseRegisterActivity to find UI elements
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
        return R.id.register_button;
    }

    // Write sensitive user data so system logs upon registration
    @Override
    protected void onRegister(String email, String password) {
        userDataToSystemLogs(email, password);
        userDataToAppLogs(email, password);
    }

    private void userDataToSystemLogs(String user_email, String user_password){
        // Log user credentials to system logs (unsafe) (System Logs)
        Log.d(TAG, "New User registered");
        Log.d(TAG, "User E-Mail: "+ user_email);
        Log.d(TAG, "User Password: " + user_password);
    }

    private void userDataToAppLogs(String user_email, String user_password){
        // Logging sensitive data to a file in app's data directory (App Logs)
        try {
            File logFile = new File(getFilesDir(), "maswe_0001_user_credentials.txt");
            FileWriter writer = new FileWriter(logFile, true);
            writer.append("Login - Username: " + user_email + ", Password: " + user_password + "\n");
            writer.close();
            Log.d(TAG, "Logged credentials to app logs");
        } catch (IOException e) {
            // System log incase the app logging did not work
            Log.e(TAG, "Error writing to log file: " + e.getMessage());
        }
    }
}
