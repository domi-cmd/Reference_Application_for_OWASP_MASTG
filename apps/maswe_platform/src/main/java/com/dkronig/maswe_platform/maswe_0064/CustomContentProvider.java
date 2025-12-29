package com.dkronig.maswe_platform.maswe_0064;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Custom Content Provider for MASWE-0064
 */
public class CustomContentProvider extends ContentProvider {
    private static final String AUTHORITY = "com.dkronig.maswe_platform.CustomContentProvider";

    @Override
    public boolean onCreate() {
        return true;
    }

    /**
     * Opens a file for reading based on the URI.
     *
     * The method extracts the filename directly from the URI.
     *
     * @param uri The URI of the file to open
     * @param mode The file access mode (only READ is supported)
     * @return ParcelFileDescriptor for the requested file
     * @throws FileNotFoundException If the file doesn't exist or URI is invalid
     */
    @Override
    public ParcelFileDescriptor openFile(Uri uri, String mode) throws FileNotFoundException {
        String fileName = uri.getLastPathSegment();

        if (fileName == null) {
            throw new FileNotFoundException("No file name specified in URI");
        }

        File file = new File(getContext().getFilesDir(), fileName);
        return ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY);
    }

    /**
     * Query operation not supported by this provider.
     *
     * @return null (operation not implemented)
     */
    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        return null;
    }

    /**
     * Returns the MIME type for the given URI.
     *
     * @param uri The URI to query
     * @return MIME type (always returns "text/plain")
     */
    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return "text/plain";
    }

    /**
     * Insert operation not supported by this provider.
     *
     * @return null (operation not implemented)
     */
    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) {
        return null;
    }

    /**
     * Delete operation not supported by this provider.
     *
     * @return 0 (operation not implemented)
     */
    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    /**
     * Update operation not supported by this provider.
     *
     * @return 0 (operation not implemented)
     */
    @Override
    public int update(@NonNull Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        return 0;
    }
}