package com.dkronig.masvs_platform.maswe_0053;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;

import com.dkronig.common.BaseLoginActivity;
import com.dkronig.masvs_platform.R;

/**
 * Login Activity for MASWE-0053
 *
 * Features:
 *  - Uses a custom encryption handler to decrypt passwords before logging in.
 */
public class LoginActivity extends BaseLoginActivity {
    private static final String CREDENTIALS_FILE_NAME = "maswe_0053_user_credentials";

    private EncryptionHandler encryptionHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        removeObfuscation();
        
        try {
            encryptionHandler = new EncryptionHandler();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Removes any obfuscation of the email and password fields.
     *
     * Removes obfuscation, enables auto complete and auto correct for password edit text.
     * Removes obfuscation, enables auto complete and auto correct for email edit text.
     * Enables copy and paste functionality for the password edit text.
     */
    private void removeObfuscation(){
        EditText email_field = findViewById(R.id.et_email);
        EditText password_field = findViewById(R.id.et_password);

        password_field.setInputType(InputType.TYPE_CLASS_TEXT |
                InputType.TYPE_TEXT_FLAG_AUTO_COMPLETE |
                InputType.TYPE_TEXT_FLAG_AUTO_CORRECT);

        email_field.setInputType(InputType.TYPE_TEXT_FLAG_AUTO_COMPLETE |
                InputType.TYPE_TEXT_FLAG_AUTO_CORRECT);

        password_field.setCustomSelectionActionModeCallback(null);
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
