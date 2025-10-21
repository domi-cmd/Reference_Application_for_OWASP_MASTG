package com.dkronig.maswe_storage.maswe_0001;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import android.os.Bundle;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import com.dkronig.common.BaseActivityTemplate;
import com.dkronig.maswe_storage.R;

public class RegisterActivity extends BaseActivityTemplate {

    // Define UI elements
    private EditText et_email, et_password;
    private Button register_button;
    private static final String TAG = "[REGISTER ACTIVITY]";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the layout of the activity
        setContentView(R.layout.activity_register);

        // Instantiate the UI elements
        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        register_button = findViewById(R.id.register_button);

        // Add a listener to the register button
        register_button.setOnClickListener(v -> registerUser());
    }

    private void registerUser(){
        // Extract and store users email and password
        String user_email = et_email.getText().toString().trim();
        String user_password = et_password.getText().toString().trim();

        // Add user credentials to apps shared preferences
        userDataToSharedPreferences(user_email, user_password);
        // Log user credentials to system logs (unsafe) (System Logs)
        userDataToSystemLogs(user_email, user_password);
        // Logging sensitive data to a file in app's data directory (App Logs)
        userDataToAppLogs(user_email, user_password);
    }

    private void userDataToSharedPreferences(String user_email, String user_password){
        SharedPreferences shared_prefs = getSharedPreferences("my_app_prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = shared_prefs.edit();
        // Store plaintext strings (unsafe)
        editor.putString("user_email", user_email);
        editor.putString("user_password", user_password);
        editor.apply();
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
            File logFile = new File(getFilesDir(), "login_logs.txt");
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