package com.githab.laravish.myapplication

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       // pushNotification()
    }
    private fun pushNotification(){
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val notificationBuilderOne = NotificationCompat.Builder(this, CHANNEL_ID_1).apply{
            setSmallIcon(R.mipmap.ic_launcher)
            setContentTitle(getString(R.string.notification_title))
            setContentText(getString(R.string.notification_text))
            priority = NotificationCompat.PRIORITY_MAX
        }
        val notificationBuilderTwo = NotificationCompat.Builder(this, CHANNEL_ID_2).apply{
            setSmallIcon(R.mipmap.ic_launcher)
            setContentTitle(getString(R.string.notification_title_2))
            setContentText(getString(R.string.notification_text_2))
            priority = NotificationCompat.PRIORITY_DEFAULT
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelName = "${getString(R.string.channel_name)}${CHANNEL_ID_1}"
            val channelDescription = "${getString(R.string.channel_description)}${CHANNEL_ID_1}"
            val priorityChannel = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(CHANNEL_ID_1,channelName,priorityChannel).apply {
                description = channelDescription
            }
            notificationManager.createNotificationChannel(channel)
        }
        notificationManager.notify(NOTIFICATION_ID_1,notificationBuilderOne.build())

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelName = "${getString(R.string.channel_name)}${CHANNEL_ID_2}"
            val channelDescription = "${getString(R.string.channel_description)}${CHANNEL_ID_2}"
            val priorityChannel = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID_2, channelName, priorityChannel).apply {
                description = channelDescription
            }
            notificationManager.createNotificationChannel(channel)
        }
        notificationManager.notify(NOTIFICATION_ID_2 + 1,notificationBuilderTwo.build())
        notificationManager.notify(NOTIFICATION_ID_2 + 2,notificationBuilderTwo.build())
        notificationManager.notify(NOTIFICATION_ID_2+ 3,notificationBuilderTwo.build())
    }
}