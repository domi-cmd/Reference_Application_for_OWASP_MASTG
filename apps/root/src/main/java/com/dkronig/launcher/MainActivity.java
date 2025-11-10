package com.dkronig.launcher;

import android.content.ComponentName;
import android.os.Bundle;
import android.content.Intent;
import android.widget.Toast;
import com.dkronig.common.BaseActivityTemplate;
import java.util.Map;

public class MainActivity extends BaseActivityTemplate {
    // Map button IDs to fully qualified class names of external apps
    private final Map<Integer, String[]> appMap = Map.of(
            // {packageName, fullyQualifiedActivityName}
            R.id.storage_button, new String[]{"com.dkronig.maswe_storage", "com.dkronig.maswe_storage.StorageMenu"},
            R.id.crypto_button,  new String[]{"com.dkronig.maswe_crypto", "com.dkronig.maswe_crypto.CryptoMenu"},
            R.id.platform_button,  new String[]{"com.dkronig.maswe_platform", "com.dkronig.maswe_platform.PlatformMenu"}
            // Add more apps here as they become standalone
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Assign click listeners to launch each external app
        appMap.forEach((buttonId, packageAndClass) -> {
            findViewById(buttonId).setOnClickListener(v -> launchApp(packageAndClass[0], packageAndClass[1]));
        });

    }

    //Launches an external app using a fully qualified ComponentName
    private void launchApp(String packageName, String activityName) {
        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(packageName, activityName));
            startActivity(intent);
        } catch (Exception e) {
            // Handle case where app isn't installed or activity isn't found
            Toast.makeText(this, "Cannot launch app: " + packageName, Toast.LENGTH_LONG).show();
        }
    }

    // Set name for action bar handled in BaseActivityTemplate
    @Override
    protected String getScreenTitle() {
        return "Choose MASWE Category";
    }
}