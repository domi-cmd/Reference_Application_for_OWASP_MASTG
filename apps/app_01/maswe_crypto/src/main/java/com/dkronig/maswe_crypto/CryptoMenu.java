package com.dkronig.maswe_crypto;

import android.os.Bundle;
import android.widget.Button;
import com.dkronig.common.BaseActivityTemplate;

import java.util.Map;

public class CryptoMenu extends BaseActivityTemplate {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crypto_menu);

        // Map UI buttons, handled in BaseActivityTemplate
        bindButtons(Map.of(
                //R.id.placeholder_button, PlaceholderClass.class,
                // More buttons as more tests are implemented
        ));
    }


    // Set name for action bar handled in BaseActivityTemplate
    @Override
    protected String getScreenTitle() {
        return "MASWE Crypto";
    }
}