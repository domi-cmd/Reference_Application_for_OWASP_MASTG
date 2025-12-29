package com.dkronig.maswe_crypto.maswe_0015;

import android.os.Bundle;

import com.dkronig.common.BaseActivityTemplate;
import com.dkronig.maswe_crypto.R;

import java.util.Map;

public class MainActivity0015 extends BaseActivityTemplate {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_template);

        // Generate secret key used for encryption and decryption in login and register activity
        try {
            EncryptionHandler.loadBksKeystore(this);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Map UI buttons, handled in BaseActivityTemplate
        bindButtons(Map.of(
                R.id.btn_login, LoginActivity.class,
                R.id.btn_register, RegisterActivity.class
        ));
    }

    // Set name for action bar handled in BaseActivityTemplate
    @Override
    protected String getScreenTitle() {
        return "MASWE_0015";
    }
}