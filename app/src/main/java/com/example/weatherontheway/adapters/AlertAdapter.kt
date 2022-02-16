package com.example.weatherontheway.adapters

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherontheway.R
import com.example.weatherontheway.database.room.AlertTable
import com.example.weatherontheway.ui.alerts.AlertsViewModel

class AlertAdapter(var alertsViewModel: AlertsViewModel) : RecyclerView.Adapter<AlertAdapter.MyViewHolder>() {
     var models: List<AlertTable> = emptyList()

    inner class MyViewHolder( itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val alertTitle : TextView = itemView.findViewById(R.id.alertTitleId)
        private val alertDate : TextView = itemView.findViewById(R.id.dateId)
        private val alertType : TextView = itemView.findViewById(R.id.alertTypeId)
        private val deleteAlert : ImageView=itemView.findViewById(R.id.deleteAlertId)


        @SuppressLint("SetTextI18n")
        @RequiresApi(Build.VERSION_CODES.O)
        fun binding(allData: AlertTable) {
            alertTitle.text=allData.title
            alertType.text=allData.type
            alertDate.text=allData.time
            deleteAlert.setOnClickListener {
                alertsViewModel.cancelAlert.value=allData.id
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = inflater.inflate(R.layout.item_design_alert, parent, false)
        return MyViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding(models[position])
    }

    override fun getItemCount(): Int {
        return models.size
    }
}