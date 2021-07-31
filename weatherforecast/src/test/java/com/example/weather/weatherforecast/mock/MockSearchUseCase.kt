package com.example.weather.weatherforecast.mock

import com.example.weather.domain.interaction.searchweather.SearchWeatherInfoUseCase
import com.example.weather.domain.interaction.searchweather.WeatherResultItem
import io.mockk.coEvery
import io.mockk.mockk
import java.lang.Exception

object MockSearchUseCase {
    fun mockSearchUseCase (
        weatherResult: List<WeatherResultItem> = emptyList(),
        exception: Exception? = null
    )  = mockk<SearchWeatherInfoUseCase>().also {
        if (exception != null) {
            coEvery { it(any()) } throws exception
        } else {
            coEvery { it(any()) } returns weatherResult
        }
    }
}