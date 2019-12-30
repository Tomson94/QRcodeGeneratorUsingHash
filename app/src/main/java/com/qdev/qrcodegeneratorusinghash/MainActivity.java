package com.qdev.qrcodegeneratorusinghash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.qdev.qrcodegeneratorusinghash.Service.HashKeyService;

public class MainActivity extends AppCompatActivity {
ImageView imageView ;
Button button;
public  int width =700;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.image);
        button = findViewById(R.id.generate);
        SharedPreferences sharedPreferences  = getSharedPreferences("mypref",MODE_PRIVATE);
        String value = sharedPreferences.getString("autorestart","no-data");
        if(!value.equals("1"))
        AutoStartHelper.getInstance().getAutoStartPermission(this);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Intent intent = new Intent();
            String packageName = getPackageName();
            PowerManager pm = (PowerManager) getSystemService(POWER_SERVICE);
            if (!pm.isIgnoringBatteryOptimizations(packageName)) {
                intent.setAction(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
                intent.setData(Uri.parse("package:" + packageName));
                startActivityForResult(intent, 1000);
            }
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("mypref",MODE_PRIVATE);
                String key  = sharedPreferences.getString("hashkey","no-data");
                Bitmap bitmap = generateQRCode(key);
                imageView.setImageBitmap(bitmap);
            }
        });

    }
    public Bitmap generateQRCode(String key){
        BitMatrix bitMatrix;
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            bitMatrix = multiFormatWriter.encode(
                    key,
                    BarcodeFormat.QR_CODE,
                    width, width, null
            );

        } catch (IllegalArgumentException Illegalargumentexception) {

            return null;
        } catch (WriterException e) {
            e.printStackTrace();
            return null;
        }
        BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
        Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
        return bitmap;
    }
}
