package com.example.weatherontheway.models

import androidx.room.Entity

data class WeatherXX(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)