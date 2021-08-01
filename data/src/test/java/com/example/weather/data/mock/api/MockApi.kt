package com.example.weather.data.mock.api

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
    exception: Exception? = null,
    fail: Boolean = false
) = mockk<WeatherApi>().apply {
    if (exception != null) {
        coEvery { searchWeatherInfo(any(), any(), any()) } throws exception
        return@apply
    }
    if (fail) {
        coEvery { searchWeatherInfo(any(), any(), any()) } throws createHttpException()
        return@apply
    }
    every { searchWeatherResponse.isSuccess()} returns (true)
    every { searchWeatherResponse.code} returns "200"
    every { searchWeatherResponse.list} returns weatherListDto
    coEvery { searchWeatherInfo(any(), any(), any()) } returns searchWeatherResponse
}

fun createHttpException(): HttpException {
    val errorBody = "{\"cod\":\"404\",\"message\":\"city not found\"}".toResponseBody()
    val response : Response<SearchWeatherResponse> = Response.error(404, errorBody)
    return HttpException(response)
}