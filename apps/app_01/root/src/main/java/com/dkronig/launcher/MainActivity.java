package com.dkronig.launcher;

import android.app.Activity;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.content.Intent;

import com.dkronig.storage.StorageMenu;

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
public class MainActivity extends AppCompatActivity {

    // Define UI elements
    /** Button to navigate to the Login screen. */
    private Button storage_button, crypto_button, auth_button, network_button,
            platform_button, code_button, resilience_button, privacy_button;



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
        setContentView(R.layout.activity_main);

        // Instantiate UI elements
        storage_button = findViewById(R.id.storage_button);
        //crypto_button = findViewById(R.id.crypto_button);
        //auth_button = findViewById(R.id.auth_button);
        //network_button = findViewById(R.id.network_button);
        //platform_button = findViewById(R.id.platform_button);
        //code_button = findViewById(R.id.code_button);
        //resilience_button = findViewById(R.id.resilience_button);
        //privacy_button = findViewById(R.id.privacy_button);


        addListener(storage_button, StorageMenu.class);
    }


    /**
     * Helper function to remove boiler plate code.
     * Attaches a click listener to the given button that launches the specified activity.
     * <p>
     * When the button is pressed, a new {@link Intent} is created and used to start
     * the target activity.
     * </p>
     *
     * @param button              The {@link Button} that should respond to clicks.
     * @param targetActivityClass The {@link Activity} class to launch when the button is clicked.
     */
    private void addListener(Button button, Class<? extends Activity> targetActivityClass){
        button.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, targetActivityClass);
            startActivity(intent);
        });
    }
}