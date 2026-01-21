package com.dkronig.masvs_storage.maswe_0001;

import android.content.Intent;

import com.dkronig.common.BaseLoginActivity;
import com.dkronig.masvs_storage.R;

/**
 * Login Activity for MASWE-0001
 */
public class LoginActivity extends BaseLoginActivity {
    private static final String SCREEN_TITLE = "Login Page";
    private static final String CREDENTIALS_FILE_NAME = "maswe_0001_user_credentials";

    @Override
    protected void onLoginSuccess(String email) {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
        finish();
    }

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
    protected String getScreenTitle() {
        return SCREEN_TITLE;
    }

    @Override
    protected String getCredentialFileName() {
        return CREDENTIALS_FILE_NAME;
    }
}
