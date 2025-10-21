package com.dkronig.maswe_storage;

import android.os.Bundle;
import android.widget.Button;
import com.dkronig.maswe_storage.maswe_0001.MainActivity0001;

import com.dkronig.common.BaseActivityTemplate;


public class StorageMenu extends BaseActivityTemplate {

    // Define UI elements
    private Button maswe_0001_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage_menu);

        // Instantiate UI elements
        maswe_0001_button = findViewById(R.id.maswe_0001_button);

        addListener(maswe_0001_button, MainActivity0001.class);
    }


    // Set name for action bar handled in BaseActivityTemplate
    @Override
    protected String getScreenTitle() {
        return "MASWE Storage";
    }
}