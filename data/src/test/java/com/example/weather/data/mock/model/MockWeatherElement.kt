package com.example.weather.data.mock.model

import com.example.weather.domain.entity.FeelsLike
import com.example.weather.domain.entity.Temperature
import com.example.weather.domain.entity.Weather
import com.example.weather.domain.entity.WeatherElement

object MockWeatherElement {
    val weatherElement = WeatherElement(
        date = 1627012800,
        sunrise = 1626993598,
        sunset = 1627039158,
        temperature = Temperature(
            day = 301.64,
            min = 297.12,
            max = 302.39,
            night = 298.52,
            eve = 299.69,
            morn = 298.57
        ),
        feelsLike = FeelsLike(
            day = 305.62,
            night = 299.44,
            eve = 299.69,
            morn = 299.47
        ),
        pressure = 1008,
        humidity = 75,
        weather = listOf(
            Weather(
                id = 501,
                main = "Rain",
                description = "moderate rain",
                icon = "10d"
            )
        ),
        speed = 7.7,
        deg = 267,
        gust = 12.12,
        clouds = 90,
        pop = 0.96,
        rain = 4.81
    )
}