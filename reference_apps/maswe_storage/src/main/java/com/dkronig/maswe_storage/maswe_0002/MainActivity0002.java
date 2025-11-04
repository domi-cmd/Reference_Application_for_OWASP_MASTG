package com.dkronig.maswe_storage.maswe_0002;

import android.os.Bundle;
import com.dkronig.common.BaseActivityTemplate;
import com.dkronig.maswe_storage.R;
import java.util.Map;

public class MainActivity0002 extends BaseActivityTemplate {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_storage);

        // Map UI buttons, handled in BaseActivityTemplate
        bindButtons(Map.of(
                R.id.login_button, LoginActivity.class,
                R.id.register_button, RegisterActivity.class
        ));
    }

    // Set name for action bar handled in BaseActivityTemplate
    @Override
    protected String getScreenTitle() {
        return "MASWE_0002";
    }
}