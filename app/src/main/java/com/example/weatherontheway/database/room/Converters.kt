package com.example.weatherontheway.database.room

import androidx.room.TypeConverter
import com.example.weatherontheway.models.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    //      *** CONVERTERS FOR CURRENT HOME WEATHER ***

    @TypeConverter
    fun fromCurrentToGson(list :Current) : String{
        return Gson().toJson(list)
    }
    @TypeConverter
    fun fromJsonToCurrent(gson: String):Current{
        return Gson().fromJson(gson,Current::class.java)
    }

    //      *** CONVERTERS FOR HOURLY HOME WEATHER ***
    @TypeConverter
    fun fromHourlyToGson(list :List<Hourly>) : String{
        return Gson().toJson(list)
    }
    @TypeConverter
    fun fromJsonToHourly(gson: String):List<Hourly>{
        val type = object : TypeToken<List<Hourly>>() {}.type
        return Gson().fromJson(gson,type)
    }

    //      *** CONVERTERS FOR DAILY HOME WEATHER ***
    @TypeConverter
    fun fromDailyToGson(list :List<Daily>) : String{
        return Gson().toJson(list)
    }
    @TypeConverter
    fun fromJsonToDaily(gson: String):List<Daily>{
        val type = object : TypeToken<List<Daily>>() {}.type
        return Gson().fromJson(gson,type)
    }

    //      *** CONVERTERS FOR HOME WEATHER ALERTS ***
    @TypeConverter
    fun fromAlertToGson(list :List<Alerts>?) : String{
        return Gson().toJson(list)
    }
    @TypeConverter
    fun fromJsonToAlert(gson: String):List<Alerts>?{
        val type = object : TypeToken<List<Alerts>?>() {}.type
        return Gson().fromJson(gson,type)
    }


}