package com.dkronig.maswe_platform.maswe_0067;

import android.os.Bundle;

import com.dkronig.common.BaseActivityTemplate;
import com.dkronig.maswe_platform.R;

/**
 * Profile Activity for MASWE-0067
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
