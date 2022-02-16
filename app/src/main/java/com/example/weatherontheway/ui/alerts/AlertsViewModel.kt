package com.example.weatherontheway.ui.alerts

import android.app.Activity
import android.app.AlarmManager
import android.app.Application
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weatherontheway.api.DataSourceViewModel
import com.example.weatherontheway.database.room.AlertTable

class AlertsViewModel (application: Application) : AndroidViewModel(application) {
    private val dataSourceViewModel: DataSourceViewModel = DataSourceViewModel(application)
    val cancelAlert :MutableLiveData<Int> = MutableLiveData<Int>()



    fun saveAlertSetting(alert: String)=dataSourceViewModel.saveAlertSetting(alert)

    fun getAlertSetting(): LiveData<String>{
        return dataSourceViewModel.getAlertSetting()
    }
    fun gerAlertTable():LiveData<List<AlertTable>>{
        return dataSourceViewModel.getAllAlerts()
    }

    fun cancelAlert(activity: Activity, id: Int){
        deleteAlert(id.toLong())
        val alarmManager = activity.getSystemService(Context.ALARM_SERVICE) as AlarmManager?
        val myIntent = Intent(activity, AlertRecever::class.java)
        val pendingIntent = PendingIntent.getBroadcast(activity, id, myIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        alarmManager!!.cancel(pendingIntent)
    }
    private fun deleteAlert(id: Long)=dataSourceViewModel.deleteAlert(id)
}