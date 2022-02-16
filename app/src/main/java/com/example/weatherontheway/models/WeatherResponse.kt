package com.example.weatherontheway.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class WeatherResponse(
    val alerts: List<Alerts>?,
    val current: Current,
    val daily: List<Daily>,
    val hourly: List<Hourly>,
    val lat: Double,
    val lon: Double,
    @PrimaryKey
    val timezone: String,
    val timezone_offset: Int
    )