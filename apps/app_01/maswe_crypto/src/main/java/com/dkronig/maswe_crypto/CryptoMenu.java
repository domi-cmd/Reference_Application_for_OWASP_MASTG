package com.dkronig.maswe_crypto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;

import com.dkronig.common.BaseActivityTemplate;

public class CryptoMenu extends BaseActivityTemplate {

    // Define UI elements
    private Button placeholder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crypto_menu);

        // Instantiate UI elements
        placeholder = findViewById(R.id.placeholder_button);

        //addListener(placeholder, MainActivity0001.class);
    }


    // Handles "Go Back" navigation functionality
    @Override
    protected String getScreenTitle() {
        return "MASWE Crypto";  // Optional custom title
    }
}