package com.dkronig.maswe_platform.maswe_0064;

import android.content.Context;
import android.widget.Toast;

import com.dkronig.common.BaseRegisterActivity;
import com.dkronig.maswe_platform.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class RegisterActivity extends BaseRegisterActivity {

    private static final String TAG = "[REGISTER ACTIVITY]";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected String getScreenTitle() {
        return "Register";
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

    // Write sensitive user data so system logs upon registration
    @Override
    protected void onRegister(String email, String password) {
        try {
            storeSensitiveData(this, "login_data_readable.txt",
                    "Email: " + email + " Password: " + password + "\n");
            Toast.makeText(this, "Data saved insecurely!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Error saving data: " + e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }


    public static void storeSensitiveData(Context context, String filename, String data) throws IOException {
        File file = new File(context.getFilesDir(), filename);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            // Store plain text sensitive data
            fos.write(data.getBytes());
        }
        // Note: No setReadable(true, false) needed, as ContentProvider will expose the data
    }
}
