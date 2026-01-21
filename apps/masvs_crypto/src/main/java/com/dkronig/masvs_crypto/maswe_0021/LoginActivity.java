package com.dkronig.masvs_crypto.maswe_0021;

import android.content.Intent;
import android.os.Bundle;

import com.dkronig.common.BaseLoginActivity;
import com.dkronig.masvs_crypto.R;

/**
 * Login Activity for MASWE-0021
 *
 * Features:
 *  - Uses a custom encryption handler to decrypt passwords before logging in.
 */
public class LoginActivity extends BaseLoginActivity {
    private static final String CREDENTIALS_FILE_NAME = "maswe_0021_user_credentials";

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
        return R.id.btn_login;
    }

    @Override
    protected String getCredentialFileName() {
        return CREDENTIALS_FILE_NAME;
    }

    @Override
    protected boolean verifyPassword(String inputPassword, String storedPassword){
        // Hash the entered password to compare it to stored hash
        String hashedInputPassword;
        try {
            hashedInputPassword = encryptionHandler.hashData(inputPassword);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return storedPassword.equals(hashedInputPassword);
    }

    @Override
    protected void onLoginSuccess(String email) {
        startActivity(new Intent(this, ProfileActivity.class));
        finish();
    }
}
