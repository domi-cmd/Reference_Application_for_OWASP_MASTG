package com.dkronig.maswe_crypto;

import android.os.Bundle;
import com.dkronig.common.BaseActivityTemplate;
import com.dkronig.maswe_crypto.maswe_0009.MainActivity0009;
import com.dkronig.maswe_crypto.maswe_0014.MainActivity0014;
import com.dkronig.maswe_crypto.maswe_0020.MainActivity0020;
import com.dkronig.maswe_crypto.maswe_0021.MainActivity0021;
import com.dkronig.maswe_crypto.maswe_0027.MainActivity0027;
import java.util.Map;

public class CryptoMenu extends BaseActivityTemplate {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crypto_menu);

        // Map UI buttons, handled in BaseActivityTemplate
        bindButtons(Map.of(
                R.id.maswe_0009_button, MainActivity0009.class,
                R.id.maswe_0014_button, MainActivity0014.class,
                R.id.maswe_0020_button, MainActivity0020.class,
                R.id.maswe_0021_button, MainActivity0021.class,
                R.id.maswe_0027_button, MainActivity0027.class
                // More buttons as more tests are implemented
        ));
    }

    // Set name for action bar handled in BaseActivityTemplate
    @Override
    protected String getScreenTitle() {
        return "MASWE Crypto";
    }
}