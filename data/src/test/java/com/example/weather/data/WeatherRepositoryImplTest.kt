package com.example.weather.data

import com.example.weather.data.remote.SearchWeatherResponse
import com.example.weather.data.remote.WeatherApi
import com.example.weather.data.converter.WeatherConvert
import com.example.weather.data.converter.WeatherElementConvert
import com.example.weather.data.remote.WeatherElementDto
import com.example.weather.domain.model.WeatherElement
import io.mockk.*
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Test

class WeatherRepositoryImplTest {
    private val searchWeatherResponse: SearchWeatherResponse = mockk()
    private val testDispatcher = TestCoroutineDispatcher()

    @Test
    fun testSearchWeatherInfo() {
        val weatherListDto = listOf(mockk<WeatherElementDto>())
        val weatherList = listOf(mockk<WeatherElement>())

        val weatherApi : WeatherApi = fakeWeatherApiSearch(weatherListDto)
        val weatherElementConvert = fakeWeatherElementConvert(weatherListDto, weatherList)

        val keySearch = "hanoi"

        val weatherRepository = WeatherRepositoryImpl(weatherApi, testDispatcher, weatherElementConvert)

        testDispatcher.runBlockingTest {
            val result = weatherRepository.searchWeatherInfo(keySearch)
            coVerify { weatherApi.searchWeatherInfo(keySearch) }
            verify { weatherElementConvert.convertToListModel(weatherListDto) }
            assertEquals(weatherList, result)
        }
    }

    private fun fakeWeatherApiSearch(
        weatherListDto: List<WeatherElementDto>
    ) = mockk<WeatherApi>().apply {
        every { searchWeatherResponse.code} returns "200"
        every { searchWeatherResponse.list} returns weatherListDto
        coEvery { searchWeatherInfo(any(), any(), any()) } returns searchWeatherResponse
    }

    private fun fakeWeatherElementConvert(
        weatherListDto : List<WeatherElementDto>,
        weatherList: List<WeatherElement>
    ) = mockk<WeatherElementConvert>().apply {
        every { convertToListModel(weatherListDto) } returns weatherList
    }
}