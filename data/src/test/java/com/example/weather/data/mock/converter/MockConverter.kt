package com.example.weather.data.mock.converter

import com.example.weather.data.repository.converter.WeatherElementConvert
import com.example.weather.data.remote.WeatherElementDto
import com.example.weather.domain.entity.WeatherElement
import io.mockk.every
import io.mockk.mockk

fun fakeConvertToWeatherElementDto(
    weatherListDto : List<WeatherElementDto>,
    weatherList: List<WeatherElement>
) = mockk<WeatherElementConvert>().apply {
    every { convertToListModel(weatherListDto) } returns weatherList
}