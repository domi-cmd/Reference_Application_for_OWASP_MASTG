package com.dkronig.app_01;

import android.app.Activity;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;

import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    // Define UI elements
    private Button login_button;
    private Button register_button;
    private Button settings_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instantiate UI elements
        login_button = findViewById(R.id.login_button);
        register_button = findViewById(R.id.register_button);
        settings_button = findViewById(R.id.settings_button);

        // Add listeners to buttons
        addListener(login_button, LoginActivity.class);
        addListener(register_button, RegisterActivity.class);
        addListener(settings_button, SettingsActivity.class);
    }


    private void addListener(Button button, Class<? extends Activity> targetActivityClass){
        button.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, targetActivityClass);
            startActivity(intent);
        });
    }
}