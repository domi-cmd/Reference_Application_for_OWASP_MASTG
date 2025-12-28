package com.dkronig.maswe_storage.maswe_0002;

import android.os.Bundle;

import java.util.Map;

import com.dkronig.common.BaseActivityTemplate;
import com.dkronig.maswe_storage.R;

/**
 * Main Activity of MASWE-0002
 *
 * Features:
 *  - Buttons to navigate to both Register and Login Activity of MASWE-0002.
 */
public class MainActivity0002 extends BaseActivityTemplate {
    private static final String SCREEN_TITLE = "MASWE_0002";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_template);

        bindButtons(Map.of(
                R.id.btn_login, LoginActivity.class,
                R.id.btn_register, RegisterActivity.class));
    }

    @Override
    protected String getScreenTitle() {
        return SCREEN_TITLE;
    }
}