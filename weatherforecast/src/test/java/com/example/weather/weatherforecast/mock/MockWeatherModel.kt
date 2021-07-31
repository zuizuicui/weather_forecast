package com.example.weather.weatherforecast.mock

import com.example.weather.domain.interaction.searchweather.WeatherResultItem
import com.example.weather.weatherforecast.ui.searchweather.WeatherModel

object MockWeatherModel {

    fun createWeatherResult(
        date: Long = 1627272000,
        averageTemp: Double = 30.0,
        pressure: Long = 100,
        humidity: Long = 70,
        description: String = "moderate rain"
    ) = WeatherResultItem(
        date, averageTemp, pressure, humidity, description
    )

    fun createWeatherModel(
        date: String = "Mon, 26 Jul 2021",
        averageTemp: String = "30",
        pressure: String = "100",
        humidity: String = "70",
        description: String = "moderate rain"
    ) = WeatherModel (
        date, averageTemp, pressure, humidity, description
    )
}