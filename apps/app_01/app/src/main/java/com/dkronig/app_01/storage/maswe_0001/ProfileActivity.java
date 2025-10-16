package com.dkronig.app_01.storage.maswe_0001;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.content.Intent;

import com.dkronig.app_01.R;

public class ProfileActivity extends AppCompatActivity {

    // Define UI elements
    private Button main_menu_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        main_menu_button = findViewById(R.id.main_menu_button);

        // Add listeners to buttons
        main_menu_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(ProfileActivity.this, MainActivity0001.class);
                startActivity(intent);
            }
        });



    }
}