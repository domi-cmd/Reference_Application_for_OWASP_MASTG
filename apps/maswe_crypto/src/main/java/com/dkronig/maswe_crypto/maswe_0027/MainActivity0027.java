package com.dkronig.maswe_crypto.maswe_0027;

import android.os.Bundle;

import java.util.Map;

import com.dkronig.common.BaseActivityTemplate;
import com.dkronig.maswe_crypto.R;

/**
 * Main Activity of MASWE-0027
 *
 * Features:
 *  - Generates a new encryption key if none exists yet. Used for encryption and decryption in
 *  - login and register activity.
 *  - Buttons to navigate to both Register and Login Activity of MASWE-0027.
 */
public class MainActivity0027 extends BaseActivityTemplate {
    private static final String SCREEN_TITLE = "MASWE_0027";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_template);

        try {
            EncryptionHandler.generateAESKey(this);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        bindButtons(Map.of(
                R.id.btn_login, LoginActivity.class,
                R.id.btn_register, RegisterActivity.class));
    }

    @Override
    protected String getScreenTitle() {
        return SCREEN_TITLE;
    }
}