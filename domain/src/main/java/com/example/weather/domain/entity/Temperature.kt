package com.example.weather.domain.entity

import com.example.weather.domain.entity.Temperature.TemperatureUnit.KELVIN
import kotlin.math.roundToInt

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

    fun average(unit: TemperatureUnit = KELVIN) : Double {
        return listOf(day, min, max, night, eve, morn)
            .average()
            .plus(unit.conversionFactor)
            .round(decimals = 2)
    }

    private fun Double.round(decimals: Int): Double {
        return if (this.isNaN()) this
        else {
            var multiplier = 1.0
            repeat(decimals) { multiplier *= 10 }
            (this * multiplier).roundToInt() / multiplier
        }
    }
}