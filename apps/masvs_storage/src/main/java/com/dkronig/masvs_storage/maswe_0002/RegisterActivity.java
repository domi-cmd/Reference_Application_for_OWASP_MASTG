package com.dkronig.masvs_storage.maswe_0002;

import android.content.Intent;
import android.net.Uri;

import androidx.core.content.FileProvider;

import java.io.File;

import com.dkronig.common.BaseRegisterActivity;
import com.dkronig.masvs_storage.R;

/**
 * Register Activity for MASWE-0002
 */
public class RegisterActivity extends BaseRegisterActivity {
    private static final String SCREEN_TITLE = "Register Page";
    private static final String CREDENTIALS_FILE_NAME = "maswe_0002_user_credentials";

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
     * Uses an implicit intent with custom file provider to share credentials
     * stored in internal storage with other apps.
     *
     * @param email The registered email address
     * @param password The password (before encryption)
     */
    @Override
    protected void onRegister(String email, String password) {
        Uri uri = FileProvider.getUriForFile(
                this,
                this.getPackageName() + ".CustomFileProvider",
                new File(this.getFilesDir(), "maswe_0002_user_credentials.txt"));

        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.putExtra(Intent.EXTRA_STREAM, uri);
        share.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivity(Intent.createChooser(share, "Share file via"));
    }
}