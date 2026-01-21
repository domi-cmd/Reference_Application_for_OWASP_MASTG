package com.dkronig.masvs_crypto.maswe_0018;

import android.os.Bundle;

import java.util.Map;

import com.dkronig.common.BaseActivityTemplate;
import com.dkronig.masvs_crypto.R;

/**
 * Main Activity of MASWE-0018
 *
 * Features:
 *  - Generates a new encryption key if none exists yet. Used for encryption and decryption in
 *  - login and register activity.
 *  - Buttons to navigate to both Register and Login Activity of MASWE-0018.
 */
public class MainActivity0018 extends BaseActivityTemplate {
    private static final String SCREEN_TITLE = "MASWE_0018";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_template);

        try {
            EncryptionHandler.generateKey();
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