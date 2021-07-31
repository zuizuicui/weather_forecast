package com.example.weather.domain.entity

import com.example.weather.domain.entity.Temperature.TemperatureUnit.*
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class CalculateAverageTemperatureTest {

    @Test
    fun testCalculateAverageTemperature_shouldValueInCelsius() {
        val temperature = createTemperature()
        val averageTemperatureInCelsius =
            temperature.average(CELSIUS)
        Assert.assertEquals(29.25, averageTemperatureInCelsius, 0.01)
    }

    @Test
    fun testCalculateAverageTemperature_shouldValueInKelvin() {
        val temperature = createTemperature()
        val averageTemperatureInKelvin =
            temperature.average(KELVIN)
        Assert.assertEquals(29.25, averageTemperatureInKelvin, 0.01)
    }

    @Test
    fun testCalculateAverageTemperature_shouldValueInFahrenheit() {
        val temperature = createTemperature()
        val averageTemperatureInFahrenheit = temperature.average(FAHRENHEIT)
        Assert.assertEquals(29.25, averageTemperatureInFahrenheit, 0.01)
    }

    @Test
    fun testCalculateAverageTemperature_withNaN() {
        val temperature = createTemperature(
            day = Double.NaN,
            min = Double.NaN,
            max = Double.NaN,
            night = Double.NaN,
            eve = Double.NaN,
            morn = Double.NaN
        )
        val averageTemperatureInFahrenheit = temperature.average(FAHRENHEIT)
        Assert.assertEquals(Double.NaN, averageTemperatureInFahrenheit, 0.01)
    }

    private fun createTemperature(
        day : Double = 304.84,
        min :Double = 297.38,
        max :Double = 305.46,
        night :Double = 299.44,
        eve :Double = 303.92,
        morn :Double = 297.38
    ) = Temperature (
        day, min, max, night, eve, morn
    )
}