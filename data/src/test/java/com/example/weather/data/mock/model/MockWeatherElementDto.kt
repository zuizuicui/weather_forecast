package com.example.weather.data.mock.model

import com.example.weather.data.remote.FeelsLikeDto
import com.example.weather.data.remote.TemperatureDto
import com.example.weather.data.remote.WeatherDto
import com.example.weather.data.remote.WeatherElementDto

object MockWeatherElementDto {
    val weatherElementDto = WeatherElementDto(
        date = 1627012800,
        sunrise = 1626993598,
        sunset = 1627039158,
        temperature = TemperatureDto(
            day = 301.64,
            min = 297.12,
            max = 302.39,
            night = 298.52,
            eve = 299.69,
            morn = 298.57
        ),
        feelsLike = FeelsLikeDto(
            day = 305.62,
            night = 299.44,
            eve = 299.69,
            morn = 299.47
        ),
        pressure = 1008,
        humidity = 75,
        weather = listOf(
            WeatherDto(
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

    val nullObjectWithNestedField = WeatherElementDto(
        date = null,
        sunrise = null,
        sunset = null,
        temperature = TemperatureDto(
            day = null,
            min = null,
            max = null,
            night = null,
            eve = null,
            morn = null
        ),
        feelsLike = FeelsLikeDto(
            day = null,
            night = null,
            eve = null,
            morn = null
        ),
        pressure = null,
        humidity = null,
        weather = listOf(
            WeatherDto(
                id = null,
                main = null,
                description = null,
                icon = null
            )
        ),
        speed = null,
        deg = null,
        gust = null,
        clouds = null,
        pop = null,
        rain = null
    )

    val nullAllField = WeatherElementDto(
        date = null,
        sunrise = null,
        sunset = null,
        temperature = null,
        feelsLike = null,
        pressure = null,
        humidity = null,
        weather = null,
        speed = null,
        deg = null,
        gust = null,
        clouds = null,
        pop = null,
        rain = null
    )
}