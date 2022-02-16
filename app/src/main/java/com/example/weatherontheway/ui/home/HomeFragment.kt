package com.example.weatherontheway.ui.home

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherontheway.R
import com.example.weatherontheway.adapters.HomeHourlyAdapter
import com.example.weatherontheway.adapters.HomeDailyAdapter
import com.example.weatherontheway.database.sharedPreference.SettingsModel
import com.example.weatherontheway.databinding.FragmentHomeBinding
import com.example.weatherontheway.ui.mainactivity.MainActivity
import com.example.weatherontheway.models.Alerts
import com.example.weatherontheway.models.Daily
import com.example.weatherontheway.models.Hourly
import com.example.weatherontheway.models.WeatherResponse
import com.example.weatherontheway.ui.notification.NotificationHelper

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var hourlyAdapter: HomeHourlyAdapter
    private lateinit var dailyAdapter: HomeDailyAdapter
    private lateinit var notificationHelper: NotificationHelper
    private var layoutManager : RecyclerView.LayoutManager?= null


    // This property is only valid between onCreateView and
    // onDestroyView.

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        homeViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        )[HomeViewModel::class.java]

        reload()

        hourlyAdapter = HomeHourlyAdapter(homeViewModel)

        dailyAdapter = HomeDailyAdapter(homeViewModel)


        binding.reload.setOnClickListener {
            Log.d("TAG", "clicked")
            reload()
        }

        homeViewModel.getRoomData().observe(this, {
            if (it.size == 1) {
                initUI(it[0])
                loadHourly(it[0].hourly)
                loadDaily(it[0].daily)
                homeViewModel.getImage(binding.currentImageId, it[0].current.weather[0].icon)
            }
        })


        return binding.root
    }





    @RequiresApi(Build.VERSION_CODES.O)
    private fun initUI(it: WeatherResponse) {
        Log.d("TAG", "icon ${it.current.weather[0].icon}")
        /*val str = it.timezone.split("/")
        binding.currentCityNameId.text = str[1]*/
        val str = it.timezone.replace("/",", ")
        binding.currentCityNameId.text = str
        binding.currentDescriptionId.text = it.current.weather[0].description
        binding.currentTempId.text = it.current.temp.toString()
        binding.currentHumidityId.text = it.current.humidity.toString()
        binding.currentWindId.text = it.current.wind_speed.toString()
        binding.currentPressureId.text = it.current.pressure.toString()
        binding.currentCloudsId.text = it.current.clouds.toString()
        binding.currentTimeId.text = homeViewModel.formatTime(it.current.dt)
        binding.currentDateId.text = homeViewModel.formatDate(it.current.dt)
        binding.currentSunriseId.text = homeViewModel.formatTime(it.current.sunrise)
        binding.currentSunsetId.text = homeViewModel.formatTime(it.current.sunset)
    }

    private fun loadHourly(hourlyList: List<Hourly>) {
        layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.homeRecyclerViewHourlyId.layoutManager = layoutManager
        hourlyAdapter.models = hourlyList
        binding.homeRecyclerViewHourlyId.adapter = hourlyAdapter
    }

    private fun loadDaily(dailyList: List<Daily>) {
        val lay: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        binding.homeRecyclerViewDailyId.layoutManager = lay
        dailyAdapter.models = dailyList
        binding.homeRecyclerViewDailyId.adapter = dailyAdapter
    }


    @RequiresApi(Build.VERSION_CODES.M)
    private fun reload() {
        Log.d("TAG", "reload")
        lateinit var settingModel: SettingsModel
        homeViewModel.getSetting().observe(this, { it ->
            Log.d("TAG", "it.lang" + it.lang)
            settingModel = it
            MainActivity.units = settingModel.units
            binding.currentTempUnitId.text = homeViewModel.getUnites(settingModel.units)


            if (settingModel.location == "gps") {
                homeViewModel.gettingLocation(this.requireContext(), activity!!).observe(this, {
                    val location = it
                    Log.d("TAG", "it.location" + location.latitude)
                    homeViewModel.loadOnlineData(
                        location.latitude.toString(),
                        location.longitude.toString(),
                        settingModel.lang,
                        settingModel.units,
                        activity!!
                    )
                })

            } else {
                homeViewModel.getLocationSettnig().observe(this, {
                    Log.d("TAG", "it.latitude" + it.latitude)
                    Log.d("TAG", "it.latitude" + it.longitude)

                    homeViewModel.loadOnlineData(
                        it.latitude.toString(),
                        it.longitude.toString(),
                        settingModel.lang,
                        settingModel.units,
                        activity!!
                    )
                })

            }
        })
    }


}