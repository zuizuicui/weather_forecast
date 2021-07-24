package com.example.weather.domain.interaction.mock.repository

import com.example.weather.domain.model.WeatherElement
import com.example.weather.domain.repository.WeatherRepository
import io.mockk.coEvery
import io.mockk.mockk

fun fakeWeatherRepository(
    weatherElement: List<WeatherElement>
)  = mockk<WeatherRepository>().apply {
    coEvery { searchWeather(any()) } returns weatherElement
}