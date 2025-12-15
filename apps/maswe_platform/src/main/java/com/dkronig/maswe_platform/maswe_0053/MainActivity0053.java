package com.dkronig.maswe_platform.maswe_0053;

import android.os.Bundle;

import com.dkronig.common.BaseActivityTemplate;
import com.dkronig.maswe_platform.R;

import java.util.Map;

public class MainActivity0053 extends BaseActivityTemplate {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_template);

        // Generate secret key used for encryption and decryption in login and register activity
        try {
            EncryptionHandler.generateKey();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Map UI buttons, handled in BaseActivityTemplate
        bindButtons(Map.of(
                R.id.login_button, LoginActivity.class,
                R.id.register_button, RegisterActivity.class
        ));
    }

    // Set name for action bar handled in BaseActivityTemplate
    @Override
    protected String getScreenTitle() {
        return "MASWE_0053";
    }
}