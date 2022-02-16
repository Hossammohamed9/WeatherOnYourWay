package com.example.weatherontheway.ui.favorite

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherontheway.adapters.FavoriteDailyAdapter
import com.example.weatherontheway.adapters.FavoriteHourlyAdapter
import com.example.weatherontheway.databinding.ActivityFavoriteDetailsBinding
import com.example.weatherontheway.models.FavData
import com.example.weatherontheway.models.Daily
import com.example.weatherontheway.models.Hourly
import com.example.weatherontheway.ui.mainactivity.MainActivity

class FavoriteDetailsActivity : AppCompatActivity() {

    private lateinit var binding : ActivityFavoriteDetailsBinding
    private lateinit var detailsViewModel: DetailsViewModel
    private lateinit var hourlyAdapter: FavoriteHourlyAdapter
    private lateinit var dailyAdapter: FavoriteDailyAdapter
    private var layoutManager : RecyclerView.LayoutManager?= null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        detailsViewModel=  ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application))[DetailsViewModel::class.java]

        hourlyAdapter = FavoriteHourlyAdapter(detailsViewModel)
        dailyAdapter = FavoriteDailyAdapter(detailsViewModel)

        val intent: Intent = intent
        val lat=intent.getStringExtra("lat")
        val lon=intent.getStringExtra("lon")
        Log.d("TAG","${intent.getStringExtra("lat")}")
        Log.d("TAG","${intent.getStringExtra("lon")}")
        detailsViewModel.getOneFav(lat!!, lon!!).observe(this,{
            if (it!=null)
            initUI(it)
            loadHourly(it.hourly)
            loadDaily(it.daily)
            detailsViewModel.getImage(binding.favCurrentImageId, it.current.weather[0].icon)

        })

        binding.favCurrentTempUnitId.text=detailsViewModel.getUnites(MainActivity.units)


    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun initUI(it: FavData) {
        Log.d("TAG", "icon ${it.current.weather[0].icon}")
        val str = it.timezone.replace("/",", ")
        binding.favCurrentCityNameId.text = str
        binding.favCurrentDescriptionId.text = it.current.weather[0].description
        binding.favCurrentTempId.text = it.current.temp.toString()
        binding.favCurrentHumidityId.text = it.current.humidity.toString()
        binding.favCurrentWindId.text = it.current.wind_speed.toString()
        binding.favCurrentPressureId.text = it.current.pressure.toString()
        binding.favCurrentCloudsId.text = it.current.clouds.toString()
        binding.favCurrentTimeId.text = detailsViewModel.formatTime(it.current.dt)
        binding.favCurrentDateId.text = detailsViewModel.formatDate(it.current.dt)
        binding.favCurrentSunriseId.text = detailsViewModel.formatTime(it.current.sunrise)
        binding.favCurrentSunsetId.text = detailsViewModel.formatTime(it.current.sunset)
    }
    fun loadHourly(hourlyList: List<Hourly>){
        layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.favRecyclerViewHourlyId.layoutManager = layoutManager
        hourlyAdapter.models=hourlyList
        binding.favRecyclerViewHourlyId.adapter=hourlyAdapter
    }
    fun loadDaily(dailyList: List<Daily>){
        val lay : RecyclerView.LayoutManager= LinearLayoutManager(this)
        binding.favRecyclerViewDailyId.layoutManager=lay
        dailyAdapter.models=dailyList
        binding.favRecyclerViewDailyId.adapter=dailyAdapter
    }
}