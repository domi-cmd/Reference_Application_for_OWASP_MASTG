package com.dkronig.masvs_crypto.maswe_0026;

import android.content.Intent;
import android.os.Bundle;

import com.dkronig.common.BaseLoginActivity;
import com.dkronig.masvs_crypto.R;

/**
 * Login Activity for MASWE-0026
 *
 * Features:
 *  - Uses a custom encryption handler to decrypt passwords before logging in.
 */
public class LoginActivity extends BaseLoginActivity {
    private static final String CREDENTIALS_FILE_NAME = "maswe_0026_user_credentials";

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
    protected String decryptPassword(String encryptedText){
        try {
            return encryptionHandler.decryptData(encryptedText);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void onLoginSuccess(String email) {
        startActivity(new Intent(this, ProfileActivity.class));
        finish();
    }
}
