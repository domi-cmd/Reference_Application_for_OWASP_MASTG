package com.dkronig.maswe_platform.maswe_0064;

import android.os.Bundle;

import com.dkronig.common.BaseActivityTemplate;
import com.dkronig.maswe_platform.R;
import com.dkronig.maswe_platform.maswe_0064.LoginActivity;

import java.util.Map;

public class MainActivity0064 extends BaseActivityTemplate {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_0064);

        // Map UI buttons, handled in BaseActivityTemplate
        bindButtons(Map.of(
                R.id.login_button, LoginActivity.class,
                R.id.register_button, RegisterActivity.class
        ));
    }

    // Set name for action bar handled in BaseActivityTemplate
    @Override
    protected String getScreenTitle() {
        return "MASWE_0002";
    }
}