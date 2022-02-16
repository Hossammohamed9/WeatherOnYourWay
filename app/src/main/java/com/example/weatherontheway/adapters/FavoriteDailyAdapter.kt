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
import com.example.weatherontheway.models.Daily
import com.example.weatherontheway.ui.favorite.DetailsViewModel
import com.example.weatherontheway.ui.mainactivity.MainActivity

class FavoriteDailyAdapter  (var detailsViewModel: DetailsViewModel): RecyclerView.Adapter<FavoriteDailyAdapter.ViewHolder>() {

    var models: List<Daily> = emptyList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteDailyAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_design_daily, parent, false)
        return ViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: FavoriteDailyAdapter.ViewHolder, position: Int) {
        holder.binding(models[position])
    }

    override fun getItemCount(): Int {
        return models.size
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private var time = itemView.findViewById<TextView>(R.id.dailyDayNameId)
        private var temp = itemView.findViewById<TextView>(R.id.dailyMaxTempId)
        private var tempUnit = itemView.findViewById<TextView>(R.id.dailyMinTempId)
        private var description = itemView.findViewById<TextView>(R.id.dailyDescriptionId)
        private var icon = itemView.findViewById<ImageView>(R.id.dailyImageViewId)
        private var cloud = itemView.findViewById<TextView>(R.id.dailyCloudsId)
        private var humidity = itemView.findViewById<TextView>(R.id.dailyHumidityId)

        @RequiresApi(Build.VERSION_CODES.O)
        fun binding(daily: Daily) {
            detailsViewModel.getImage(icon,daily.weather[0].icon)
            description.text = daily.weather[0].description
            temp.text = daily.temp.day.toString()
            time.text = detailsViewModel.formatDate(daily.dt)
            tempUnit.text= detailsViewModel.getUnites(MainActivity.units)
            cloud.text = daily.clouds.toString()
            humidity.text = daily.humidity.toString()

        }

    }
}