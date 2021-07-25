package com.example.weather.data.remote.weather

import com.example.weather.data.build
import com.example.weather.data.enqueueResponse
import com.example.weather.data.dispatcher.DataDispatchers
import com.example.weather.data.repository.WeatherRepositoryImpl
import com.example.weather.domain.model.CityNotFoundException
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Test


@ExperimentalCoroutinesApi
class WeatherApiTest {
    private val mockWebServer = MockWebServer()

    private val api = mockWebServer.build(WeatherApi::class.java)

    private val testDispatcher = TestCoroutineDispatcher()

    private val sut = WeatherRepositoryImpl(api, DataDispatchers(testDispatcher, testDispatcher))

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `should search weather correctly given 200 response`() {
        mockWebServer.enqueueResponse("weather-info-200.json", 200)

        runBlocking {
            val actual = sut.searchWeather("abc")

            Assert.assertNotNull(actual)
        }
    }

    @Test(expected = CityNotFoundException::class)
    fun `should search weather given 404 not found response`() {
        mockWebServer.enqueueResponse("weather-info-404.json", 200)
        runBlocking {
            sut.searchWeather("abc")
        }
    }
}