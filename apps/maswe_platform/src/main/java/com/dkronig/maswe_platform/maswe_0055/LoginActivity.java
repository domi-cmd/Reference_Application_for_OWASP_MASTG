package com.dkronig.maswe_platform.maswe_0055;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import com.dkronig.common.BaseLoginActivity;
import com.dkronig.maswe_platform.R;

public class LoginActivity extends BaseLoginActivity {
    private static final String CREDENTIALS_FILE_NAME = "maswe_0055_user_credentials";
    private EncryptionHandler encryptionHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        clearScreenshotFlags();

        try {
            encryptionHandler = new EncryptionHandler();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Clears any flags set that would prevent screenshots
     */
    private void clearScreenshotFlags(){
        /**
         getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
         WindowManager.LayoutParams.FLAG_SECURE);
         **/
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_SECURE);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login_maswe0055;
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
