package com.example.ex05_notificationjava;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.ActivityResultRegistry;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.app.NotificationCompat;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int status = getApplicationContext().checkSelfPermission("android.permission.ACCESS_FINE_LOCATION");
        if (status == PackageManager.PERMISSION_GRANTED) {
            Log.d("퍼미션", " 허용됨 ");
        } else {
            Log.d("퍼미션", " 허용안됨 ");
        }

        ActivityResultLauncher requestPermissionLuncher = registerForActivityResult(
                new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
                    @Override
                    public void onActivityResult(Boolean result) {
                        if(result){
                            Log.d("TEST", " 권한 허용쓰 ");
                        }else{
                            Log.d("TEST", " 권한 허용안함쓰 ");
                        }
                    }
                });

        requestPermissionLuncher.launch(
                Manifest.permission.ACCESS_FINE_LOCATION);


        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        NotificationCompat.Builder builder ;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("one-channel" , "My Channel" , NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription( "이것은 채널" );
            channel.setShowBadge(true) ;// 뱃지 주기
            Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes.USAGE_ALARM)
                    .build() ;

            channel.setSound(uri,audioAttributes);
            channel.enableLights(true);
            channel.setLightColor( Color.RED );
            channel.enableVibration(true);
            channel.setVibrationPattern(new long[]{100,200,100,200});

            manager.createNotificationChannel(channel);
            builder = new NotificationCompat.Builder(this,"one-channel");
        }else{
            builder = new NotificationCompat.Builder(this);
        }
        builder.setSmallIcon(android.R.drawable.ic_notification_overlay);
        builder.setWhen(System.currentTimeMillis());
        builder.setContentTitle("타이틀 입니다");
        builder.setContentText("메세지 부분 입니당.");

        manager.notify(11 , builder.build());

    }

}