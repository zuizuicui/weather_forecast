package com.example.weather.domain.entity

data class Temperature(
    val day: Double,
    val min: Double,
    val max: Double,
    val night: Double,
    val eve: Double,
    val morn: Double
) {
    enum class TemperatureUnit(val conversionFactor: Double) {
        KELVIN(0.0),
        CELSIUS(- 272.15),
        FAHRENHEIT(-457.87)
    }

    fun average(unit: TemperatureUnit) : Double {
        return listOf(day, min, max, night, eve, morn)
            .average()
            .plus(unit.conversionFactor)
    }
}