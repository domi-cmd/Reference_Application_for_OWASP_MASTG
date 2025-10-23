package com.dkronig.maswe_storage.maswe_0002;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;

import com.dkronig.common.BaseRegisterActivity;
import com.dkronig.maswe_storage.R;

import java.io.File;
import java.io.File;
import android.content.Context;
import android.widget.Toast;

import androidx.core.content.FileProvider;

import java.io.FileOutputStream;
import java.io.IOException;
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
            // Call the vulnerable function
            storeSensitiveData(this, "maswe_0002_user_credentials.txt",
                    "Email: " + email + " Password: " + password + "\n");
            Toast.makeText(this, "Data saved insecurely!", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, "Error saving data: " + e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

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


    public static void storeSensitiveData(Context context, String filename, String data) throws IOException {
        File file = new File(context.getFilesDir(), filename);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(data.getBytes());  // Store plain text sensitive data
        fos.close();

        // Intentionally set insecure permissions (world-readable/writable)
        // This is deprecated and does not work anymore
        // Readable by all
        file.setReadable(true, false);
        // Writable by all
        file.setWritable(true, false);
    }
}