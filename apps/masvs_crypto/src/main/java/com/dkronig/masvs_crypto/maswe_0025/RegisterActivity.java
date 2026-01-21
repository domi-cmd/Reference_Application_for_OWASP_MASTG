package com.dkronig.masvs_crypto.maswe_0025;

import android.os.Bundle;

import com.dkronig.common.BaseRegisterActivity;
import com.dkronig.masvs_crypto.R;

/**
 * Register Activity for MASWE-0025
 *
 * Features:
 *  - Uses a custom encryption handler for decrypting user passwords.
 */
public class RegisterActivity extends BaseRegisterActivity {
    private static final String CREDENTIALS_FILE_NAME = "maswe_0025_user_credentials";

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
    protected String getCredentialFileName() {
        return CREDENTIALS_FILE_NAME;
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
        return R.id.btn_register;
    }

    @Override
    protected String encryptPassword(String plaintext){
        try {
            return encryptionHandler.encryptData(plaintext);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
