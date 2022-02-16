package com.example.weatherontheway.database.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.weatherontheway.models.FavData
import com.example.weatherontheway.models.WeatherResponse

@Dao
interface WeatherDao {

    @Query("SELECT * FROM WeatherResponse")
    fun getAllData(): LiveData<List<WeatherResponse>>

    @Query("SELECT * FROM WeatherResponse")
    fun getData(): List<WeatherResponse>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAllData(allData: WeatherResponse)

    @Query("DELETE FROM WeatherResponse")
    fun deleteAll()


    //      ***** FAVORITE *****

    @Query("SELECT * FROM FavData ")
    fun getFavData(): LiveData<List<FavData>>


    @Query("SELECT * FROM FavData ")
    fun getFavDataNotLiveData(): List<FavData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveFaveData(favData: FavData)


    @Query("SELECT * FROM FavData WHERE lat LIKE:lat AND lon LIKE:lon LIMIT 1")
    fun getOneFav(lat: String, lon: String): LiveData<FavData>

    @Query("DELETE FROM FavData WHERE lat LIKE:lat AND lon LIKE:lon")
    fun deleteOneFav(lat: String, lon: String)


    @Query("DELETE FROM FavData")
    fun deleteAllFav()

    @Query("SELECT timezone FROM FavData")
    fun getTimezones(): LiveData<List<String>>

    @Query("SELECT * FROM FavData WHERE timezone LIKE:timezone LIMIT 1")
    fun getOAlerts(timezone : String): LiveData<FavData>?


    //      ***** ALERTS *****

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAlert(alertTable: AlertTable):Long

    @Query("SELECT * FROM AlertTable")
    fun getAllAlerts():LiveData<List<AlertTable>>

    @Query("DELETE FROM AlertTable WHERE id Like:id")
    fun deleteAlert(id: Long)


}