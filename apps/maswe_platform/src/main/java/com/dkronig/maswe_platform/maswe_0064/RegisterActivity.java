package com.dkronig.maswe_platform.maswe_0064;

import android.content.Context;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.dkronig.common.BaseRegisterActivity;
import com.dkronig.maswe_platform.R;

/**
 * Register Activity for MASWE-0064
 */
public class RegisterActivity extends BaseRegisterActivity {
    private static final String CREDENTIALS_FILE_NAME = "maswe_0064_user_credentials";

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
    protected void onRegister(String email, String password) {
        try {
            storeSensitiveData(this, "maswe_0064_user_credentials.txt",
                    "Email: " + email + " Password: " + password + "\n");
        } catch (Exception e) {
            Toast.makeText(this, "Error saving data: " + e.getMessage(),
                    Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    public static void storeSensitiveData(Context context, String filename, String data) throws IOException {
        File file = new File(context.getFilesDir(), filename);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(data.getBytes());
        }
    }
}
