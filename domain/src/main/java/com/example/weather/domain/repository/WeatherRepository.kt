package com.example.weather.domain.repository

import com.example.weather.domain.entity.WeatherElement

interface WeatherRepository {
    suspend fun searchWeather(searchKey: String): List<WeatherElement>
}