package com.dkronig.app_01;

import android.app.Activity;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    // Define UI elements
    private Button login_button;
    private Button register_button;
    private Button settings_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
            Intent intent = new Intent(MainActivity.this, targetActivityClass);
            startActivity(intent);
        });
    }
}