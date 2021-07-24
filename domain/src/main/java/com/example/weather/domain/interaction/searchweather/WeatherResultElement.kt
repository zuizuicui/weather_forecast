package com.example.weather.domain.interaction.searchweather

data class WeatherResultElement (
    val date: Long,
    val averageTemp: Double,
    val pressure: Long,
    val humidity: Long,
    val description: String
)