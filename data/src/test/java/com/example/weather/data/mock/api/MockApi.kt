package com.example.weather.data.mock.api

import com.example.weather.data.remote.SearchWeatherResponse
import com.example.weather.data.remote.weather.WeatherApi
import com.example.weather.data.remote.WeatherElementDto
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk

fun fakeWeatherApiSearch(
    searchWeatherResponse: SearchWeatherResponse = mockk(),
    responseCode: String = "200",
    weatherListDto: List<WeatherElementDto>
) = mockk<WeatherApi>().apply {
    every { searchWeatherResponse.isSuccess()} returns (responseCode == "200")
    every { searchWeatherResponse.code} returns responseCode
    every { searchWeatherResponse.list} returns weatherListDto
    coEvery { searchWeatherInfo(any(), any(), any()) } returns searchWeatherResponse
}