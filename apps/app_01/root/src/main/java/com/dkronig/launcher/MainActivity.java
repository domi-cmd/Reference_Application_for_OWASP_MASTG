package com.dkronig.launcher;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.dkronig.common.BaseActivityTemplate;
import com.dkronig.maswe_crypto.CryptoMenu;
import com.dkronig.maswe_platform.PlatformMenu;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;
import android.view.View;

import java.util.Map;

public class MainActivity extends BaseActivityTemplate {

    // Each button ID maps to the package name of a standalone MASWE app
    private static final Map<Integer, String> APP_LINKS = Map.of(
            R.id.storage_button, "com.dkronig.maswe_storage"
            //R.id.crypto_button, "com.dkronig.maswe_crypto",
            //R.id.platform_button, "com.dkronig.maswe_platform"
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Attach listeners for all buttons dynamically
        for (Map.Entry<Integer, String> entry : APP_LINKS.entrySet()) {
            View button = findViewById(entry.getKey());
            button.setOnClickListener(v -> openExternalApp(entry.getValue()));
        }

    }

    private void openExternalApp(String packageName) {
        PackageManager pm = getPackageManager();
        Intent launchIntent = pm.getLaunchIntentForPackage(packageName);

        if (launchIntent != null) {
            startActivity(launchIntent);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                finishAffinity();
                System.exit(0);
            }, 300); // wait 300ms before exit
        } else {
            Toast.makeText(this,
                    "App not found: " + packageName,
                    Toast.LENGTH_SHORT
            ).show();
        }
    }


    // Set name for action bar handled in BaseActivityTemplate
    @Override
    protected String getScreenTitle() {
        return "Choose MASWE Category";
    }
}