package com.example.weather.data.remote.weather

import com.example.weather.data.remote.config.wrapresponse.WrapResponse
import com.example.weather.data.remote.SearchWeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("/data/2.5/forecast/daily")
    suspend fun searchWeather(
        @Query("q") searchKey: String,
        @Query("cnt") cnt: String = "7",
        @Query("appid") appId: String = "60c6fbeb4b93ac653c492ba806fc346d",
    ): WrapResponse<SearchWeatherResponse>
}