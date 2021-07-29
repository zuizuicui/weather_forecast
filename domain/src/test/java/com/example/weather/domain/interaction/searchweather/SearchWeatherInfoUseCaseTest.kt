package com.example.weather.domain.interaction.searchweather

import com.example.weather.domain.interaction.DomainDispatchers
import com.example.weather.domain.entity.WeatherElement
import com.example.weather.domain.interaction.mock.repository.fakeWeatherRepository
import com.example.weather.domain.repository.WeatherRepository
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class SearchWeatherInfoUseCaseTest {

    private val testDispatcher = TestCoroutineDispatcher()

    private val weatherElement: List<WeatherElement> = listOf(mockk())
    private val weatherRepository: WeatherRepository = fakeWeatherRepository(weatherElement)

    lateinit var searchWeatherInfoUseCase : SearchWeatherInfoUseCase

    @Before
    fun setUp() {
        searchWeatherInfoUseCase = SearchWeatherInfoUseCase(
            weatherRepository,
            DomainDispatchers(testDispatcher)
        )
    }

    @Test
    fun testSearchWeather_shouldReturnResult() {
        val expectResult = createExpectResult()
        prepareForTest(expectResult)

        testDispatcher.runBlockingTest {
            assertEquals(expectResult, searchWeatherInfoUseCase(""))
        }
        testDispatcher.cleanupTestCoroutines()
    }

    private fun createExpectResult() = listOf(WeatherResultItem(
        date = 10000,
        pressure = 12,
        humidity = 10,
        description = "description",
        averageTemp = 1.0
    ))

    private fun prepareForTest(expectResult: List<WeatherResultItem>) {
        expectResult[0].let {
            weatherElement[0].apply {
                every { date } returns it.date
                every { pressure } returns  it.pressure
                every { humidity } returns it.humidity
                every { temperature } returns mockk()
                every { weather } returns mockk()
            }
        }

    }
}