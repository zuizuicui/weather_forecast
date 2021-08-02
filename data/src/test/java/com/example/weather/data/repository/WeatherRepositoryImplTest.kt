package com.example.weather.data.repository

import com.example.weather.data.mock.api.fakeWeatherApiSearch
import com.example.weather.data.mock.cache.MockCache
import com.example.weather.data.mock.converter.fakeConvertToWeatherElementDto
import com.example.weather.data.remote.SearchWeatherResponse
import com.example.weather.data.remote.WeatherElementDto
import com.example.weather.data.remote.weather.WeatherApi
import com.example.weather.data.repository.dispatcher.DataDispatchers
import com.example.weather.domain.entity.WeatherElement
import com.example.weather.domain.entity.exception.CityNotFoundException
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Test

class WeatherRepositoryImplTest {
    private val testDispatcher = TestCoroutineDispatcher()

    private val weatherListDto = listOf(mockk<WeatherElementDto>())
    private val weatherList = listOf(mockk<WeatherElement>())

    private val dispatchers = DataDispatchers(testDispatcher, testDispatcher)
    private val weatherElementConvert = fakeConvertToWeatherElementDto(weatherListDto, weatherList)
    private val cache = spyk(MockCache.fakeCache(null))

    private lateinit var weatherRepository : WeatherRepositoryImpl

    @Test
    fun searchWeather_shouldReturnSuccess() {
        val searchKey = "hanoi"
        val searchWeatherResponse: SearchWeatherResponse = mockk()
        val weatherApiSuccess : WeatherApi = fakeWeatherApiSearch(searchWeatherResponse, weatherListDto)

        weatherRepository = WeatherRepositoryImpl(
            weatherApiSuccess,
            dispatchers,
            weatherElementConvert,
            cache
        )

        testDispatcher.runBlockingTest {
            val result = weatherRepository.searchWeather(searchKey)

            coVerify { cache.saveResponseToCache(searchKey, searchWeatherResponse) }
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
            cache
        )

        testDispatcher.runBlockingTest {
            weatherRepository.searchWeather(searchKey)

            coVerify { cache.retrieveResponseFromCache<SearchWeatherResponse>(searchKey) }
            coVerify(exactly = 0) { cache.saveResponseToCache(searchKey, any()) }
        }
    }

    @Test
    fun searchWeather_shouldFromCache() {
        val searchKey = "hanoi"
        val searchWeatherResponse = MockCache.createResponse(weatherListDto)
        val weatherApiSuccess : WeatherApi = fakeWeatherApiSearch(weatherListDto = weatherListDto)
        val cache = MockCache.fakeCache(searchWeatherResponse)

        weatherRepository = WeatherRepositoryImpl(
            weatherApiSuccess,
            dispatchers,
            weatherElementConvert,
            cache
        )

        testDispatcher.runBlockingTest {
            val result = weatherRepository.searchWeather(searchKey)
            coVerify(exactly = 0) { weatherApiSuccess.searchWeather(searchKey) }
            coVerify { weatherElementConvert.convertToListModel(weatherListDto) }
            assertEquals(weatherList, result)
        }
    }
}