package com.example.weatherontheway.ui.alerts

import android.app.Application
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log.d
import com.example.weatherontheway.R
import com.example.weatherontheway.database.room.RoomRepositry
import com.example.weatherontheway.ui.notification.NotificationHelper

class AlertRecever() : BroadcastReceiver() {
   // lateinit var notificationHelper: NotificationHelper


    override fun onReceive(context: Context?, intent: Intent?) {
      /*  notificationHelper = NotificationHelper(context!!)
        val roomRepositry= RoomRepositry(context = context.applicationContext as Application)
        val current = roomRepositry.getData()[0].current
        val actualType = current.weather[0].description
        d("TAG", "Receved")
        if (intent != null) {

            val type = intent.getStringExtra("TYPE")
            if (actualType.contains(type!!) ) {
                val notificationBuilder = notificationHelper.getChanelNotification(
                    context.getString(R.string.weather_alert),
                    context.getString(R.string.take_care) + type
                )
                notificationHelper.getManger()!!.notify(1, notificationBuilder.build())
            }
            d("TAG", "Receved  $type")

        }*/
    }
}


