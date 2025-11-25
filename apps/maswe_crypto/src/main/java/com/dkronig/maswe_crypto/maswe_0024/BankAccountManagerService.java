package com.dkronig.maswe_crypto.maswe_0024;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.dkronig.maswe_crypto.R;
import com.google.gson.Gson;

public class BankAccountManagerService extends Service {

    private long bankBalance = 0; // in cents

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        // Not a bound service — we only use startService()
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        handleIntent(intent);
        return START_NOT_STICKY;  // service stops automatically if no more work
    }

    private void handleIntent(Intent intent) {
        if (intent == null) return;

        String json = intent.getStringExtra("command");
        if (json == null) return;

        BankCommand cmd = new Gson().fromJson(json, BankCommand.class);

        // === PROCESS COMMAND ===
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

        showToast("Success! New balance: " + (bankBalance / 100.0) + " €");
        Log.d("BankService", "Changed balance to: " + bankBalance);
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
        sendBroadcast(updateIntent);  // Local works for SDK < 33; for 33+ use normal broadcast
    }
}
