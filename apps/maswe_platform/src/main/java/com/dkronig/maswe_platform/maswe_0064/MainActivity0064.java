package com.dkronig.maswe_platform.maswe_0064;

import android.os.Bundle;
import com.dkronig.common.BaseActivityTemplate;
import com.dkronig.maswe_platform.R;
import java.util.Map;

public class MainActivity0064 extends BaseActivityTemplate {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_template);

        // Map UI buttons, handled in BaseActivityTemplate
        bindButtons(Map.of(
                R.id.btn_login, LoginActivity.class,
                R.id.btn_register, RegisterActivity.class
        ));
    }

    // Set name for action bar handled in BaseActivityTemplate
    @Override
    protected String getScreenTitle() {
        return "MASWE_0064";
    }
}