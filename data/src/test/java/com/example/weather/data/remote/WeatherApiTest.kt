package com.example.weather.data.remote

import com.example.weather.data.WeatherRepositoryImpl
import com.example.weather.data.build
import com.example.weather.data.enqueueResponse
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

    private val sut = WeatherRepositoryImpl(api, testDispatcher)

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `should fetch movies correctly given 200 response`() {
        mockWebServer.enqueueResponse("weather-info-200.json", 200)

        runBlocking {
            val actual = sut.searchWeatherInfo("abc")

            Assert.assertNotNull(actual)
        }
    }
}