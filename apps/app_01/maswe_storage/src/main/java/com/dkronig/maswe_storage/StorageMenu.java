package com.dkronig.maswe_storage;

import android.app.Activity;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.content.Intent;
import android.view.MenuItem;
import com.dkronig.maswe_storage.R;
import com.dkronig.maswe_storage.maswe_0001.MainActivity0001;

import com.dkronig.common.BaseActivityTemplate;


public class StorageMenu extends BaseActivityTemplate {

    // Define UI elements
    /** Button to navigate to the Login screen. */
    private Button maswe_0001_button;



    /**
     * Called when the activity is first created.
     * <p>
     * Initializes the user interface, retrieves button views, and
     * attaches click listeners that start the appropriate activities.
     * </p>
     *
     * @param savedInstanceState If the activity is being re-initialized after previously
     *                           being shut down, this Bundle contains the most recent
     *                           data supplied; otherwise, it is {@code null}.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage_menu);

        // Instantiate UI elements
        maswe_0001_button = findViewById(R.id.maswe_0001_button);

        addListener(maswe_0001_button, MainActivity0001.class);
    }

    
    // Handles "Go Back" navigation functionality
    @Override
    protected String getScreenTitle() {
        return "MASWE Storage";  // Optional custom title
    }
}