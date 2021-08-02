package com.example.weather.data.repository

import com.example.weather.data.local.WeatherDao
import com.example.weather.data.remote.weather.WeatherApi
import com.example.weather.data.mock.api.fakeWeatherApiSearch
import com.example.weather.data.mock.converter.fakeConvertToWeatherElementDto
import com.example.weather.data.repository.dispatcher.DataDispatchers
import com.example.weather.data.remote.WeatherElementDto
import com.example.weather.domain.entity.WeatherElement
import com.example.weather.domain.entity.exception.CityNotFoundException
import com.example.weather.domain.entity.exception.NetworkErrorException
import com.example.weather.domain.entity.exception.UnKnowException
import io.mockk.*
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Test
import java.lang.RuntimeException

class WeatherRepositoryImplTest {
    private val testDispatcher = TestCoroutineDispatcher()

    private val weatherListDto = listOf(mockk<WeatherElementDto>())
    private val weatherList = listOf(mockk<WeatherElement>())

    private val dispatchers = DataDispatchers(testDispatcher, testDispatcher)
    private val weatherElementConvert = fakeConvertToWeatherElementDto(weatherListDto, weatherList)
    private val weatherDao : WeatherDao = mockk()

    private lateinit var weatherRepository : WeatherRepositoryImpl

    @Test
    fun searchWeather_shouldReturnSuccess() {
        val searchKey = "hanoi"

        val weatherApiSuccess : WeatherApi = fakeWeatherApiSearch(weatherListDto = weatherListDto)

        weatherRepository = WeatherRepositoryImpl(
            weatherApiSuccess,
            dispatchers,
            weatherElementConvert,
            weatherDao
        )

        testDispatcher.runBlockingTest {
            val result = weatherRepository.searchWeather(searchKey)
            coVerify { weatherApiSuccess.searchWeather(searchKey) }
            coVerify { weatherElementConvert.convertToListModel(weatherListDto) }
            assertEquals(weatherList, result)
        }
    }

    @Test (expected = CityNotFoundException::class)
    fun searchWeather_shouldReturnFail() {
        val searchKey = "hanoi"
        val weatherApiFail : WeatherApi = fakeWeatherApiSearch(cityNotFound = true)

        weatherRepository = WeatherRepositoryImpl(
            weatherApiFail,
            dispatchers,
            weatherElementConvert,
            weatherDao
        )

        testDispatcher.runBlockingTest {
            weatherRepository.searchWeather(searchKey)
        }
    }
}