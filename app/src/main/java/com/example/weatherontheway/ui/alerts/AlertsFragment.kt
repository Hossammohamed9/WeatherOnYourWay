package com.example.weatherontheway.ui.alerts

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.work.*
import com.example.weatherontheway.adapters.AlertAdapter
import com.example.weatherontheway.database.room.AlertTable
import com.example.weatherontheway.databinding.FragmentAlertsBinding
import java.util.*
import java.util.concurrent.TimeUnit

const val INPUT_TIME = "input_time"
class AlertsFragment : Fragment() {

    private lateinit var alertsViewModel: AlertsViewModel
    private var _binding: FragmentAlertsBinding? = null
    private lateinit var alertAdapter: AlertAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        alertsViewModel =
            ViewModelProvider(this).get(AlertsViewModel::class.java)

        _binding = FragmentAlertsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        alertAdapter = AlertAdapter(alertsViewModel)
        val lay: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        binding.alertRecyclerView.layoutManager = lay
        binding.alertRecyclerView.adapter=alertAdapter

        binding.alertFloatingActionButton.setOnClickListener {
            val intent = Intent(activity,CreateAlert::class.java)
            startActivity(intent)
        }

        alertsViewModel.gerAlertTable().observe(this,{
            if (it!=null)
                showRecyclel(it)
        })

        alertsViewModel.getAlertSetting().observe(this,{
            if (it == "OFF"){
                binding.enableOrNot.isChecked=false
                Log.d("TAG", "offff    $it")
            }else{
                binding.enableOrNot.isChecked=true
                Log.d("TAG","onnnn    $it")
            }
        })


        binding.enableOrNot.setOnClickListener {
            alertsViewModel.saveAlertSetting(binding.enableOrNot.text.toString())
            Log.d("TAG",binding.enableOrNot.text.toString())
        }

        alertsViewModel.cancelAlert.observe(this,{
            alertsViewModel.cancelAlert(activity!!,it)
        })




        return root
    }




    private fun showRecyclel(alerts: List<AlertTable>?) {
        if (alerts!!.isNotEmpty()){
            binding.alertRecyclerView.visibility=View.VISIBLE
            alertAdapter.models = alerts
            alertAdapter.notifyDataSetChanged()
        }else{
            binding.alertRecyclerView.visibility=View.INVISIBLE
        }

    }


        fun startAlert() {

        val myIntent = Intent(activity, AlertRecever::class.java)
        val pendingIntent = PendingIntent.getBroadcast(activity, 1, myIntent, 0)
        val alarmManager: AlarmManager =
            activity!!.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val calendar: Calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()
        calendar.add(Calendar.SECOND, 400)

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, 1000, pendingIntent)

    }

    fun cancelAlarm() {
        val myIntent = Intent(activity, AlertRecever::class.java)
        val pendingIntent = PendingIntent.getBroadcast(activity, 1, myIntent, 0)
        val alarmManager: AlarmManager =
            activity!!.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.cancel(pendingIntent)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}