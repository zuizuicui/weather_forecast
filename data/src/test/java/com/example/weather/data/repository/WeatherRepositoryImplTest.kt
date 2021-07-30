package com.example.weather.data.repository

import com.example.weather.data.remote.weather.WeatherApi
import com.example.weather.data.mock.api.fakeWeatherApiSearch
import com.example.weather.data.mock.converter.fakeConvertToWeatherElementDto
import com.example.weather.data.repository.dispatcher.DataDispatchers
import com.example.weather.data.remote.WeatherElementDto
import com.example.weather.domain.entity.WeatherElement
import com.example.weather.domain.entity.exception.CityNotFoundException
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

    private val weatherApiSuccess : WeatherApi = fakeWeatherApiSearch(weatherListDto = weatherListDto)
    private val weatherApiFail : WeatherApi = fakeWeatherApiSearch(responseCode = "500")

    private val dispatchers = DataDispatchers(testDispatcher, testDispatcher)
    private val weatherElementConvert = fakeConvertToWeatherElementDto(weatherListDto, weatherList)

    private lateinit var weatherRepository : WeatherRepositoryImpl

    @Before
    fun setUp() {
        weatherRepository = WeatherRepositoryImpl(
            weatherApiSuccess,
            DataDispatchers(testDispatcher, testDispatcher),
            weatherElementConvert
        )
    }

    @Test
    fun `test search weather info return success`() {
        val keySearch = "hanoi"

        weatherRepository = WeatherRepositoryImpl(
            weatherApiSuccess,
            dispatchers,
            weatherElementConvert
        )

        testDispatcher.runBlockingTest {
            val result = weatherRepository.searchWeather(keySearch)
            coVerify { weatherApiSuccess.searchWeatherInfo(keySearch) }
            coVerify { weatherElementConvert.convertToListModel(weatherListDto) }
            assertEquals(weatherList, result)
        }
    }

    @Test (expected = CityNotFoundException::class)
    fun `test search weather info return fail`() {
        val keySearch = "hanoi"

        weatherRepository = WeatherRepositoryImpl(
            weatherApiFail,
            dispatchers,
            weatherElementConvert
        )

        testDispatcher.runBlockingTest {
            weatherRepository.searchWeather(keySearch)
        }
    }
}