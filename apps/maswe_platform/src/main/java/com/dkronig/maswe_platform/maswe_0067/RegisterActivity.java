package com.dkronig.maswe_platform.maswe_0067;

import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;

import com.dkronig.common.BaseRegisterActivity;
import com.dkronig.maswe_platform.R;

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
    protected String getScreenTitle() {
        return "Register";
    }

    // Define name for encrypted file where user credentials are stored
    @Override
    protected String getCredentialFileName() {
        return "maswe_0067_user_credentials";
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
    protected String encryptPassword(String plaintext){
        try {
            // Encrypt user data
            return encryptionHandler.encryptData(plaintext);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
