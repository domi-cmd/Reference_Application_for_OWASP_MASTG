package com.dkronig.maswe_crypto.maswe_0026;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dkronig.common.BaseActivityTemplate;
import com.dkronig.maswe_crypto.R;
import com.google.gson.Gson;

import java.util.UUID;

/**
 * Profile Activity for MASWE-0026
 * This activity provides a banking interface for users. It includes command signing
 * and service communication patterns in a simulated banking environment.
 *
 * Features:
 *  - View current account balance
 *  - Send signed commands to increase balance
 *  - Receive real-time balance updates via broadcasts
 */
public class ProfileActivity extends BaseActivityTemplate {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_banking);
        init();
    }

    protected void init() {
        Button sendBankCommandButton = findViewById(R.id.btn_sendBankCommand);
        EditText amountInput = findViewById(R.id.et_bankBalanceInput);

        if (sendBankCommandButton != null && amountInput != null) {
            sendBankCommandButton.setOnClickListener(v -> {
                String inputText = amountInput.getText().toString().trim();
                if (!inputText.isEmpty()) {
                    try {
                        int amount = Integer.parseInt(inputText);
                        sendCommand("increase", amount);
                    } catch (Exception e) {
                        amountInput.setError("Please enter a valid number");
                    }
                } else {
                    amountInput.setError("Amount required");
                }
            });
        }
    }

    private void sendCommand(String command, int amountEuros) throws Exception {
        BankCommand bankCommand = new BankCommand();
        bankCommand.command = command;
        bankCommand.amountEuros = amountEuros;
        bankCommand.timestamp = System.currentTimeMillis();
        bankCommand.nonce = UUID.randomUUID().toString();

        String payload = bankCommand.command + bankCommand.amountEuros
                + bankCommand.timestamp + bankCommand.nonce;
        bankCommand.signature = EncryptionHandler.sign(payload);

        Intent intent = new Intent(this, BankAccountManagerService.class);
        intent.putExtra("command", new Gson().toJson(bankCommand));
        startService(intent);
    }

    private BroadcastReceiver balanceReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            long balance = intent.getLongExtra("balance", 0);
            TextView tv = findViewById(R.id.tv_bankBalanceDisplay);
            if (tv != null) {
                tv.setText("Balance: " + balance + " €");
            }
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
