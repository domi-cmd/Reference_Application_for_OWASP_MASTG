package com.dkronig.maswe_crypto.maswe_0009;

import android.content.SharedPreferences;
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
        return R.layout.activity_register;
    }

    @Override
    protected String getScreenTitle() {
        return "Register";
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
    protected void userDataToSharedPreferences(String email, String password){
        // Encrypt user data
        String encrypted_email;
        String encrypted_password;
        try {
            // Encrypt user data
            encrypted_email = encryptionHandler.encryptDataDES(email);
            encrypted_password = encryptionHandler.encryptDataDES(password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Save encrypted user data to shared preferences
        SharedPreferences sharedPrefs = getSharedPreferences("my_app_prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString("user_email", encrypted_email);
        editor.putString("user_password", encrypted_password);
        editor.apply();
    }
}
