package com.dkronig.maswe_storage.maswe_0007;

import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.IOException;
import java.io.OutputStream;

import com.dkronig.common.BaseRegisterActivity;
import com.dkronig.maswe_storage.R;

public class RegisterActivity extends BaseRegisterActivity {
    private static final String SCREEN_TITLE = "Register Page";
    private static final String CREDENTIALS_FILE_NAME = "maswe_0007_user_credentials";
    private static final String FILENAME = "maswe_0007_user_credentials.txt";
    private Uri fileUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        createMediaStoreEntry();
    }

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

    @Override
    protected void onRegister(String email, String password) {
       writeToSharedStorage("Email: " + email + " Password: " + password);
    }

    /**
     * Creates a new MediaStore entry in shared documents directory to store user credentials in.
     */
    private void createMediaStoreEntry(){
        ContentValues values = new ContentValues();
        values.put(MediaStore.MediaColumns.DISPLAY_NAME, FILENAME);
        values.put(MediaStore.MediaColumns.MIME_TYPE, "text/plain");
        values.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DOCUMENTS);

        fileUri = getContentResolver().insert(MediaStore.Files.
                getContentUri("external"), values);
    }

    /**
     * Saves user credentials to shared storage (MediaStore)
     * Writes content using OutputStream wrapped in BufferedWriter.
     *
     * @param content Consists of the user email and password.
     */
    private void writeToSharedStorage(String content) {
        try (OutputStream out = getContentResolver().openOutputStream(fileUri)) {
            assert out != null;
            out.write(content.getBytes());
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
