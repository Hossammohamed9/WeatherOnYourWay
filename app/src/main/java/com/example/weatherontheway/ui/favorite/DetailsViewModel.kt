package com.example.weatherontheway.ui.favorite

import android.annotation.SuppressLint
import android.app.Application
import android.os.Build
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import com.example.weatherontheway.api.DataSourceViewModel
import com.example.weatherontheway.util.MyFunctions

class DetailsViewModel(application: Application) : AndroidViewModel(application) {
    private val mApplication: Application=application
    private val dataSourceViewModel: DataSourceViewModel = DataSourceViewModel(mApplication)
    private val myFunctions : MyFunctions = MyFunctions()
    fun getOneFav(lat: String,lon: String)= dataSourceViewModel.getOneFav(lat,lon)

    fun saveFave(lat: String,lon: String,lang: String,units :String){
        dataSourceViewModel.saveFave(lat,lon,lang,units)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getImage(imageView: ImageView, string: String) {
        myFunctions.getImage(imageView,string)
    }
    @SuppressLint("SimpleDateFormat")
    fun formatTime(format: Int): String {
        return myFunctions.formatTime(format)

    }
    @SuppressLint("SimpleDateFormat")
    fun formatDate(format: Int): String {
        return myFunctions.formatDate(format)
    }
    fun getUnites(units: String): String {
        return myFunctions.getUnits(units)
    }
}