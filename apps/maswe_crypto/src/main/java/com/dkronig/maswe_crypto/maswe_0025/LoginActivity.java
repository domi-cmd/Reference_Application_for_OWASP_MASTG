package com.dkronig.maswe_crypto.maswe_0025;

import android.content.Intent;
import android.os.Bundle;

import com.dkronig.common.BaseLoginActivity;
import com.dkronig.maswe_crypto.R;

public class LoginActivity extends BaseLoginActivity {

    private EncryptionHandler encryptionHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            encryptionHandler = new EncryptionHandler();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
        return R.id.login_button;
    }

    @Override
    protected String getScreenTitle() {
        return "Login";
    }

    // Define name for encrypted file where user credentials are stored
    @Override
    protected String getCredentialFileName() {
        return "maswe_0025_user_credentials";
    }

    @Override
    protected String decryptPassword(String encryptedText){
        // Decrypt password and email
        try {
            return encryptionHandler.decryptData(encryptedText);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void onLoginSuccess(String email) {
        // Navigate to profile or main screen
        startActivity(new Intent(this, ProfileActivity.class));
        finish();
    }

    @Override
    protected void onLoginFailure(String email) {
        // default Toast
        super.onLoginFailure(email);
    }
}
