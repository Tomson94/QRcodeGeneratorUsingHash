package com.qdev.qrcodegeneratorusinghash.Service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.qdev.qrcodegeneratorusinghash.DialogueActivity;
import com.qdev.qrcodegeneratorusinghash.PogoModel.HashKey;
import com.qdev.qrcodegeneratorusinghash.R;
import com.qdev.qrcodegeneratorusinghash.RestService.RetrofitInstance;
import com.qdev.qrcodegeneratorusinghash.RestService.RetrofitInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HashKeyService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        final Handler handler = new Handler();
       final int delay = 5*60*1000; //milliseconds
       // final int delay = 5000; //milliseconds

        startForeground(1,showNotification());
        handler.postDelayed(new Runnable(){
            public void run(){
               // Toast.makeText(getApplicationContext(),"service started",Toast.LENGTH_LONG).show();
                RetrofitInterface retrofitInterface = RetrofitInstance.getRetrofitInsatnce().create(RetrofitInterface.class);
                retrofitInterface.getKey().enqueue(new Callback<HashKey>() {
                    @Override
                    public void onResponse(Call<HashKey> call, Response<HashKey> response) {
                        if(response.isSuccessful()){
                            if(response.body().getHashkey().length()==0){

                            }else {
                                SharedPreferences myPref= getSharedPreferences("mypref",MODE_PRIVATE);
                                SharedPreferences.Editor  editor  =myPref.edit();
                                editor.putString("hashkey",response.body().getHashkey());
                                editor.commit();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<HashKey> call, Throwable t) {

                    }
                });
                handler.postDelayed(this, delay);
            }
        }, delay);
    }

    public Notification showNotification(){
        int notifyID = 1;
        String channelID = "channel_01";
        int importance = NotificationManager.IMPORTANCE_HIGH;
        NotificationChannel mChannel = null;
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Notification.Builder notification = new  Notification.Builder(getApplicationContext());
        Intent intent = new Intent(getApplicationContext(), DialogueActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mChannel = new NotificationChannel(channelID, "Channel_01", importance);
            mNotificationManager.createNotificationChannel(mChannel);
            notification.setChannelId(channelID)
                    .setContentTitle("qr")
                    .setContentText("service running")
                    .setContentIntent(pendingIntent)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setDefaults(Notification.DEFAULT_SOUND);
          

        }else {

            notification.setContentTitle("qr")
                    .setContentText("service running")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setDefaults(Notification.DEFAULT_SOUND)
                    .setContentIntent(pendingIntent);



        }

        Notification notification1 = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
              notification1= notification.build();
        }
        return notification1;
    }


    @Override
    public boolean stopService(Intent name) {
        return super.stopService(name);
    }

}
