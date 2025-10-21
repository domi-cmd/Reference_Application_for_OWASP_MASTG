package com.dkronig.maswe_storage.maswe_0001;

import android.app.Activity;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;
import android.widget.Button;
import android.content.Intent;

import com.dkronig.maswe_storage.R;

public class MainActivity0001 extends AppCompatActivity {

    // Define UI elements
    /** Button to navigate to the Login screen. */
    private Button login_button;
    /** Button to navigate to the Registering screen. */
    private Button register_button;
    /** Button to navigate to the Settings screen. */
    private Button settings_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_0001);

        // Set actionbar title and enable the back arrow (“Up” button) in the ActionBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("MASWE_0001");
        }

        // Instantiate UI elements
        login_button = findViewById(R.id.login_button);
        register_button = findViewById(R.id.register_button);
        settings_button = findViewById(R.id.settings_button);

        // Add listeners to buttons
        addListener(login_button, LoginActivity.class);
        addListener(register_button, RegisterActivity.class);
        addListener(settings_button, SettingsActivity.class);
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
            Intent intent = new Intent(MainActivity0001.this, targetActivityClass);
            startActivity(intent);
        });
    }

    // Handles "Go Back" navigation functionality
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle clicks on the ActionBar items
        if (item.getItemId() == android.R.id.home) {
            // Go back to previous activity
            getOnBackPressedDispatcher().onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}