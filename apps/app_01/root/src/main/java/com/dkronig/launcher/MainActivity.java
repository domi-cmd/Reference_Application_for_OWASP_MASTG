package com.dkronig.launcher;

import android.app.Activity;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.content.Intent;

import com.dkronig.common.BaseActivityTemplate;
import com.dkronig.maswe_storage.StorageMenu;
import com.dkronig.maswe_crypto.CryptoMenu;

public class MainActivity extends BaseActivityTemplate {

    // Define UI elements
    private Button storage_button, crypto_button, auth_button, network_button,
            platform_button, code_button, resilience_button, privacy_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instantiate UI elements
        storage_button = findViewById(R.id.storage_button);
        crypto_button = findViewById(R.id.crypto_button);
        //auth_button = findViewById(R.id.auth_button);
        //network_button = findViewById(R.id.network_button);
        //platform_button = findViewById(R.id.platform_button);
        //code_button = findViewById(R.id.code_button);
        //resilience_button = findViewById(R.id.resilience_button);
        //privacy_button = findViewById(R.id.privacy_button);

        // Add listeners to menu buttons
        addListener(storage_button, StorageMenu.class);
        addListener(crypto_button, CryptoMenu.class);
    }

    // Set name for action bar handled in BaseActivityTemplate
    @Override
    protected String getScreenTitle() {
        return "Choose MASWE Category";
    }
}