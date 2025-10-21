package com.dkronig.maswe_storage.maswe_0001;

import android.os.Bundle;
import android.widget.Button;
import com.dkronig.common.BaseActivityTemplate;
import com.dkronig.maswe_storage.R;

public class MainActivity0001 extends BaseActivityTemplate {

    // Define UI elements
    private Button login_button;
    private Button register_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_0001);

        // Instantiate UI elements
        login_button = findViewById(R.id.login_button);
        register_button = findViewById(R.id.register_button);

        // Add listeners to buttons
        addListener(login_button, LoginActivity.class);
        addListener(register_button, RegisterActivity.class);
    }

    // Set name for action bar handled in BaseActivityTemplate
    @Override
    protected String getScreenTitle() {
        return "MASWE_0001";
    }
}