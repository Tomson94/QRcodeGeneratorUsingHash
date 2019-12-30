package com.qdev.qrcodegeneratorusinghash.Reciever;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.qdev.qrcodegeneratorusinghash.MainActivity;
import com.qdev.qrcodegeneratorusinghash.Service.HashKeyService;

public class BootReciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        if(intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)){
            Intent intent1  = new Intent(context, HashKeyService.class);
            if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O){

                context.startForegroundService(intent1);

            }else {
                context.startService(intent1);
            }

        }
    }
}
