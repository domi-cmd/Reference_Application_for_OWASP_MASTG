package com.dkronig.maswe_crypto.maswe_0025;

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

        // "Restore" the command to its formatting pre sending
        BankCommand cmd = new Gson().fromJson(json, BankCommand.class);

        // Check the checksum, to see if the received intent comes from the expected sender
        if (!verifyChecksum(cmd)) {
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

    private boolean verifyChecksum(BankCommand cmd) {
        String payload = cmd.command + cmd.amountEuros + cmd.timestamp + cmd.nonce;
        long expected = IntegrityVerifier.crc32(payload);
        try {
            // Need to parse the stored crc32 string to long
            long received = Long.parseLong(cmd.hmac);
            return expected == received;
        } catch (Exception e) {
            return false;
        }
    }

    private void showToast(String msg) {
        // Helper, as toasts from a Service require posting to the main thread
        new Handler(Looper.getMainLooper()).post(() ->
                Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
        );
    }
}
