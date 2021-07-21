package com.example.weather.data.remote

import com.example.weather.domain.model.WeatherInfo

interface WeatherApi {
    suspend fun searchWeatherInfo(keySearch: String): List<WeatherInfo>
}