package com.example.weatherontheway.database.room

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.weatherontheway.models.FavData
import com.example.weatherontheway.models.WeatherResponse

@TypeConverters(Converters::class)
@Database(entities = [WeatherResponse::class, FavData::class,AlertTable::class], version = 1,exportSchema = false)
abstract class WeatherDatabase : RoomDatabase() {
    companion object{
        @Volatile
        private var db :WeatherDatabase? =null

        fun getInstance(application: Application): WeatherDatabase? {
            synchronized(this) {
                if (db == null)
                    db = Room.databaseBuilder(
                        application, WeatherDatabase::class.java, "Weather"
                    ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
            }
            return db
        }

    }


    abstract fun weatherDao(): WeatherDao

}