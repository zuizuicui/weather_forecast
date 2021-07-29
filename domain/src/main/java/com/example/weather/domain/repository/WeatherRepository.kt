package com.example.weather.domain.repository

import com.example.weather.domain.entity.WeatherElement

interface WeatherRepository {
    suspend fun searchWeather(keySearch: String): List<WeatherElement>
}