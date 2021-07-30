package com.example.weather.domain.entity

import com.example.weather.domain.entity.Temperature.TemperatureUnit.*
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class CalculateAverageTemperatureTest {

    private lateinit var temperature : Temperature

    @Before
    fun setup() {
        temperature = Temperature (
            day = 304.84,
            min = 297.38,
            max = 305.46,
            night = 299.44,
            eve = 303.92,
            morn = 297.38
        )
    }

    @Test
    fun testCalculateAverageTemperature_shouldValueInCelsius() {
        val averageTemperatureInCelsius =
            temperature.average(CELSIUS)
        Assert.assertEquals(29.25, averageTemperatureInCelsius, 0.01)
    }

    @Test
    fun testCalculateAverageTemperature_shouldValueInKelvin() {
        val averageTemperatureInKelvin =
            temperature.average(KELVIN)
        Assert.assertEquals(29.25, averageTemperatureInKelvin, 0.01)
    }

    @Test
    fun testCalculateAverageTemperature_shouldValueInFahrenheit() {
        val averageTemperatureInFahrenheit = temperature.average(FAHRENHEIT)
        Assert.assertEquals(29.25, averageTemperatureInFahrenheit, 0.01)
    }
}