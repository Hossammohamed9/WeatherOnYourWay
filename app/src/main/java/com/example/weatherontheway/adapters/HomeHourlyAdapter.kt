package com.example.weatherontheway.adapters

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherontheway.R
import com.example.weatherontheway.ui.mainactivity.MainActivity
import com.example.weatherontheway.models.Hourly
import com.example.weatherontheway.ui.home.HomeViewModel


class HomeHourlyAdapter (var homeViewModel: HomeViewModel): RecyclerView.Adapter<HomeHourlyAdapter.ViewHolder>() {

     lateinit var models: List<Hourly>


    /*private var helloAr = arrayOf("1 PM","2 PM","3 PM","4 PM","5 PM","6 PM","7 PM","8 PM","9 PM","10 PM","11 PM","12 PM","13 PM","14 PM","15 PM","16 PM","17 PM","18 PM","19 PM","20 PM","21 PM","22 PM","23 PM","24 PM")
    private var welcomeAr = arrayOf("Cloudy","Sunny","Rain","Snow","Cloudy","Sunny","Rain","Snow","Cloudy","Sunny","Rain","Snow","Cloudy","Sunny","Rain","Snow","Cloudy","Sunny","Rain","Snow","Cloudy","Sunny","Rain","Snow")
    private var textviewAr = arrayOf("21","25","22","18","23","24","16","19","21","25","22","18","23","24","16","19","21","25","22","18","23","24","16","19")*/


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = inflater.inflate(R.layout.item_design, parent, false)
        return ViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding(models[position])
    }

    override fun getItemCount(): Int {
        return models.size
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private var time = itemView.findViewById<TextView>(R.id.hourlyTime)
        private var temp = itemView.findViewById<TextView>(R.id.hourlyTemp)
        private var tempUnit = itemView.findViewById<TextView>(R.id.hourlyTempUnit)
        private var description = itemView.findViewById<TextView>(R.id.hourlyDesc)
        private var icon = itemView.findViewById<ImageView>(R.id.hourlyModeImage)
        private var cloud = itemView.findViewById<TextView>(R.id.hourlyClouds)
        private var humidity = itemView.findViewById<TextView>(R.id.hourlyHumidity)
        private var wind = itemView.findViewById<TextView>(R.id.hourlyWind)



        @RequiresApi(Build.VERSION_CODES.O)
        fun binding(hourly: Hourly) {
            homeViewModel.getImage(icon,hourly.weather[0].icon)
            description.text = hourly.weather[0].description
            temp.text = hourly.temp.toString()
            time.text = homeViewModel.formatTime(hourly.dt)
            tempUnit.text=homeViewModel.getUnites(MainActivity.units)
            cloud.text = hourly.clouds.toString()
            humidity.text = hourly.humidity.toString()
            wind.text = hourly.wind_speed.toString()


        }

    }
}