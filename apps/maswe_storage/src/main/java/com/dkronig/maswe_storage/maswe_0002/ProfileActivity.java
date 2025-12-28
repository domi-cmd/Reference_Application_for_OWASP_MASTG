package com.dkronig.maswe_storage.maswe_0002;

import android.os.Bundle;

import com.dkronig.common.BaseActivityTemplate;
import com.dkronig.maswe_storage.R;

/**
 * Profile Activity for MASWE-0002
 */
public class ProfileActivity extends BaseActivityTemplate {
    private final static String SCREEN_TITLE = "Profile Page";
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