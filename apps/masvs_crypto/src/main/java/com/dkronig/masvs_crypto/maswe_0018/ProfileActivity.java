package com.dkronig.masvs_crypto.maswe_0018;

import android.os.Bundle;

import com.dkronig.common.BaseActivityTemplate;
import com.dkronig.masvs_crypto.R;

/**
 * Profile Activity for MASWE-0018
 */
public class ProfileActivity extends BaseActivityTemplate {
    private static final String SCREEN_TITLE = "Profile Page";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_template);
    }

    @Override
    protected String getScreenTitle() {
        return SCREEN_TITLE;
    }
}
