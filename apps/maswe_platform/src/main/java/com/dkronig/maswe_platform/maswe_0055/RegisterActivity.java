package com.dkronig.maswe_platform.maswe_0055;

import android.os.Bundle;
import android.view.WindowManager;

import com.dkronig.common.BaseRegisterActivity;
import com.dkronig.maswe_platform.R;

/**
 * Register Activity for MASWE-0055
 *
 * Features:
 *  - Uses a custom encryption handler for decrypting user passwords.
 */
public class RegisterActivity extends BaseRegisterActivity {
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
        return R.layout.activity_register_maswe0055;
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
