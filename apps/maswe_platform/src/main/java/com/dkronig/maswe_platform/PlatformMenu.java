package com.dkronig.maswe_platform;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.dkronig.maswe_platform.maswe_0055.MainActivity0055;
import com.dkronig.maswe_platform.maswe_0064.MainActivity0064;
import com.dkronig.common.BaseActivityTemplate;

import java.util.HashMap;
import java.util.Map;

public class PlatformMenu extends BaseActivityTemplate {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_platform_menu);

        // Map UI buttons, handled in BaseActivityTemplate
        Map<Integer, Class<? extends AppCompatActivity>> buttonMap = new HashMap<>();

        buttonMap.put(R.id.maswe_0055_button, MainActivity0055.class);
        buttonMap.put(R.id.maswe_0064_button, MainActivity0064.class);
        // More buttons as more vulnerabilities are implemented

        bindButtons(buttonMap);
    }

    // Set name for action bar handled in BaseActivityTemplate
    @Override
    protected String getScreenTitle() {
        return "MASWE Platform";
    }
}