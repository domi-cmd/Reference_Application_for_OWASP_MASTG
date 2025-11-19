package com.dkronig.maswe_storage.maswe_0002;

import android.content.Intent;
import android.net.Uri;
import com.dkronig.common.BaseRegisterActivity;
import com.dkronig.maswe_storage.R;
import java.io.File;
import androidx.core.content.FileProvider;

public class RegisterActivity extends BaseRegisterActivity {

    private static final String TAG = "[REGISTER ACTIVITY]";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register_template;
    }

    // Provide IDs for BaseRegisterActivity to find UI elements
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
    protected String getScreenTitle() {
        return "Register";
    }

    // Define name for encrypted file where user credentials are stored
    @Override
    protected String getCredentialFileName() {
        return "maswe_0002_user_credentials";
    }

    // Use an implicit intent with my misconfigured file provider to share credentials
    // stored in internal storage with other apps
    @Override
    protected void onRegister(String email, String password) {
        Uri uri = FileProvider.getUriForFile(
                this,
                this.getPackageName() + ".CustomFileProvider",
                new File(this.getFilesDir(), "maswe_0002_user_credentials.txt")
        );

        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.putExtra(Intent.EXTRA_STREAM, uri);
        share.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivity(Intent.createChooser(share, "Share file via"));
    }
}