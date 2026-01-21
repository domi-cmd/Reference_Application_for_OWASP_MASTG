package com.dkronig.masvs_platform;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

import com.dkronig.common.BaseActivityTemplate;
import com.dkronig.masvs_platform.maswe_0053.MainActivity0053;
import com.dkronig.masvs_platform.maswe_0055.MainActivity0055;
import com.dkronig.masvs_platform.maswe_0064.MainActivity0064;
import com.dkronig.masvs_platform.maswe_0067.MainActivity0067;

/**
 * Starting activity of the MASVS platform app, displays all implemented maswe platform
 * vulnerabilities in the form of buttons, which lead to the designated vulnerability.
 */
public class PlatformMenu extends BaseActivityTemplate {
    private static final String SCREEN_TITLE = "MASVS Platform";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_platform_menu);

        initializeButtons();
    }

    /**
     * Initializes and binds navigation buttons to their respective activities.
     */
    private void initializeButtons(){
        Map<Integer, Class<? extends AppCompatActivity>> buttonMap = new HashMap<>();

        buttonMap.put(R.id.btn_maswe_0053, MainActivity0053.class);
        buttonMap.put(R.id.btn_maswe_0055, MainActivity0055.class);
        buttonMap.put(R.id.btn_maswe_0064, MainActivity0064.class);
        buttonMap.put(R.id.btn_maswe_0067, MainActivity0067.class);
        // More buttons as more vulnerabilities are implemented

        bindButtons(buttonMap);
    }

    @Override
    protected String getScreenTitle() {
        return SCREEN_TITLE;
    }
}