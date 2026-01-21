package com.dkronig.masvs_storage.maswe_0006;

import android.os.Bundle;

import com.dkronig.common.BaseRegisterActivity;
import com.dkronig.masvs_storage.R;

/**
 * Register Activity for MASWE-0006
 */
public class RegisterActivity extends BaseRegisterActivity {
    private static final String SCREEN_TITLE = "Register Page";
    private static final String CREDENTIALS_FILE_NAME = "maswe_0006_user_credentials";
    private EncryptionHandler encryptionHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        encryptionHandler = new EncryptionHandler();
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
        return R.id.btn_register;
    }

    @Override
    protected String getScreenTitle() {
        return SCREEN_TITLE;
    }

    @Override
    protected String getCredentialFileName() {
        return CREDENTIALS_FILE_NAME;
    }

    /**
     * Uses encryption handler to encrypt the password of the new registered user.
     *
     * @param password The plaintext password
     * @return The encrypted password if successful, an RuntimeException otherwise.
     */
    @Override
    protected String encryptPassword(String password){
        try {
            return encryptionHandler.encryptData(password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
