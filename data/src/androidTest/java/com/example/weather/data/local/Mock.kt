package com.example.weather.data.local

import com.example.weather.data.local.table.*

object Mock {
    val searchKey = SearchKeyEntity(
        searchKey = "hanoi",
        createdTime = 1000,
        expiredTime = 100
    )

    val city = CityEntity(
        id = 1,
        name = "Ha noi",
        coordinate = CoordinateEntity(
            lat = 123456.0,
            lon = 123456.0
        ),
        country = "Viet nam",
        population = 10,
        timezone = 7,
        searchKey = "hanoi"
    )

    val weather = WeatherEntity(
        id = 1,
        date = 1627012800,
        sunrise = 1626993598,
        sunset = 1627039158,
        temperature = TemperatureEntity(
            day = 301.64,
            min = 297.12,
            max = 302.39,
            night = 298.52,
            eve = 299.69,
            morn = 298.57
        ),
        feelsLike = FeelsLikeEntity(
            day = 305.62,
            night = 299.44,
            eve = 299.69,
            morn = 299.47
        ),
        pressure = 1008,
        humidity = 75,

        speed = 7.7,
        deg = 267,
        gust = 12.12,
        clouds = 90,
        pop = 0.96,
        rain = 4.81,
        searchKey = "hanoi"
    )

    val weatherIcon = WeatherIconEntity(
        id = 501,
        main = "Rain",
        description = "moderate rain",
        icon = "10d"
    )

    val weatherWeatherIconEntity = WeatherWeatherIconCrossRef(
        weatherId = 1,
        iconId = 501,
    )

    val result = SearchResult(
        searchResult = searchKey,
        weatherWithIcons = listOf(WeatherWithIcons(
            weather = weather,
            icons = listOf(weatherIcon)
        )),
        city = city
    )
}