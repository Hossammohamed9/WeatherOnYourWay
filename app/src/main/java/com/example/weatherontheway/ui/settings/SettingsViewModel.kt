package com.example.weatherontheway.ui.settings

import android.app.Activity
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherontheway.api.DataSourceViewModel
import com.example.weatherontheway.database.sharedPreference.SettingsModel
import com.example.weatherontheway.util.MyFunctions

class SettingsViewModel (application: Application) : AndroidViewModel(application) {
    private val mApplication: Application = application
    val dataSourceViewModel: DataSourceViewModel = DataSourceViewModel(mApplication)

    private val myFunctions = MyFunctions()

    fun setLocale(activity: Activity, languageCode: String?) {
        myFunctions.setLocale(activity, languageCode)
    }

    fun getSetting(): LiveData<SettingsModel> {
        return dataSourceViewModel.getSetting()
    }

    fun setSetting(settingModel: SettingsModel) {
        dataSourceViewModel.setSetting(settingModel)
    }
}