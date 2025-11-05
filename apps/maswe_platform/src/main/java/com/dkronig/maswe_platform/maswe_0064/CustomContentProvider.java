package com.dkronig.maswe_platform.maswe_0064;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;

import java.io.File;
import java.io.FileNotFoundException;

public class CustomContentProvider extends ContentProvider {
    private static final String AUTHORITY = "com.dkronig.maswe_platform.CustomContentProvider";
    //private static final String FILE_PATH = "maswe_0064_user_credentials.txt";

    @Override
    public boolean onCreate() {
        return true;
    }

    @Override
    public ParcelFileDescriptor openFile(Uri uri, String mode) throws FileNotFoundException {
        String fileName = uri.getLastPathSegment(); // extracts "login_data_readable.txt"

        if (fileName == null) {
            throw new FileNotFoundException("No file name specified in URI");
        }

        File file = new File(getContext().getFilesDir(), fileName);
        return ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY);
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return null;
    }

    @Override
    public String getType(Uri uri) {
        return "text/plain";
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}