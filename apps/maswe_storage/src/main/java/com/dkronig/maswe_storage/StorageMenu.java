package com.dkronig.maswe_storage;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.dkronig.common.BaseActivityTemplate;
import com.dkronig.maswe_storage.maswe_0001.MainActivity0001;
import com.dkronig.maswe_storage.maswe_0002.MainActivity0002;
import com.dkronig.maswe_storage.maswe_0006.MainActivity0006;
import com.dkronig.maswe_storage.maswe_0007.MainActivity0007;
import java.util.HashMap;
import java.util.Map;

/**
 * Starting activity of the maswe storage app, displays all implemented maswe storage
 * vulnerabilities in the form of buttons, which lead to the designated vulnerability.
 */
public class StorageMenu extends BaseActivityTemplate {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage_menu);

        initializeButtons();
    }

    /**
     * Initializes and binds navigation buttons to their respective activities.
     */
    private void initializeButtons(){
        Map<Integer, Class<? extends AppCompatActivity>> buttonMap = new HashMap<>();

        buttonMap.put(R.id.maswe_0001_button, MainActivity0001.class);
        buttonMap.put(R.id.maswe_0002_button, MainActivity0002.class);
        buttonMap.put(R.id.maswe_0006_button, MainActivity0006.class);
        buttonMap.put(R.id.maswe_0007_button, MainActivity0007.class);
        // More buttons as more vulnerabilities are implemented

        bindButtons(buttonMap);
    }

    // Set name for action bar handled in BaseActivityTemplate
    @Override
    protected String getScreenTitle() {
        return "MASWE Storage";
    }
}