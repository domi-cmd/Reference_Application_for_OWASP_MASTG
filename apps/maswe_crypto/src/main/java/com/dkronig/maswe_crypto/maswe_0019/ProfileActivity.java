package com.dkronig.maswe_crypto.maswe_0019;

import android.os.Bundle;

import com.dkronig.common.BaseActivityTemplate;
import com.dkronig.maswe_crypto.R;

/**
 * Profile Activity for MASWE-0019
 */
public class ProfileActivity extends BaseActivityTemplate {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_template);

    }

    // Set name for action bar handled in BaseActivityTemplate
    @Override
    protected String getScreenTitle() {
        return "Profile";
    }
}