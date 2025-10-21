package com.dkronig.maswe_storage.maswe_0001;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.content.Intent;

import com.dkronig.common.BaseActivityTemplate;
import com.dkronig.maswe_storage.R;

public class SettingsActivity extends BaseActivityTemplate {

    // Define UI elements
    private Button main_menu_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }
}