package com.example.weather.data.mock.api

import com.example.weather.data.remote.config.wrapresponse.WrapResponse
import com.example.weather.data.remote.SearchWeatherResponse
import com.example.weather.data.remote.WeatherElementDto
import com.example.weather.data.remote.weather.WeatherApi
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.HttpException
import retrofit2.Response

fun fakeWeatherApiSearch(
    searchWeatherResponse: SearchWeatherResponse = mockk(),
    weatherListDto: List<WeatherElementDto>? = null,
    response: WrapResponse<SearchWeatherResponse>? = null,
    cityNotFound: Boolean = false
) = mockk<WeatherApi>().apply {
    if (response != null) {
        coEvery { searchWeather(any(), any(), any()) } returns response
        return@apply
    }
    if (cityNotFound) {
        coEvery { searchWeather(any(), any(), any()) } returns WrapResponse.ApiError(code = 404, body = "city not found")
        return@apply
    }
    every { searchWeatherResponse.isSuccess()} returns (true)
    every { searchWeatherResponse.code} returns "200"
    every { searchWeatherResponse.list} returns weatherListDto
    coEvery { searchWeather(any(), any(), any()) } returns WrapResponse.Success(searchWeatherResponse)
}