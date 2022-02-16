package com.example.weatherontheway.database.room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.weatherontheway.models.FavData
import com.example.weatherontheway.models.WeatherResponse


class RoomRepositry(context: Application) : AndroidViewModel(context) {
    val database :WeatherDatabase?=WeatherDatabase.getInstance(context)
    val weatherDao :WeatherDao= database!!.weatherDao()



    //      *** CURRENT WEATHER FUNCTIONS ***

    suspend fun saveAllData(allData : WeatherResponse){
        weatherDao.saveAllData(allData)
    }
    fun getAllData(): LiveData<List<WeatherResponse>>{
        return weatherDao.getAllData()
    }
    fun getData(): List<WeatherResponse>{
        return weatherDao.getData()
    }
    fun deleteAll(){
        return weatherDao.deleteAll()
    }


    //      *** FAVORITE WEATHER FUNCTIONS ***
    suspend fun saveFavData(favData : FavData){
        weatherDao.saveFaveData(favData)
    }
    fun getFavData(): LiveData<List<FavData>>{
        return weatherDao.getFavData()
    }
    fun getFavDataNotLiveData(): List<FavData>{
        return weatherDao.getFavDataNotLiveData()
    }
    fun deleteAllFav(){
        return weatherDao.deleteAllFav()
    }

    fun getOneFav(lat: String,lon: String):LiveData<FavData>{
        return weatherDao.getOneFav(lat,lon)
    }
    fun deleteOneFav(lat: String,lon: String){
        weatherDao.deleteOneFav(lat,lon)
    }


    //      *** WEATHER ALERTS FUNCTIONS ***
    fun saveAlert(alertTable: AlertTable):Long{
        return weatherDao.saveAlert(alertTable)
    }
    fun getAllAlerts():LiveData<List<AlertTable>>{
        return weatherDao.getAllAlerts()
    }
    fun deleteAlert(id: Long)=weatherDao.deleteAlert(id)
}