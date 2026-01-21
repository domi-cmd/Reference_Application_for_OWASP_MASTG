package com.dkronig.masvs_storage.maswe_0007;

import android.os.Bundle;

import com.dkronig.common.BaseActivityTemplate;
import com.dkronig.masvs_storage.R;

/**
 * Profile Activity for MASWE-0007
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