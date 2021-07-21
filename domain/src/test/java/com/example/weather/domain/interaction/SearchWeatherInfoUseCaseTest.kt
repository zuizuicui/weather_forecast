package com.example.weather.domain.interaction

import com.example.weather.domain.model.WeatherInfo
import com.example.weather.domain.repository.WeatherRepository
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Test

class SearchWeatherInfoUseCaseTest {
    private val weatherInfo: List<WeatherInfo> = listOf()
    private val weatherRepository = FakeWeatherRepository(weatherInfo)
    private val searchWeatherInfoUseCase = SearchWeatherInfoUseCase(weatherRepository)
    private val testDispatcher = TestCoroutineDispatcher()

    @Test
    fun testSearchWeather() {
        testDispatcher.runBlockingTest {
            assertEquals(weatherInfo, searchWeatherInfoUseCase(""))
        }
        testDispatcher.cleanupTestCoroutines()
    }
}

class FakeWeatherRepository (private val weatherInfo: List<WeatherInfo>) : WeatherRepository {
    override suspend fun searchWeatherInfo(keySearch: String) = weatherInfo
}