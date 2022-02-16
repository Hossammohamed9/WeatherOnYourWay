package com.example.weatherontheway.util

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.*

class MyFunctions{

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("ServiceCast")
    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
        }
        return false
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun getImage(imageView: ImageView, string: String) {
        Glide.with(imageView)
            .load("https://openweathermap.org/img/w/$string.png") //3
            .fitCenter() //4
            .into(imageView)
    }


    @SuppressLint("SimpleDateFormat")
    fun formatDate(format: Int): String {
        val sdf = SimpleDateFormat("MM/dd/yyyy")
        val netDate = Date(format.toLong() * 1000)
        return sdf.format(netDate)
    }

    @SuppressLint("SimpleDateFormat")
    fun formatTime(format: Int): String {
        val dateFormat = SimpleDateFormat("HH:mm a")
        val date = Date()
        date.time = format.toLong() * 1000
        return dateFormat.format(date)
    }

    fun getUnits(units: String): String {
        return when (units) {
            "standard" -> {
                "K"
            }
            "imperial" -> {
                "F"
            }
            else -> {
                "C"
            }
        }
    }

    @Suppress("DEPRECATION", "NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    fun setLocale(activity: Activity, languageCode: String?) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val resources: Resources = activity.resources
        val config: Configuration = resources.configuration
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)
    }
}