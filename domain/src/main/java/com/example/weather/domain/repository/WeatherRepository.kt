package com.example.weather.domain.repository

import com.example.weather.domain.model.WeatherInfo

interface WeatherRepository {
    suspend fun searchWeatherInfo(keySearch: String): List<WeatherInfo>
}