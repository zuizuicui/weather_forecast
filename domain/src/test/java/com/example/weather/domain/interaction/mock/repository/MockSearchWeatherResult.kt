package com.example.weather.domain.interaction.mock.repository

import com.example.weather.domain.interaction.searchweather.WeatherResultItem

object MockSearchWeatherResult {
    val searchWeatherResult =
        WeatherResultItem(
            date=1627012800,
            averageTemp=27.51,
            pressure=1008,
            humidity=75,
            description="moderate rain"
        )

    val nullObject =
        WeatherResultItem(
            date=0,
            averageTemp= 0.0,
            pressure=0,
            humidity=0,
            description=""
        )
}