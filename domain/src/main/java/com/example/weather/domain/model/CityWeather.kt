package com.example.weather.domain.model

data class City(
    val id: Long,
    val name: String,
    val coordinate: Coordinate,
    val country: String,
    val population: Long,
    val timezone: Long
)

data class Coordinate(
    val lon: Double,
    val lat: Double
)

data class WeatherElement (
    val date: Long,
    val sunrise: Long,
    val sunset: Long,
    val temperature: Temperature,
    val feelsLike: FeelsLike,
    val pressure: Long,
    val humidity: Long,
    val weather: List<Weather>,
    val speed: Double,
    val deg: Long,
    val gust: Double,
    val clouds: Long,
    val pop: Double,
    val rain: Double
)

data class FeelsLike(
    val day: Double,
    val night: Double,
    val eve: Double,
    val morn: Double
)

data class Temperature(
    val day: Double,
    val min: Double,
    val max: Double,
    val night: Double,
    val eve: Double,
    val morn: Double
)

data class Weather(
    val id: Long,
    val main: String,
    val description: String,
    val icon: String
)