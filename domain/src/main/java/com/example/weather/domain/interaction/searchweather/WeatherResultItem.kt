package com.example.weather.domain.interaction.searchweather

data class WeatherResultItem (
    val date: Long,
    val averageTemp: Double,
    val pressure: Long,
    val humidity: Long,
    val description: String
)