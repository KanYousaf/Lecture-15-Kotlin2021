package com.example.lecture15

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat


class MainActivity : AppCompatActivity() {
    private lateinit var notification_builder: NotificationCompat.Builder
    private lateinit var notificationManager : NotificationManager
    private lateinit var sensor_intent: Intent
    private val color = Color.RED
    private val light_color = Color.argb(255, 255, 255, 0)

    // This ID can be the value you want.
    private val NOTIFICATION_ID_STRING = "My Notifications"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sensor_intent = Intent(this, SensorActivity::class.java)
        notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(NOTIFICATION_ID_STRING, "My Channel Name", NotificationManager.IMPORTANCE_DEFAULT)
            channel.description = "My notification channel description"
            notificationManager.createNotificationChannel(channel)
        }
        notification_builder = NotificationCompat.Builder(this, NOTIFICATION_ID_STRING)
            .setSmallIcon(R.drawable.ic_priority_high_black_24dp)
            .setColor(color)
            .setContentTitle("Welcome to Notification")
            .setContentText("Let's go to Sensor Act")
            .setLights(light_color, 2000, 3000)
        notificationManager()

    }

    fun notificationManager() {
        val pendingIntent = PendingIntent.getActivity(this@MainActivity, 0, sensor_intent, 0)
        notification_builder.setContentIntent(pendingIntent)
        val notification = notification_builder.build()
        notificationManager.notify(99, notification)
    }
}
