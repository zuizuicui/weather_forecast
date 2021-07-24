package com.example.weather.domain.repository

import com.example.weather.domain.model.WeatherElement

interface WeatherRepository {
    suspend fun searchWeather(keySearch: String): List<WeatherElement>
}