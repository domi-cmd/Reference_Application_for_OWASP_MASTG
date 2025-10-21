package com.dkronig.maswe_storage.maswe_0001;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.util.Log;
import android.widget.Toast;
import android.text.TextUtils;

import com.dkronig.maswe_storage.R;

public class LoginActivity extends AppCompatActivity {

    // Define UI elements
    private Button main_menu_button;
    private EditText et_email, et_password;
    private Button login_button;
    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        main_menu_button = findViewById(R.id.main_menu_button);
        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        login_button = findViewById(R.id.login_button);

        // Add listeners to buttons
        main_menu_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(LoginActivity.this, MainActivity0001.class);
                startActivity(intent);
            }
        });

        // Add a listener to the register button
        login_button.setOnClickListener(v -> loginUser());
    }

    private void loginUser() {
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
            Log.d(TAG, "Login successful for email=" + email);
            Intent intent = new Intent(this, ProfileActivity.class);
            startActivity(intent);
            finish();
        } else {
            Log.w(TAG, "Login failed for email=" + email);
            Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show();
        }
    }
}