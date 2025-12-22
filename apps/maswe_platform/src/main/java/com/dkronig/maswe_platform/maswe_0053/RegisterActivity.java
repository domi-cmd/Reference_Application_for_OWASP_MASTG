package com.dkronig.maswe_platform.maswe_0053;

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

        EditText email_field = findViewById(R.id.et_email);
        EditText password_field = findViewById(R.id.et_password);

        // Remove password obfuscation, enable auto complete and auto correct
        password_field.setInputType(InputType.TYPE_CLASS_TEXT |
                InputType.TYPE_TEXT_FLAG_AUTO_COMPLETE |
                InputType.TYPE_TEXT_FLAG_AUTO_CORRECT);

        // Same for email
        email_field.setInputType(InputType.TYPE_TEXT_FLAG_AUTO_COMPLETE |
                InputType.TYPE_TEXT_FLAG_AUTO_CORRECT);

        // Enable copy and paste functionality by setting it to null (default)
        password_field.setCustomSelectionActionModeCallback(null);

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
        return "maswe_0053_user_credentials";
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
