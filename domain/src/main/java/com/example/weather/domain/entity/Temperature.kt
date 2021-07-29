package com.example.weather.domain.entity

data class Temperature(
    val day: Double,
    val min: Double,
    val max: Double,
    val night: Double,
    val eve: Double,
    val morn: Double
) {
    companion object {
        private const val CONVERT_K_TO_C = - 272.15
        fun Double.defaultTemperatureToCelsius() = this + CONVERT_K_TO_C
    }

    fun average() = listOf(day, min, max, night, eve, morn).average()
}