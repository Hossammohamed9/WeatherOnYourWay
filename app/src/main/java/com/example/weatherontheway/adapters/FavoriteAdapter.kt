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
import com.example.weatherontheway.models.FavData
import com.example.weatherontheway.ui.favorite.FavoriteViewModel

class FavoriteAdapter (var favViewModel: FavoriteViewModel,val units:String) : RecyclerView.Adapter<FavoriteAdapter.MyViewHolder>() {
    lateinit var models: List<FavData>

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var temp = itemView.findViewById<TextView>(R.id.favTempId)
        var tempUnits = itemView.findViewById<TextView>(R.id.favTempUnitId)
        var description = itemView.findViewById<TextView>(R.id.favDescId)
        var time_Zone = itemView.findViewById<TextView>(R.id.favCityId)
        var icon = itemView.findViewById<ImageView>(R.id.favModeImgId)


        @RequiresApi(Build.VERSION_CODES.O)
        fun binding(favData: FavData, position: Int) {
            favViewModel.getImage(icon, favData.current.weather[0].icon)
            description.text = favData.current.weather[0].description
            temp.text = favData.current.temp.toString()
            val str = favData.timezone.replace("/",", ")
            time_Zone.text = str
            tempUnits.text=favViewModel.getUnits(units)

            itemView.setOnClickListener{
                favViewModel.intentLiveData(position)
            }
            itemView.setOnLongClickListener{
                favViewModel.setAlertDialogLiveData(favData)
                true
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = inflater.inflate(R.layout.item_design_favorite, parent, false)
        return MyViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding(models.get(position),position)
    }

    override fun getItemCount(): Int {
        return models.size
    }
}