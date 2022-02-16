package com.example.weatherontheway.api

import com.example.weatherontheway.util.Constants.Companion.BASE_URL
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory


object WeatherClient {
    var gson = GsonBuilder()
        .setLenient()
        .create()
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create()) //important
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    val apiService: WeatherApi = getRetrofit().create(WeatherApi::class.java)
}
