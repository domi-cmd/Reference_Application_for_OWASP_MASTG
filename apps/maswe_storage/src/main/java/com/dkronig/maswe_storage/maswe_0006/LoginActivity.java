package com.dkronig.maswe_storage.maswe_0006;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.dkronig.common.BaseLoginActivity;
import com.dkronig.maswe_storage.R;

public class LoginActivity extends BaseLoginActivity {

    private EncryptionHandler encryptionHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        encryptionHandler = new EncryptionHandler();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
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

    @Override
    protected void loginUser(){
        String email = et_email.getText().toString().trim();
        String password = et_password.getText().toString().trim();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Email and password cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        SharedPreferences prefs = getSharedPreferences("my_app_prefs", MODE_PRIVATE);
        String storedEmail = prefs.getString("user_email", null);
        String storedPassword = prefs.getString("user_password", null);

        String decrypted_email;
        String decrypted_password;

        // Decrypt password and email
        try {
            decrypted_email = encryptionHandler.decryptData(storedEmail);
            decrypted_password = encryptionHandler.decryptData(storedPassword);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (email.equals(decrypted_email) && password.equals(decrypted_password)) {
            onLoginSuccess(email);
        } else {
            onLoginFailure(email);
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
        super.onLoginFailure(email); // default Toast
    }
}
