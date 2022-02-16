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
import com.example.weatherontheway.models.Hourly
import com.example.weatherontheway.ui.favorite.DetailsViewModel
import com.example.weatherontheway.ui.mainactivity.MainActivity

class FavoriteHourlyAdapter (var detailsViewModel: DetailsViewModel): RecyclerView.Adapter<FavoriteHourlyAdapter.ViewHolder>() {

    var models: List<Hourly> = emptyList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteHourlyAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_design, parent, false)
        return ViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: FavoriteHourlyAdapter.ViewHolder, position: Int) {
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
            detailsViewModel.getImage(icon,hourly.weather[0].icon)
            description.text = hourly.weather[0].description
            temp.text = hourly.temp.toString()
            time.text = detailsViewModel.formatTime(hourly.dt)
            tempUnit.text= detailsViewModel.getUnites(MainActivity.units)
            cloud.text = hourly.clouds.toString()
            humidity.text = hourly.humidity.toString()
            wind.text = hourly.wind_speed.toString()

        }

    }
}