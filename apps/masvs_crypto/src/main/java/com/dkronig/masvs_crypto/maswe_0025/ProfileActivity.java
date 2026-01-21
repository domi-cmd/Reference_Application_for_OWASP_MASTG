package com.dkronig.masvs_crypto.maswe_0025;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.UUID;

import com.dkronig.common.BaseActivityTemplate;
import com.dkronig.masvs_crypto.R;

/**
 * Profile Activity for MASWE-0025
 * This activity provides a banking interface for users. It includes command signing
 * and service communication patterns in a simulated banking environment.
 *
 * Features:
 *  - View current account balance
 *  - Send signed commands to increase balance
 *  - Receive real-time balance updates via broadcasts
 */
public class ProfileActivity extends BaseActivityTemplate {
    private static final String SCREEN_TITLE = "Profile Page";
    private static final String BROADCAST_ACTION_BALANCE_UPDATED = "BANK_BALANCE_UPDATED";
    private static final String EXTRA_COMMAND = "command";
    private static final String EXTRA_BALANCE = "balance";
    private static final String COMMAND_INCREASE = "increase";
    private static final String ERROR_INVALID_NUMBER = "Please enter a valid number";
    private static final String ERROR_AMOUNT_REQUIRED = "Amount required";
    private static final String BALANCE_FORMAT = "Balance: %d €";

    private BroadcastReceiver balanceReceiver;
    private EditText etAmountInput;
    private TextView tvBalanceDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_banking);

        initializeViews();
        setupBalanceReceiver();
    }

    /**
     * Initializes UI components and sets up event listeners.
     */
    private void initializeViews() {
        Button btnSendBankCommand = findViewById(R.id.btn_sendBankCommand);
        etAmountInput = findViewById(R.id.et_bankBalanceInput);
        tvBalanceDisplay = findViewById(R.id.tv_bankBalanceDisplay);

        if (btnSendBankCommand != null && etAmountInput != null) {
            btnSendBankCommand.setOnClickListener(v -> handleBankCommandClick());
        }
    }

    /**
     * Handles the bank command button click event.
     * Validates input and sends the banking command if valid.
     */
    private void handleBankCommandClick() {
        String inputText = etAmountInput.getText().toString().trim();

        if (inputText.isEmpty()) {
            etAmountInput.setError(ERROR_AMOUNT_REQUIRED);
            return;
        }

        try {
            int amount = Integer.parseInt(inputText);
            sendBankCommand(COMMAND_INCREASE, amount);
        } catch (NumberFormatException e) {
            etAmountInput.setError(ERROR_INVALID_NUMBER);
        } catch (Exception e) {
            etAmountInput.setError("Command failed: " + e.getMessage());
        }
    }

    /**
     * Creates and sends a signed banking command to the service.
     *
     * @param command The banking command (e.g., "increase")
     * @param amountEuros The amount in euros
     * @throws Exception If signing or service communication fails
     */
    private void sendBankCommand(String command, int amountEuros) throws Exception {
        BankCommand bankCommand = createBankCommand(command, amountEuros);

        Intent intent = new Intent(this, BankAccountManagerService.class);
        intent.putExtra(EXTRA_COMMAND, new Gson().toJson(bankCommand));
        startService(intent);
    }

    /**
     * Creates a signed bank command with timestamp and nonce.
     *
     * @param command The banking command
     * @param amountEuros The amount in euros
     * @return Signed BankCommand object
     * @throws Exception If signature generation fails
     */
    private BankCommand createBankCommand(String command, int amountEuros) throws Exception {
        BankCommand bankCommand = new BankCommand();
        bankCommand.command = command;
        bankCommand.amountEuros = amountEuros;
        bankCommand.timestamp = System.currentTimeMillis();
        bankCommand.nonce = UUID.randomUUID().toString();

        String payload = buildSignaturePayload(bankCommand);
        bankCommand.signature = EncryptionHandler.sign(payload);

        return bankCommand;
    }

    /**
     * Builds the payload string for signature generation.
     *
     * @param command The bank command to sign
     * @return Concatenated payload string
     */
    private String buildSignaturePayload(BankCommand command) {
        return command.command + command.amountEuros + command.timestamp + command.nonce;
    }

    /**
     * Sets up the broadcast receiver for balance updates.
     */
    private void setupBalanceReceiver() {
        balanceReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                updateBalanceDisplay(intent);
            }
        };
    }

    /**
     * Updates the balance display from the received broadcast intent.
     *
     * @param intent The broadcast intent containing balance data
     */
    @SuppressLint("DefaultLocale")
    private void updateBalanceDisplay(Intent intent) {
        long balance = intent.getLongExtra(EXTRA_BALANCE, 0);
        if (tvBalanceDisplay != null) {
            tvBalanceDisplay.setText(String.format(BALANCE_FORMAT, balance));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(
                balanceReceiver,
                new IntentFilter(BROADCAST_ACTION_BALANCE_UPDATED),
                Context.RECEIVER_NOT_EXPORTED);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(balanceReceiver);
    }

    @Override
    protected String getScreenTitle() {
        return SCREEN_TITLE;
    }
}
