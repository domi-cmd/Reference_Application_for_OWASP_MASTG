package com.dkronig.common;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.widget.Button;
import java.util.Map;

public abstract class BaseActivityTemplate extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // All subclasses get an ActionBar with an Up (back) arrow by default
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(getScreenTitle());
        }
    }

    // Optionally allow each activity to define its own title
    protected String getScreenTitle() {
        return getClass().getSimpleName(); // Default: class name
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // Use modern back navigation API
            getOnBackPressedDispatcher().onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Adds a click listener to a button that launches the target activity.
     * Usage: addListener(myButton, TargetActivity.class);
     */
    protected void addListener(Button button, Class<? extends AppCompatActivity> targetActivityClass) {
        button.setOnClickListener(v -> {
            Intent intent = new Intent(BaseActivityTemplate.this, targetActivityClass);
            startActivity(intent);
        });
    }

    /**
     * Automatically binds buttons to activities based on their IDs
     * Example usage in child activity:
     * bindButtons(Map.of(R.id.login_button, LoginActivity.class, R.id.register_button, RegisterActivity.class));
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