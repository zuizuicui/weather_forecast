package com.example.weather.data.repository

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

    private lateinit var weatherRepository : WeatherRepositoryImpl

    @Test
    fun `test search weather info return success`() {
        val keySearch = "hanoi"

        val weatherApiSuccess : WeatherApi = fakeWeatherApiSearch(weatherListDto = weatherListDto)

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
        val weatherApiFail : WeatherApi = fakeWeatherApiSearch(responseCode = "500")

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