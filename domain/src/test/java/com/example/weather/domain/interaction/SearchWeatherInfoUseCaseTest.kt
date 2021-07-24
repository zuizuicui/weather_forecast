package com.example.weather.domain.interaction

import com.example.weather.domain.model.WeatherElement
import com.example.weather.domain.repository.WeatherRepository
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Test

class SearchWeatherInfoUseCaseTest {
    private val weatherElement: List<WeatherElement> = listOf()
    private val weatherRepository = FakeWeatherRepository(weatherElement)
    private val searchWeatherInfoUseCase = SearchWeatherInfoUseCase(weatherRepository)
    private val testDispatcher = TestCoroutineDispatcher()

    @Test
    fun testSearchWeather() {
        testDispatcher.runBlockingTest {
            assertEquals(weatherElement, searchWeatherInfoUseCase(""))
        }
        testDispatcher.cleanupTestCoroutines()
    }
}

class FakeWeatherRepository (private val weatherElement: List<WeatherElement>) : WeatherRepository {
    override suspend fun searchWeatherInfo(keySearch: String) = weatherElement
}