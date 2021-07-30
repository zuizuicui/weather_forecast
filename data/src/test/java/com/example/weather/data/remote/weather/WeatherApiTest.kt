package com.example.weather.data.remote.weather

import com.example.weather.data.build
import com.example.weather.data.enqueueResponse
import com.example.weather.data.repository.dispatcher.DataDispatchers
import com.example.weather.data.repository.WeatherRepositoryImpl
import com.example.weather.data.repository.converter.WeatherElementConvertImpl
import com.example.weather.domain.entity.exception.CityNotFoundException
import com.example.weather.domain.repository.WeatherRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class WeatherApiTest {
    private val testDispatcher = TestCoroutineDispatcher()

    private val mockWebServer = MockWebServer()

    lateinit var api : WeatherApi

    lateinit var weatherRepository : WeatherRepository

    @Before
    fun setup() {
        api = mockWebServer.build(WeatherApi::class.java)

        val dispatchers = DataDispatchers(testDispatcher, testDispatcher)
        val converter = WeatherElementConvertImpl(dispatchers)
        weatherRepository = WeatherRepositoryImpl(api, dispatchers, converter)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `should search weather correctly given 200 response`() {
        mockWebServer.enqueueResponse("weather-info-200.json", 200)

        runBlocking {
            val actual = weatherRepository.searchWeather("abc")

            Assert.assertNotNull(actual)
        }
    }

    @Test(expected = CityNotFoundException::class)
    fun `should search weather given 404 not found response`() {
        mockWebServer.enqueueResponse("weather-info-404.json", 200)
        runBlocking {
            weatherRepository.searchWeather("abc")
        }
    }
}