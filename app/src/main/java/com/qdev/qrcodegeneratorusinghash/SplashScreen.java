package com.qdev.qrcodegeneratorusinghash;

import android.app.AppComponentFactory;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.qdev.qrcodegeneratorusinghash.Service.HashKeyService;

public class SplashScreen extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Handler handler = new Handler();
        Intent intent1  = new Intent(getApplicationContext(), HashKeyService.class);
        stopService(intent1);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O){

                    startForegroundService(intent1);

                }else {
                    startService(intent1);
                }
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();

            }
        },5000);
    }
}
