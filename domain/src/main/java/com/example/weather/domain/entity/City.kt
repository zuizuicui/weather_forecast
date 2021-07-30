package com.example.weather.domain.entity

data class City(
    val id: Long,
    val name: String,
    val coordinate: Coordinate?,
    val country: String,
    val population: Long,
    val timezone: Long
)

data class Coordinate(
    val lon: Double,
    val lat: Double
)