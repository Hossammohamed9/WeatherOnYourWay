package com.example.weatherontheway.models

data class Alerts(
    val description: String,
    val end: Int,
    val event: String,
    val sender_name: String,
    val start: Int
)