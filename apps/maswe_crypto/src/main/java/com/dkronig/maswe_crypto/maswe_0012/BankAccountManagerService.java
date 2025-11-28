package com.dkronig.maswe_crypto.maswe_0012;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.gson.Gson;

public class BankAccountManagerService extends Service {
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
            handleIntent(intent);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // This makes the service stop automatically if there is no more work
        return START_NOT_STICKY;
    }

    private void handleIntent(Intent intent) throws Exception {
        // Check if intent valid
        if (intent == null) return;

        // Unpack the banking command from the intents extra
        String json = intent.getStringExtra("command");
        if (json == null) return;

        // "Restore" the command to its formatting pre sending
        BankCommand cmd = new Gson().fromJson(json, BankCommand.class);

        // Check the checksum, to see if the received intent comes from the expected sender
        if (!EncryptionHandler.verify(cmd)) {
            showToast("Integrity check failed");
            return;
        }

        // Process the command passed by the intent
        if ("increase".equals(cmd.command)) {
            // Check for negative balances, incase of negative "increase" numbers are passed
            if(bankBalance + cmd.amountEuros < 0){
                showToast("Insufficient funds!");
                return;
            }
            bankBalance += cmd.amountEuros;
            sendBalanceUpdate();

        }
    }

    private void sendBalanceUpdate() {
        Intent updateIntent = new Intent("BANK_BALANCE_UPDATED");
        updateIntent.putExtra("balance", bankBalance);
        updateIntent.setPackage(getPackageName());
        sendBroadcast(updateIntent);
    }

    private void showToast(String msg) {
        // Helper, as toasts from a Service require posting to the main thread
        new Handler(Looper.getMainLooper()).post(() ->
                Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
        );
    }
}
