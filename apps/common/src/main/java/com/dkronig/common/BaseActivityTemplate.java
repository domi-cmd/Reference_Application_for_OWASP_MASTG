package com.dkronig.common;

import android.os.Bundle;
import android.view.MenuItem;
import android.content.Intent;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Map;

/**
 * Base activity template for common functionalities by all activities.
 * - Automatic action bar setup, which allows for "go back" navigation and displays app name
 * - Customizable screen title with default value
 * - Handling of button-to-activity binding
 */
public abstract class BaseActivityTemplate extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActionBar();
    }

    /**
     * Configures the action bar with "go back" navigation and custom title.
     */
    private void setupActionBar(){
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(getScreenTitle());
        }
    }

    /**
     * Returns the title to be displayed in the action bar.
     * Can be overridden in subclasses to provide a custom name.
     *
     * @return The screen title of the app, which defaults to the simple app name.
     */
    protected String getScreenTitle() {
        return getClass().getSimpleName();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            getOnBackPressedDispatcher().onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Adds a click listener to a button that launches the target activity.
     *
     * @param button The button to which the listener is attached.
     * @param targetActivityClass The class of the activity which is launched upon clicking
     *                            the button.
     */
    protected void addListener(Button button, Class<? extends AppCompatActivity> targetActivityClass) {
        button.setOnClickListener(v -> {
            Intent intent = new Intent(BaseActivityTemplate.this, targetActivityClass);
            startActivity(intent);
        });
    }

    /**
     * Automatically binds multiple buttons to their respective activities.
     * Each button is configured to launch its corresponding activity on click.
     *
     * Example usage:
     * <pre>
     * Map<Integer, Class<?>> buttonMap = new HashMap<>();
     * buttonMap.put(R.id.btn_login, LoginActivity.class);
     * buttonMap.put(R.id.btn_register, RegisterActivity.class);
     * bindButtons(buttonMap);
     * </pre>
     *
     * @param buttonMap Map of button resource IDs to their target activity classes
     */
    protected void bindButtons(Map<Integer, Class<? extends AppCompatActivity>> buttonMap) {
        for (Map.Entry<Integer, Class<? extends AppCompatActivity>> entry : buttonMap.entrySet()) {
            Button button = findViewById(entry.getKey());
            if (button != null) {
                addListener(button, entry.getValue());
            }
        }
    }
}