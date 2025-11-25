package com.dkronig.maswe_crypto.maswe_0024;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.dkronig.common.BaseActivityTemplate;
import com.dkronig.maswe_crypto.R;
import com.google.gson.Gson;

public class ProfileActivity extends BaseActivityTemplate {
    private static final String BANK_PACKAGE = "com.dkronig.maswe_crypto.maswe_0024";
    private static final String BANK_ACTION   = "com.dkronig.maswe_crypto.bank.ACTION_PROCESS_COMMAND";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_banking);

        init();

    }

    protected void init() {
        Button sendBankCommandButton = findViewById(R.id.sendBankCommandButton);

        if (sendBankCommandButton != null) {
            sendBankCommandButton.setOnClickListener(v -> sendCommand("increase", 10));
        }
    }

    private void sendCommand(String command, int amountEuros){
        BankCommand bankCommand = new BankCommand();
        bankCommand.command = command;
        bankCommand.amountEuros = amountEuros;

        Intent intent = new Intent(this, BankAccountManagerService.class);
        intent.putExtra("command", new Gson().toJson(bankCommand));
        startService(intent);

        Log.d("AAA", "Send intent");
    }

    private BroadcastReceiver balanceReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            long balance = intent.getLongExtra("balance", 0);
            Log.d("AAAAAA", "Trying to change balance");

            //runOnUiThread(() -> { // Ensure UI thread
                TextView tv = findViewById(R.id.bankBalanceDisplay);
                if (tv != null) {
                    tv.setText("Balance: " + (balance / 100.0) + " €");
                }
            //});
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(
                balanceReceiver,
                new IntentFilter("BANK_BALANCE_UPDATED"),
                Context.RECEIVER_NOT_EXPORTED
        );
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(balanceReceiver);
    }

    // Set name for action bar handled in BaseActivityTemplate
    @Override
    protected String getScreenTitle() {
        return "Profile";
    }
}