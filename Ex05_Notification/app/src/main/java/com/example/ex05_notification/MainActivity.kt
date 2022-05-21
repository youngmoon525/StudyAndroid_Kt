package com.example.ex05_notification

import android.Manifest
import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//권한 처리하기 테스트===
        var status = ContextCompat.checkSelfPermission(this , "android.permission.ACCESS_FINE_LOCATION")
        if (status == PackageManager.PERMISSION_GRANTED){
            Log.d("퍼미션", " 허용됨 ")
        } else{
            Log.d("퍼미션", " 허용안됨 ")
        }

        val requestPermissionLauncher =
            registerForActivityResult(
                ActivityResultContracts.RequestPermission()
            ) { isGranted: Boolean ->
                if (isGranted) {
                    Log.d("TEST", " 권한 허용쓰 ");
                } else {
                    Log.d("TEST", " 권한 허용안함쓰 ")
                }
            }
        requestPermissionLauncher.launch(
            Manifest.permission.ACCESS_FINE_LOCATION
        )
//권한 처리하기 테스트===

        //노티피케이션 시작
        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        //val builder = NotificationCompat.Builder(this , "Test");
        val builder:NotificationCompat.Builder; //↑ 생성자를 미룰수가 있음
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){ //오레오 버전 이상은 반드시 채널 필요함
            val channel = NotificationChannel(
                "one-channel",
                "My Channel",
                NotificationManager.IMPORTANCE_HIGH
            )

            channel.description = "이것은 채널"
            channel.setShowBadge(true) // 뱃지 주기
            var uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val audioAttributes = AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .setUsage(AudioAttributes.USAGE_ALARM)
                .build()

            channel.setSound(uri,audioAttributes)
            channel.enableLights(true)
            channel.lightColor = Color.RED
            channel.enableVibration(true)
            channel.vibrationPattern = longArrayOf(100,200,100,200)

            manager.createNotificationChannel(channel)
            builder = NotificationCompat.Builder(this,"one-channel")
        }else{
            builder = NotificationCompat.Builder(this)
        }
        val detailIntent = Intent(this,DetailActivity::class.java);
        val pendingIntent = PendingIntent.getActivity(this,10,detailIntent,PendingIntent.FLAG_IMMUTABLE);
        builder.setContentIntent(pendingIntent)
        builder.setSmallIcon(android.R.drawable.ic_notification_overlay)
        builder.setWhen(System.currentTimeMillis())
        builder.setContentTitle("타이틀 입니다")
        builder.setContentText("메세지 부분 입니당.")

        manager.notify(11 , builder.build())


    }
}