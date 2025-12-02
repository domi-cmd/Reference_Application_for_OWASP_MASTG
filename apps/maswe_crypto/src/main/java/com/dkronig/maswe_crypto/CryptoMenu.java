package com.dkronig.maswe_crypto;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.dkronig.common.BaseActivityTemplate;
import com.dkronig.maswe_crypto.maswe_0009.MainActivity0009;
import com.dkronig.maswe_crypto.maswe_0011.MainActivity0011;
import com.dkronig.maswe_crypto.maswe_0012.MainActivity0012;
import com.dkronig.maswe_crypto.maswe_0014.MainActivity0014;
import com.dkronig.maswe_crypto.maswe_0015.MainActivity0015;
import com.dkronig.maswe_crypto.maswe_0019.MainActivity0019;
import com.dkronig.maswe_crypto.maswe_0020.MainActivity0020;
import com.dkronig.maswe_crypto.maswe_0021.MainActivity0021;
import com.dkronig.maswe_crypto.maswe_0022.MainActivity0022;
import com.dkronig.maswe_crypto.maswe_0023.MainActivity0023;
import com.dkronig.maswe_crypto.maswe_0024.MainActivity0024;
import com.dkronig.maswe_crypto.maswe_0025.MainActivity0025;
import com.dkronig.maswe_crypto.maswe_0026.MainActivity0026;
import com.dkronig.maswe_crypto.maswe_0027.MainActivity0027;
import java.util.HashMap;
import java.util.Map;

public class CryptoMenu extends BaseActivityTemplate {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crypto_menu);

        // Map UI buttons, handled in BaseActivityTemplate
        Map<Integer, Class<? extends AppCompatActivity>> buttonMap = new HashMap<>();

        buttonMap.put(R.id.maswe_0009_button, MainActivity0009.class);
        buttonMap.put(R.id.maswe_0011_button, MainActivity0011.class);
        buttonMap.put(R.id.maswe_0012_button, MainActivity0012.class);
        buttonMap.put(R.id.maswe_0014_button, MainActivity0014.class);
        buttonMap.put(R.id.maswe_0015_button, MainActivity0015.class);
        buttonMap.put(R.id.maswe_0019_button, MainActivity0019.class);
        buttonMap.put(R.id.maswe_0020_button, MainActivity0020.class);
        buttonMap.put(R.id.maswe_0021_button, MainActivity0021.class);
        buttonMap.put(R.id.maswe_0022_button, MainActivity0022.class);
        buttonMap.put(R.id.maswe_0023_button, MainActivity0023.class);
        buttonMap.put(R.id.maswe_0024_button, MainActivity0024.class);
        buttonMap.put(R.id.maswe_0025_button, MainActivity0025.class);
        buttonMap.put(R.id.maswe_0026_button, MainActivity0026.class);
        buttonMap.put(R.id.maswe_0027_button, MainActivity0027.class);

        bindButtons(buttonMap);

    }

    // Set name for action bar handled in BaseActivityTemplate
    @Override
    protected String getScreenTitle() {
        return "MASWE Crypto";
    }
}