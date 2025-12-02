package com.dkronig.maswe_crypto.maswe_0011;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dkronig.common.BaseActivityTemplate;
import com.dkronig.maswe_crypto.R;
import com.dkronig.maswe_crypto.maswe_0015.BankAccountManagerService;
import com.dkronig.maswe_crypto.maswe_0015.BankCommand;
import com.google.gson.Gson;

import java.util.UUID;

public class ProfileActivity extends BaseActivityTemplate {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_template);
    }

    // Set name for action bar handled in BaseActivityTemplate
    @Override
    protected String getScreenTitle() {
        return "Profile";
    }
}
