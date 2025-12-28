package com.dkronig.maswe_storage.maswe_0006;

import android.os.Bundle;

import java.util.Map;

import com.dkronig.common.BaseActivityTemplate;
import com.dkronig.maswe_storage.R;

/**
 * Main Activity of MASWE-0006
 *
 * Features:
 *  - Buttons to navigate to both Register and Login Activity of MASWE-0006.
 */
public class MainActivity0006 extends BaseActivityTemplate {
    private static final String SCREEN_TITLE = "MASWE_0006";

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