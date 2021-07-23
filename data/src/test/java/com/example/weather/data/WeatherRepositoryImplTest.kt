package com.example.weather.data

import com.example.weather.data.remote.WeatherApi
import com.example.weather.domain.model.WeatherInfo
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Test

class WeatherRepositoryImplTest {
    private val weatherInfo: List<WeatherInfo> = listOf()
    private val weatherApi = FakeWeatherApi(weatherInfo)
    private val testDispatcher = TestCoroutineDispatcher()

    @Test
    fun testSearchWeatherInfo() {
        val weatherRepository = WeatherRepositoryImpl(weatherApi, testDispatcher)
        testDispatcher.runBlockingTest {
            assertEquals(weatherInfo, weatherRepository.searchWeatherInfo(""))
        }
    }

    class FakeWeatherApi(private val weatherInfo: List<WeatherInfo>) : WeatherApi {
        override suspend fun searchWeatherInfo(
            searchKey: String,
            cnt: String,
            appid: String
        ) = weatherInfo

    }
}