package com.example.weather.data.repository.converter

import com.example.weather.data.local.table.WeatherWithIcons
import com.example.weather.data.remote.WeatherElementDto
import com.example.weather.domain.entity.WeatherElement

interface WeatherElementConvert {
    suspend fun convertToListModel(weatherElements: List<WeatherElementDto>) : List<WeatherElement>
    suspend fun convertEntityToListModel(weatherEntity: List<WeatherWithIcons>) : List<WeatherElement>
}

