package com.dkronig.maswe_crypto.maswe_0010;

import android.os.Bundle;

import com.dkronig.common.BaseRegisterActivity;
import com.dkronig.maswe_crypto.R;

public class RegisterActivity extends BaseRegisterActivity {
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
        return R.layout.activity_register_template;
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
    protected int getRegisterButtonId() {
        return R.id.register_button;
    }

    @Override
    protected String getScreenTitle() {
        return "Register";
    }

    // Define name for encrypted file where user credentials are stored
    @Override
    protected String getCredentialFileName() {
        return "maswe_0027_user_credentials";
    }

    @Override
    protected String encrypt(String plaintext){
        // Encrypt user data
        try {
            return encryptionHandler.encryptData(plaintext);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
