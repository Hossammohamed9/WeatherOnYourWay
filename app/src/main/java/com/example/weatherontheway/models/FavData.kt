package com.example.weatherontheway.models

import androidx.room.Entity
import com.example.weatherontheway.models.Alerts
import com.example.weatherontheway.models.Current
import com.example.weatherontheway.models.Daily
import com.example.weatherontheway.models.Hourly

@Entity(primaryKeys = arrayOf("lat","lon"))
data class FavData(
    val lat: Double,
    val lon: Double,
    val timezone: String,
    val timezone_offset: Int,
    val current: Current,
    val hourly: List<Hourly>,
    val daily: List<Daily>,
    val alerts: List<Alerts>?)