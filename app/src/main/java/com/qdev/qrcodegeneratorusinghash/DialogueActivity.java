package com.qdev.qrcodegeneratorusinghash;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.qdev.qrcodegeneratorusinghash.Service.HashKeyService;

public class DialogueActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("Stop Background service");
        alert.setCancelable(false);
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Intent intent = new Intent(getApplicationContext(), HashKeyService.class);
                getApplicationContext().stopService(intent);
                finish(); }
        });
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.cancel();
                finish();
            }
        });

        AlertDialog dlg = alert.create();
        dlg.show();
    }
}
