package com.githab.laravish.myapplication

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        val data = message.data
        if (data.isNotEmpty()) {
            val title = data[TITLE_KEY]
            val mess = data[MESSAGE_KEY]
            if (!title.isNullOrBlank() && !mess.isNullOrBlank()) {
                pushNotification(title, mess)
            }
        }
    }

    private fun pushNotification(title: String, message: String) {
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID_1).apply {
            setSmallIcon(R.mipmap.ic_launcher)
            setContentTitle(title)
            setContentText(message)
            priority = NotificationCompat.PRIORITY_MAX
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelName = title
            val channelDescription = message
            val priorityChannel = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(CHANNEL_ID_1, channelName, priorityChannel).apply {
                description = channelDescription
            }
            notificationManager.createNotificationChannel(channel)
        }
        notificationManager.notify(NOTIFICATION_ID_1, notificationBuilder.build())
    }
}