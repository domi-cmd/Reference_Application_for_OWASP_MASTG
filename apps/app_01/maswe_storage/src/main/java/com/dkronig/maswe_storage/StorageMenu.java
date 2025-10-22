package com.dkronig.maswe_storage;

import android.os.Bundle;
import android.widget.Button;
import com.dkronig.maswe_storage.maswe_0001.MainActivity0001;
import com.dkronig.maswe_storage.maswe_0002.MainActivity0002;
import com.dkronig.common.BaseActivityTemplate;
import java.util.Map;


public class StorageMenu extends BaseActivityTemplate {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage_menu);

        // Map UI buttons, handled in BaseActivityTemplate
        bindButtons(Map.of(
                R.id.maswe_0001_button, MainActivity0001.class,
                R.id.maswe_0002_button, MainActivity0002.class
                // More buttons as more tests are implemented
        ));
    }


    // Set name for action bar handled in BaseActivityTemplate
    @Override
    protected String getScreenTitle() {
        return "MASWE Storage";
    }
}