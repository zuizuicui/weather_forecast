package com.example.weather.data.repository

import com.example.weather.data.remote.weather.WeatherApi
import com.example.weather.data.mock.api.fakeWeatherApiSearch
import com.example.weather.data.mock.converter.fakeConvertToWeatherElementDto
import com.example.weather.data.dispatcher.DataDispatchers
import com.example.weather.data.remote.WeatherElementDto
import com.example.weather.domain.model.WeatherElement
import io.mockk.*
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class WeatherRepositoryImplTest {
    private val testDispatcher = TestCoroutineDispatcher()

    private val weatherListDto = listOf(mockk<WeatherElementDto>())
    private val weatherList = listOf(mockk<WeatherElement>())

    private val weatherApi : WeatherApi = fakeWeatherApiSearch(weatherListDto = weatherListDto)
    private val weatherElementConvert = fakeConvertToWeatherElementDto(weatherListDto, weatherList)

    private lateinit var weatherRepository : WeatherRepositoryImpl

    @Before
    fun setUp() {
        weatherRepository = WeatherRepositoryImpl(
            weatherApi,
            DataDispatchers(testDispatcher, testDispatcher),
            weatherElementConvert
        )
    }

    @Test
    fun testSearchWeatherInfo() {
        val keySearch = "hanoi"

        testDispatcher.runBlockingTest {
            val result = weatherRepository.searchWeather(keySearch)
            coVerify { weatherApi.searchWeatherInfo(keySearch) }
            verify { weatherElementConvert.convertToListModel(weatherListDto) }
            assertEquals(weatherList, result)
        }
    }
}