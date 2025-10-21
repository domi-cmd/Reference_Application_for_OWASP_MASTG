package com.dkronig.maswe_crypto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;

import com.dkronig.common.BaseActivityTemplate;


/**
 * The {@code MainActivity} class serves as the main entry point of the application.
 * <p>
 * It displays three buttons (Login, Register, and Settings) that navigate
 * the user to their respective activities when clicked.
 * </p>
 *
 * <p><b>Responsibilities:</b></p>
 * <ul>
 *     <li>Initialize and reference the main UI buttons.</li>
 *     <li>Attach click listeners to each button to start new activities.</li>
 * </ul>
 *
 * <p><b>Usage:</b></p>
 * This activity is declared as the LAUNCHER activity in
 * {@code AndroidManifest.xml}, so it starts automatically when the app launches.
 *
 * @author Dominic Kronig
 */
public class CryptoMenu extends BaseActivityTemplate {

    // Define UI elements
    /** Button to navigate to the Login screen. */
    private Button placeholder;



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
        setContentView(R.layout.activity_crypto_menu);

        // Instantiate UI elements
        placeholder = findViewById(R.id.placeholder_button);

        //addListener(placeholder, MainActivity0001.class);
    }


    // Handles "Go Back" navigation functionality
    @Override
    protected String getScreenTitle() {
        return "MASWE Crypto";  // Optional custom title
    }
}