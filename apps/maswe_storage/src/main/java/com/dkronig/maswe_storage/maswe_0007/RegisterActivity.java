package com.dkronig.maswe_storage.maswe_0007;

import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import com.dkronig.common.BaseRegisterActivity;
import com.dkronig.maswe_storage.R;
import java.io.IOException;
import java.io.OutputStream;

public class RegisterActivity extends BaseRegisterActivity {

    private static final String FILENAME = "maswe_0007_user_credentials.txt";
    private Uri fileUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create a new MediaStore entry in shared documents directory to store user credentials in
        ContentValues values = new ContentValues();
        values.put(MediaStore.MediaColumns.DISPLAY_NAME, FILENAME);
        values.put(MediaStore.MediaColumns.MIME_TYPE, "text/plain");
        values.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DOCUMENTS);

        fileUri = getContentResolver().insert(MediaStore.Files.
                getContentUri("external"), values);
    }

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

    // Write sensitive user data to system shared storage
    @Override
    protected void onRegister(String email, String password) {
       writeToSharedStorage("Email: " + email + " Password: " + password);
    }

    private void writeToSharedStorage(String content) {
        // Write content using OutputStream wrapped in BufferedWriter
        try (OutputStream out = getContentResolver().openOutputStream(fileUri)) {
            assert out != null;
            out.write(content.getBytes());
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
