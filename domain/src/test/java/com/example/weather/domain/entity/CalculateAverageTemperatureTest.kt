package com.example.weather.domain.entity

import org.junit.Assert
import org.junit.Test

class CalculateAverageTemperatureTest {
    val sut = CalculateAverageTemperature()

    @Test
    fun testCalculateAverageTemperature_shouldReturnAverageValue() {
        val temperature = Temperature (
            day = 304.84,
            min = 297.38,
            max = 305.46,
            night = 299.44,
            eve = 303.92,
            morn = 297.38
        )

        Assert.assertEquals(29.25, sut(temperature), 0.01)
    }
}