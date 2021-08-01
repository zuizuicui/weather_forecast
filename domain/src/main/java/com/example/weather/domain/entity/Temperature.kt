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
    enum class TemperatureUnit(val calculate : (Double) -> Double) {
        KELVIN(calculate = {it}),
        CELSIUS(calculate = {it - 273.15}),
        FAHRENHEIT(calculate = {(it - 273.15)* 1.8000 + 32.00})
    }

    fun average(unit: TemperatureUnit = KELVIN) : Double {
        return listOf(day, min, max, night, eve, morn)
            .average()
            .let { unit.calculate(it) }
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