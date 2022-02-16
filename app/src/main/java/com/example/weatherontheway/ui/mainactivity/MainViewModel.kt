package com.example.weatherontheway.ui.mainactivity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.weatherontheway.api.DataSourceViewModel
import com.example.weatherontheway.database.sharedPreference.SettingsModel
import com.example.weatherontheway.models.FavData

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val dataSourceViewModel: DataSourceViewModel = DataSourceViewModel(application)
    fun getFavDataNotLiveData(): List<FavData>{
        return dataSourceViewModel.getFavDataNotLiveData()
    }

    fun saveFav(lat: String,lon: String,lang: String,units :String){
        dataSourceViewModel.saveFave(lat,lon,lang,units)
    }
    fun getSetting():LiveData<SettingsModel>{
        return dataSourceViewModel.getSetting()
    }
    fun deleteAllFav(){
        return dataSourceViewModel.deleteAllFav()
    }
}