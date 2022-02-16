package com.example.weatherontheway.ui.alerts

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.weatherontheway.R
import com.example.weatherontheway.databinding.MakeAlertBinding
import java.util.*


class CreateAlert : AppCompatActivity() {
    private lateinit var activityCreateAlermBinding: MakeAlertBinding
    private lateinit var createAlermViewModel: CreateAlertViewModel
    private var zhour: Int? = null
    private var zmin: Int? = null
    private var zmonth: Int? = null
    private var zday: Int? = null
    private var zyear: Int? = null
    private val startCalendar: Calendar = Calendar.getInstance()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityCreateAlermBinding = DataBindingUtil.setContentView(
            this,
            R.layout.make_alert
        )

        createAlermViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(this.application)
        )[CreateAlertViewModel::class.java]

        activityCreateAlermBinding.timePickerStart.setOnClickListener {
            getTime()

        }

        activityCreateAlermBinding.datePickerStart.setOnClickListener {
            getDate()
        }


        activityCreateAlermBinding.saveButton.setOnClickListener {
            createAlermViewModel.saveData(
                title = activityCreateAlermBinding.alertTitle.text.toString(),
                resources.getStringArray(R.array.alert)[activityCreateAlermBinding.alertSpinner.selectedItemPosition],
                reputation = activityCreateAlermBinding.checkboxReputation.isChecked,
                time = "${activityCreateAlermBinding.startTimeText.text}  ${activityCreateAlermBinding.startDateText.text}"
            )

        }



        createAlermViewModel.idLiveData.observe(this,{
            if (it!=null){
                createAlermViewModel.setAlaram(this, zhour!!, zmin!!, zmonth!!, zday!!, zyear!!
                    ,type = resources.getStringArray(R.array.alert)[activityCreateAlermBinding.alertSpinner.selectedItemPosition],
                    reputation = activityCreateAlermBinding.checkboxReputation.isChecked,it)
            }
        })

        createAlermViewModel.getDataSavedOrNot().observe(this, {
            if (it) {
                finish()
            } else {
                Toast.makeText(this, getString(R.string.missing_details), Toast.LENGTH_SHORT)
                    .show()

            }
        })
    }


    @SuppressLint("SetTextI18n")
    private fun getDate() {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(
            this, { _, yearA, monthOfYear, dayOfMonth ->
                zmonth = monthOfYear + 1
                zyear = yearA
                zday = dayOfMonth
                startCalendar[Calendar.MONTH] = month - 1
                startCalendar[Calendar.DATE] = dayOfMonth
                startCalendar[Calendar.YEAR] = year
                activityCreateAlermBinding.startDateText.text = "$dayOfMonth-${month+1}-$year"
            }, year, month, day
        )
        dpd.datePicker.minDate = System.currentTimeMillis() - 1000
        dpd.show()
    }

    @SuppressLint("SetTextI18n")
    private fun getTime() {
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR)
        val minute = c.get(Calendar.MINUTE)
        val tpd = TimePickerDialog(
            this,
            { _, h, m ->
                c[Calendar.HOUR_OF_DAY] = h
                c[Calendar.MINUTE] = m
                    zhour = h
                    zmin = m
                    startCalendar.set(Calendar.HOUR_OF_DAY, h)
                    startCalendar.set(Calendar.MINUTE, m)
                    startCalendar[Calendar.SECOND] = 0
                    activityCreateAlermBinding.startTimeText.text = "$h:$m"

            }, hour, minute, false
        )
        tpd.show()
    }

}


