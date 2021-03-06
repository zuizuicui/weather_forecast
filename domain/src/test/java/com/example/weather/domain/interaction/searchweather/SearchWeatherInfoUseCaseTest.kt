package com.example.weather.domain.interaction.searchweather

import com.example.weather.domain.interaction.DomainDispatchers
import com.example.weather.domain.entity.WeatherElement
import com.example.weather.domain.entity.exception.InvalidInputException
import com.example.weather.domain.interaction.mock.repository.MockSearchWeatherResult
import com.example.weather.domain.interaction.mock.repository.MockWeatherElement
import com.example.weather.domain.interaction.mock.repository.fakeWeatherRepository
import com.example.weather.domain.repository.WeatherRepository
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class SearchWeatherUseCaseTest {

    private val testDispatcher = TestCoroutineDispatcher()

    private val dispatchers = DomainDispatchers(testDispatcher)

    lateinit var searchWeatherUseCase : SearchWeatherUseCase

    @After
    fun teardown() {
        testDispatcher.cleanupTestCoroutines()
    }

    @Test(expected = InvalidInputException::class)
    fun testSearchWeather_shouldThrowException() {
        val weatherRepository: WeatherRepository = mockk(relaxed = true)

        searchWeatherUseCase = SearchWeatherUseCase(
            weatherRepository,
            dispatchers
        )

        val searchKey = "ha"

        testDispatcher.runBlockingTest {
            searchWeatherUseCase(searchKey)
        }
    }

    @Test
    fun testSearchWeather_shouldReturnResult() {

        val weatherElement: List<WeatherElement> = listOf(MockWeatherElement.weatherElement)
        val weatherRepository: WeatherRepository = fakeWeatherRepository(weatherElement)

        searchWeatherUseCase = SearchWeatherUseCase(
            weatherRepository,
            dispatchers
        )

        val expectResult = listOf(MockSearchWeatherResult.searchWeatherResult)

        val searchKey = "hanoi"

        testDispatcher.runBlockingTest {
            assertEquals(expectResult, searchWeatherUseCase(searchKey))
            coVerify { weatherRepository.searchWeather(searchKey) }
        }
    }

    @Test
    fun testSearchWeather_shouldReturnNullObject() {

        val weatherElement: List<WeatherElement> = listOf(MockWeatherElement.nullObject)
        val weatherRepository: WeatherRepository = fakeWeatherRepository(weatherElement)

        searchWeatherUseCase = SearchWeatherUseCase(
            weatherRepository,
            dispatchers
        )

        val expectResult = listOf(MockSearchWeatherResult.nullObject)

        val searchKey = "hanoi"

        testDispatcher.runBlockingTest {
            assertEquals(expectResult, searchWeatherUseCase(searchKey))
            coVerify { weatherRepository.searchWeather(searchKey) }
        }
    }
}