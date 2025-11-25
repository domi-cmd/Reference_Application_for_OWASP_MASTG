package com.dkronig.maswe_crypto.maswe_0024;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Handler;
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
        handleIntent(intent);
        // This makes the service stop automatically if there is no more work
        return START_NOT_STICKY;
    }

    private void handleIntent(Intent intent) {
        // Check if intent valid
        if (intent == null) return;

        // Unpack the banking command from the intents extra
        String json = intent.getStringExtra("command");
        if (json == null) return;

        BankCommand cmd = new Gson().fromJson(json, BankCommand.class);

        // Process the command passed by the intent
        if ("increase".equals(cmd.command)) {
            bankBalance += cmd.amountEuros;
            sendBalanceUpdate();

        } else if ("decrease".equals(cmd.command)) {
            if (bankBalance >= cmd.amountEuros) {
                bankBalance -= cmd.amountEuros;
                sendBalanceUpdate();
            } else {
                showToast("Insufficient funds!");
                return;
            }
        }

        showToast("Success! New balance: " + (bankBalance) + " €");
    }

    private void showToast(String msg) {
        // Toasts from a Service require posting to the main thread
        new Handler(Looper.getMainLooper()).post(() ->
                Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
        );
    }

    private void sendBalanceUpdate() {
        Intent updateIntent = new Intent("BANK_BALANCE_UPDATED");
        updateIntent.putExtra("balance", bankBalance);
        updateIntent.setPackage(getPackageName());
        sendBroadcast(updateIntent);
    }
}
