package com.dkronig.masvs_crypto.maswe_0011;

import android.os.Bundle;

import com.dkronig.common.BaseActivityTemplate;
import com.dkronig.masvs_crypto.R;

import java.util.Map;

/**
 * Main Activity of MASWE-0011
 *
 * Features:
 *  - Generates a new encryption key if none exists yet. Used for encryption and decryption in
 *  - login and register activity.
 *  - Buttons to navigate to both Register and Login Activity of MASWE-0011.
 */
public class MainActivity0011 extends BaseActivityTemplate {
    private static final String SCREEN_TITLE = "MASWE_0011";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_template);

        try {
            EncryptionHandler.loadBksKeystore(this);
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