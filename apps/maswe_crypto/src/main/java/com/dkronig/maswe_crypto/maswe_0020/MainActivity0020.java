package com.dkronig.maswe_crypto.maswe_0020;

import android.os.Bundle;
import com.dkronig.common.BaseActivityTemplate;
import com.dkronig.maswe_crypto.R;
import java.util.Map;

public class MainActivity0020 extends BaseActivityTemplate {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_template);

        // Map UI buttons, handled in BaseActivityTemplate
        bindButtons(Map.of(
                R.id.login_button, LoginActivity.class,
                R.id.register_button, RegisterActivity.class
        ));
    }

    // Set name for action bar handled in BaseActivityTemplate
    @Override
    protected String getScreenTitle() {
        return "MASWE_0020";
    }
}