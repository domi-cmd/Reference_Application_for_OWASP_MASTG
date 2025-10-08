package com.dkronig.app_01;

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
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        settings_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });
    }
}