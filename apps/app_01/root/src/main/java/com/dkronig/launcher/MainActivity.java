package com.dkronig.launcher;

import android.app.Activity;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.content.Intent;

import com.dkronig.common.BaseActivityTemplate;
import com.dkronig.maswe_storage.StorageMenu;
import com.dkronig.maswe_crypto.CryptoMenu;
import java.util.Map;

public class MainActivity extends BaseActivityTemplate {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Map UI buttons, handled in BaseActivityTemplate
        bindButtons(Map.of(
                R.id.storage_button, StorageMenu.class,
                R.id.crypto_button, CryptoMenu.class
                //R.id.auth_button, AuthMenu.class,
                //R.id.network_button = NetworkMenu.class;
                //R.id.platform_button = PlatformMenu.class;
                //R.id.code_button = CodeMenu.class;
                //R.id.resilience_button = ResilienceMenu.class;
                //R.id.privacy_button = PrivacyMenu.class;
        ));

    }

    // Set name for action bar handled in BaseActivityTemplate
    @Override
    protected String getScreenTitle() {
        return "Choose MASWE Category";
    }
}