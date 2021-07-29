package com.example.weather.domain.entity

import io.mockk.mockk
import org.junit.Assert
import org.junit.Test

class SelectShowingWeatherTest {
    val selectShowingWeather = SelectShowingWeather()

    @Test
    fun testSelectShowingWeather_shouldSelectFirstOne() {
        val weather1: Weather = mockk()
        val weather2: Weather = mockk()
        val weather3: Weather = mockk()

        val weatherList = listOf(weather1, weather2, weather3)

        Assert.assertEquals(weather1, selectShowingWeather(weatherList))
        Assert.assertNotEquals(weather2, selectShowingWeather(weatherList))
        Assert.assertNotEquals(weather3, selectShowingWeather(weatherList))
    }
}