package com.example.weather.data.repository.converter

import com.example.weather.data.remote.WeatherElementDto
import com.example.weather.domain.entity.WeatherElement

interface WeatherElementConvert {
    suspend fun convertToListModel(weatherElements: List<WeatherElementDto>) : List<WeatherElement>
}

