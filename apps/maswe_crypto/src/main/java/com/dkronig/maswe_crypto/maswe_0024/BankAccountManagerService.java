package com.dkronig.maswe_crypto.maswe_0024;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;
import androidx.annotation.Nullable;

import com.google.gson.Gson;

/**
 * Background service for managing bank account operations.
 *
 * This service processes signed banking commands received via intents, validates
 * their cryptographic signatures, and updates the account balance accordingly.
 * Balance updates are broadcast to registered receivers (here in profile activity).
 *
 * Features:
 * - Processes banking commands (increase/decrease balance)
 * - Validates command signatures for authenticity
 * - Maintains account balance (only in-memory)
 * - Broadcasts balance updates to UI components
 * - Prevents negative bank balances
 */
public class BankAccountManagerService extends Service {
    private static final String EXTRA_COMMAND = "command";
    private static final String EXTRA_BALANCE = "balance";
    private static final String BROADCAST_ACTION_BALANCE_UPDATED = "BANK_BALANCE_UPDATED";
    private static final String COMMAND_INCREASE = "increase";
    private static final String MESSAGE_INTEGRITY_FAILED = "Integrity check failed";
    private static final String MESSAGE_INSUFFICIENT_FUNDS = "Insufficient funds!";

    private long bankBalance = 0;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        // Not a bound service
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        try {
            processCommand(intent);
        } catch (Exception e) {
            handleCommandError(e);
        }
        return START_NOT_STICKY;
    }

    /**
     * Processes an incoming banking command intent.
     * Validates the command signature and executes the requested operation.
     *
     * @param intent The intent containing the banking command
     * @throws Exception If command processing fails
     */
    private void processCommand(Intent intent) throws Exception {
        if (intent == null) return;

        BankCommand command = extractCommand(intent);
        if (command == null) {
            return;
        }

        if (!validateCommandSignature(command)) {
            showToast(MESSAGE_INTEGRITY_FAILED);
            return;
        }
        executeCommand(command);
    }

    /**
     * Helper method that extracts and deserializes the banking command from the intent.
     *
     * @param intent The intent containing the command JSON
     * @return Deserialized BankCommand, or null if extraction fails
     */
    private BankCommand extractCommand(Intent intent) {
        String json = intent.getStringExtra(EXTRA_COMMAND);
        if (json == null) {
            return null;
        }

        return new Gson().fromJson(json, BankCommand.class);
    }

    /**
     * Validates the cryptographic signature of the banking command.
     * Parses the stored crc32 string to long.
     *
     * @param command The command to validate
     * @return true if signature is valid, false otherwise
     * @throws Exception If signature verification fails
     */
    private boolean validateCommandSignature(BankCommand command) {
        String payload = command.command + command.amountEuros + command.timestamp + command.nonce;
        long expected = IntegrityVerifier.crc32(payload);
        try {
            long received = Long.parseLong(command.hmac);
            return expected == received;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Executes the banking command after validation.
     * Currently supports the "increase" command type.
     *
     * @param command The validated command to execute
     */
    private void executeCommand(BankCommand command) {
        if (COMMAND_INCREASE.equals(command.command)) {
            processIncreaseCommand(command);
        }
    }

    /**
     * Processes a balance increase command.
     * Validates that the operation won't result in a negative balance.
     *
     * @param command The increase command containing the amount
     */
    private void processIncreaseCommand(BankCommand command) {
        long newBalance = bankBalance + command.amountEuros;

        if (newBalance < 0) {
            showToast(MESSAGE_INSUFFICIENT_FUNDS);
            return;
        }

        bankBalance = newBalance;
        broadcastBalanceUpdate();
    }

    /**
     * Broadcasts the current balance to registered receivers.
     */
    private void broadcastBalanceUpdate() {
        Intent updateIntent = new Intent(BROADCAST_ACTION_BALANCE_UPDATED);
        updateIntent.putExtra(EXTRA_BALANCE, bankBalance);
        updateIntent.setPackage(getPackageName());
        sendBroadcast(updateIntent);
    }

    /**
     * Handles errors that occur during command processing.
     *
     * @param e The exception that occurred
     */
    private void handleCommandError(Exception e) {
        throw new RuntimeException("Failed to process banking command", e);
    }

    /**
     * Displays a toast message on the main thread.
     * Required because services run on background threads.
     *
     * @param message The message to display
     */
    private void showToast(String message) {
        new Handler(Looper.getMainLooper()).post(() ->
                Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        );
    }
}