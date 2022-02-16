package com.example.weatherontheway.api


class Repository(private val weatherApi: WeatherApi) {
    fun getOneCall(lat: String,lon: String,lang: String, appid: String,exclude :String,units :String) =
        weatherApi.getWeather(lat,lon,lang,appid,exclude,units)
    fun getFavCall(lat: String,lon: String,lang: String, appid: String,exclude :String,units :String) =
        weatherApi.getFavWeather(lat,lon,lang,appid,exclude,units)

}