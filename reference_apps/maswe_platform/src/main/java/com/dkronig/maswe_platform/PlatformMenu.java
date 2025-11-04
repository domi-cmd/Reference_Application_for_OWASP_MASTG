package com.dkronig.maswe_platform;

import android.os.Bundle;

import com.dkronig.maswe_platform.maswe_0064.MainActivity0064;

import com.dkronig.common.BaseActivityTemplate;
import java.util.Map;


public class PlatformMenu extends BaseActivityTemplate {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_platform_menu);

        // Map UI buttons, handled in BaseActivityTemplate
        bindButtons(Map.of(
                R.id.maswe_0064_button, MainActivity0064.class
                // More buttons as more vulnerabilities are implemented
        ));
    }


    // Set name for action bar handled in BaseActivityTemplate
    @Override
    protected String getScreenTitle() {
        return "MASWE Platform";
    }
}