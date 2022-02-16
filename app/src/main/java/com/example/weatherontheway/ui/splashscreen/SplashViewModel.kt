package com.example.weatherontheway.ui.splashscreen

import android.app.Activity
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.weatherontheway.api.DataSourceViewModel
import com.example.weatherontheway.database.sharedPreference.SettingsModel
import com.example.weatherontheway.util.MyFunctions

class SplashViewModel(application: Application) : AndroidViewModel(application) {
    private val dataSourceViewModel: DataSourceViewModel = DataSourceViewModel(application)
    private val myFunctions = MyFunctions()

    private fun setLocale(activity: Activity, languageCode: String?) {
        myFunctions.setLocale(activity,languageCode)
    }
    fun getSetting():LiveData<SettingsModel>{
        return dataSourceViewModel.getSetting()
    }
    fun enableLocalization(activity: Activity, languageCode: String?){
        setLocale(activity,languageCode)

    }



}