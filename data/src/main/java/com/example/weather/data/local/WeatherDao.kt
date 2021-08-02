package com.example.weather.data.local

import androidx.room.*
import com.example.weather.data.local.table.*

@Dao
interface WeatherDao {
    @Query("SELECT * FROM SearchKeyEntity WHERE searchKey = :searchKey LIMIT 1")
    fun getSearchKey(searchKey: String): SearchKeyEntity?

    @Transaction
    @Query("SELECT * FROM SearchKeyEntity WHERE searchKey = :searchKey LIMIT 1")
    fun getSearchResult(searchKey: String): SearchResult?

    @Delete
    fun deletedSearchKey(searchKey: SearchKeyEntity)

    @Delete
    fun deletedSearchKey(searchKeys: List<SearchKeyEntity>)

    @Insert
    fun insertSearchKey(searchKey: SearchKeyEntity)

    @Insert
    fun insertCity(city: CityEntity)

    @Insert
    fun insertWeatherIcons(weatherIcons: List<WeatherIconEntity>)

    @Insert
    fun insertWeather(weather: List<WeatherEntity>)

    @Query("SELECT weather_id FROM WeatherEntity ORDER BY weather_id DESC LIMIT 1 ")
    fun getLastWeatherKey() : Long

    @Insert
    fun insertWeatherWeatherItemCrossRef(weatherWeatherItemCrossRef: List<WeatherWeatherIconCrossRef>)
}