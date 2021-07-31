package com.example.weather.weatherforecast.mock

import com.example.weather.weatherforecast.ui.searchweather.WeatherModel

object MockSearchWeatherViewModel {
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