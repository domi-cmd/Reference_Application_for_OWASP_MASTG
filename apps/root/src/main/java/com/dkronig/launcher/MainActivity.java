package com.dkronig.launcher;

import android.content.ComponentName;
import android.os.Bundle;
import android.content.Intent;
import android.widget.Toast;

import com.dkronig.common.BaseActivityTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Main Activity for the root app of this project. All included apps can be launched from here.
 *
 * Apps and their respective vulnerability categories here included are:
 *  - maswe_storage (Storage vulnerabilities)
 *  - maswe_crypto (Crypto vulnerabilities)
 *  - maswe_platform (Platform vulnerabilities)
 *
 *  Each category is implemented as a separate app module that can be launched via explicit intents.
 *  The apps can also be launched independently, as they are all standalone apps themselves.
 */
public class MainActivity extends BaseActivityTemplate {
    private static final String SCREEN_TITLE = "Choose MASWE Category";

    // Package names for OWASP MASWE Apps that are to be included
    private static final String STORAGE_PACKAGE = "com.dkronig.maswe_storage";
    private static final String CRYPTO_PACKAGE = "com.dkronig.maswe_crypto";
    private static final String PLATFORM_PACKAGE = "com.dkronig.maswe_platform";

    // Launcher Activities for the Apps that are to be included
    private static final String STORAGE_LAUNCHER_ACTIVITY = "com.dkronig.maswe_storage.StorageMenu";
    private static final String CRYPTO_LAUNCHER_ACTIVITY = "com.dkronig.maswe_crypto.CryptoMenu";
    private static final String PLATFORM_LAUNCHER_ACTIVITY = "com.dkronig.maswe_platform.PlatformMenu";

    private Map<Integer, String[]> appMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeAppMap();
        setupButtonListeners();
    }

    /**
     * Initializes the mapping between button IDs and their corresponding app configurations.
     */
    private void initializeAppMap() {
        appMap = new HashMap<>();
        appMap.put(R.id.btn_storage, new String[]{STORAGE_PACKAGE, STORAGE_LAUNCHER_ACTIVITY});
        appMap.put(R.id.btn_crypto, new String[]{CRYPTO_PACKAGE, CRYPTO_LAUNCHER_ACTIVITY});
        appMap.put(R.id.btn_platform, new String[]{PLATFORM_PACKAGE, PLATFORM_LAUNCHER_ACTIVITY});
    }

    /**
     * Launches an external app using an explicit ComponentName.
     *
     * @param packageName The package name of the target app
     * @param activityName The fully qualified class name of the target activity
     */
    private void launchApp(String packageName, String activityName) {
        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(packageName, activityName));
            startActivity(intent);
        } catch (Exception e) {
            handleLaunchError(packageName, e);
        }
    }

    /**
     * Sets up click listeners for all category buttons.
     */
    private void setupButtonListeners(){
        // Assign click listeners to launch each external app
        appMap.forEach((buttonId, packageAndClass) -> {
            findViewById(buttonId).setOnClickListener(v -> launchApp(packageAndClass[0],
                    packageAndClass[1]));
        });
    }

    /**
     * Handles errors that occur when launching an external app.
     *
     * @param packageName The package name that failed to launch
     * @param e The exception that occurred
     */
    private void handleLaunchError(String packageName, Exception e){
        // Handle case where app isn't installed or activity isn't found
        String errorMessage = "Cannot launch app: " + packageName;
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    protected String getScreenTitle() {
        return SCREEN_TITLE;
    }
}