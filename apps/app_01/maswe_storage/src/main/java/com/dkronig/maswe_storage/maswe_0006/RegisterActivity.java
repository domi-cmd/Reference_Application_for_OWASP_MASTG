package com.dkronig.maswe_storage.maswe_0006;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.dkronig.common.BaseRegisterActivity;
import com.dkronig.maswe_storage.R;
import com.dkronig.maswe_storage.maswe_0001.MainActivity0001;
import com.dkronig.maswe_storage.maswe_0002.MainActivity0002;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class RegisterActivity extends BaseRegisterActivity {

    private static final String TAG = "[REGISTER ACTIVITY]";
    private EncryptionHandler encryptionHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        encryptionHandler = new EncryptionHandler();
    }

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

    @Override
    protected void userDataToSharedPreferences(String email, String password){
        // Encrypt user data
        String encrypted_email;
        String encrypted_password;
        try {
            // Encrypt user data
            encrypted_email = encryptionHandler.encryptData(email);
            encrypted_password = encryptionHandler.encryptData(password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        SharedPreferences sharedPrefs = getSharedPreferences("my_app_prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString("user_email", encrypted_email);
        editor.putString("user_password", encrypted_password);
        editor.apply();

        // Save encrypted user data to app sandbox
        userDataToAppLogs(encrypted_email, encrypted_password);
    }

    private void userDataToAppLogs(String user_email, String user_password){
        // Logging sensitive data to a file in app's data directory (App Logs)
        try {
            File logFile = new File(getFilesDir(), "maswe_0006_user_credentials.txt");
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
