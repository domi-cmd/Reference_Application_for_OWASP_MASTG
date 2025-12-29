package com.dkronig.maswe_platform.maswe_0064;

import android.content.Intent;
import com.dkronig.common.BaseLoginActivity;
import com.dkronig.maswe_platform.R;

public class LoginActivity extends BaseLoginActivity {

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
        return "Login";
    }

    // Define name for encrypted file where user credentials are stored
    @Override
    protected String getCredentialFileName() {
        return "maswe_0064_user_credentials";
    }

    @Override
    protected void onLoginSuccess(String email) {
        // Navigate to profile or main screen
        startActivity(new Intent(this, ProfileActivity.class));
        finish();
    }

    @Override
    protected void onLoginFailure(String email) {
        super.onLoginFailure(email); // default Toast
    }
}
