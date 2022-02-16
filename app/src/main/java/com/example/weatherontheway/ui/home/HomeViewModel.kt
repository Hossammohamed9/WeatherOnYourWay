package com.example.weatherontheway.ui.home

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.content.Context
import android.location.Location
import android.os.Build
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.weatherontheway.R
import com.example.weatherontheway.api.DataSourceViewModel
import com.example.weatherontheway.database.sharedPreference.SettingsModel
import com.example.weatherontheway.models.WeatherResponse
import com.example.weatherontheway.util.MyFunctions
import com.google.android.gms.maps.model.LatLng

class HomeViewModel (application: Application) : AndroidViewModel(application) {

    private val mApplication: Application =application
    private val locationHanding: LocationClass = LocationClass(mApplication.applicationContext)
    private val dataSourceViewModel: DataSourceViewModel = DataSourceViewModel(mApplication)
    private val myFunctions : MyFunctions= MyFunctions()


    @RequiresApi(Build.VERSION_CODES.M)
    fun loadOnlineData(lat: String, lon: String, lang: String, units: String, context: Context)
    {
        if (myFunctions.isOnline(context)) {
            Log.d("TAG", "loadOnlineData: ")
            dataSourceViewModel.loadOneCall(lat, lon, lang, units)
        }else{
            Toast.makeText(context,context.getString(R.string.offline), Toast.LENGTH_SHORT).show()
        }
    }

    fun getUnites(units: String): String {
        return myFunctions.getUnits(units)
    }

    fun gettingLocation(context: Context, activity: Activity) :LiveData<Location>{
        locationHanding.loadLocation(context,activity)
        return locationHanding.getLocatin()
    }
    fun getLocationSettnig():LiveData<LatLng>{
        return dataSourceViewModel.getLocationSetting()
    }

    fun getSetting():LiveData<SettingsModel>{
        return dataSourceViewModel.getSetting()
    }


    fun getRoomData():LiveData<List<WeatherResponse>>{
        return dataSourceViewModel.getRoomDataBase()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getImage(imageView: ImageView, string: String) {
        myFunctions.getImage(imageView,string)
    }


    @SuppressLint("SimpleDateFormat")
    fun formatDate(format: Int): String {
        return myFunctions.formatDate(format)
    }

    @SuppressLint("SimpleDateFormat")
    fun formatTime(format: Int): String {
        return myFunctions.formatTime(format)
    }

    fun getAlertFromSetting():LiveData<String>{
        return dataSourceViewModel.getAlertSetting()
    }

}