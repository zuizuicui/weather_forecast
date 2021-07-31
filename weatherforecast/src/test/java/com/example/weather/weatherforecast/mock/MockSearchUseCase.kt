package com.example.weather.weatherforecast.mock

import com.example.weather.domain.interaction.searchweather.SearchWeatherInfoUseCase
import com.example.weather.domain.interaction.searchweather.WeatherResultItem
import io.mockk.coEvery
import io.mockk.mockk

object MockSearchUseCase {
    fun mockSearchUseCase (
        weatherResult: List<WeatherResultItem> = emptyList()
    )  = mockk<SearchWeatherInfoUseCase>().also {
        coEvery { it(any()) } returns weatherResult
    }
}