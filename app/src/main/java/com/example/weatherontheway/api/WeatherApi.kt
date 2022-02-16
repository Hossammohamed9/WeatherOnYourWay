package com.example.weatherontheway.api

import com.example.weatherontheway.models.FavData
import com.example.weatherontheway.models.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("/data/2.5/onecall?")
    fun getWeather(@Query("lat") lat: String,
                   @Query("lon") lon: String,
                   @Query("lang") lang: String,
                   @Query("appid") appid: String,
                   @Query("exclude") exclude :String,
                   @Query("units") units :String) :Call<WeatherResponse>

    @GET("/data/2.5/onecall?")
    fun getFavWeather(@Query("lat") lat: String,
                   @Query("lon") lon: String,
                   @Query("lang") lang: String,
                   @Query("appid") appid: String,
                   @Query("exclude") exclude :String,
                   @Query("units") units :String) :Call<FavData>

    @GET("/data/2.5/onecall?")
    fun geWeather2(@Query("lat") lat: String,
                      @Query("lon") lon: String,
                      @Query("lang") lang: String,
                      @Query("units") units :String) :Call<WeatherResponse>

}