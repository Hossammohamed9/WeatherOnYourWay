package com.example.weatherontheway.ui.favorite

import android.app.Application
import android.content.Context
import android.os.Build
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import com.example.weatherontheway.api.DataSourceViewModel
import com.example.weatherontheway.models.FavData
import com.example.weatherontheway.util.MyFunctions

class FavoriteViewModel (application: Application) : AndroidViewModel(application) {
    private val dataSourceViewModel: DataSourceViewModel = DataSourceViewModel(application)
    private val intentLiveData: MutableLiveData<Int> = MutableLiveData<Int>()
    private val alertDialogLiveData: MutableLiveData<FavData> = MutableLiveData<FavData>()
    private val myFunctions : MyFunctions = MyFunctions()
    fun deleteOneFav(lat: String, lon: String)= dataSourceViewModel.deleteOneFav(lat,lon)

    fun getFavDataBase(): LiveData<List<FavData>> {
        return dataSourceViewModel.getFavDataBase()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getImage(imageView: ImageView, string: String) {
        Glide.with(imageView)  //2
            .load("https://openweathermap.org/img/wn/$string@2x.png") //3
            .centerCrop() //4
            .into(imageView)
    }


    fun intentLiveData(position : Int){
        intentLiveData.value=position
    }

    fun getIntent():LiveData<Int>{
        return intentLiveData
    }
    fun setAlertDialogLiveData(position : FavData){
        alertDialogLiveData.value=position
    }

    fun getAlertDialogLiveData():LiveData<FavData>{
        return alertDialogLiveData
    }
    @RequiresApi(Build.VERSION_CODES.M)
    fun getOnline(context: Context) : Boolean{
        return myFunctions.isOnline(context)
    }
    fun getUnits(units: String): String {
        return myFunctions.getUnits(units)
    }
}
