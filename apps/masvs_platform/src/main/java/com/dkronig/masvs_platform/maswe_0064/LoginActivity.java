package com.dkronig.masvs_platform.maswe_0064;

import android.content.Intent;

import com.dkronig.common.BaseLoginActivity;
import com.dkronig.masvs_platform.R;

/**
 * Login Activity for MASWE-0064
 */
public class LoginActivity extends BaseLoginActivity {
    private static final String CREDENTIALS_FILE_NAME = "maswe_0064_user_credentials";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login_template;
    }

    @Override
    protected int getEmailFieldId() {
        return R.id.et_email;
    }

    @Override
    protected int getPasswordFieldId() {
        return R.id.et_password;
    }

    @Override
    protected int getLoginButtonId() {
        return R.id.btn_login;
    }

    @Override
    protected String getCredentialFileName() {
        return CREDENTIALS_FILE_NAME;
    }

    @Override
    protected void onLoginSuccess(String email) {
        startActivity(new Intent(this, ProfileActivity.class));
        finish();
    }
}
