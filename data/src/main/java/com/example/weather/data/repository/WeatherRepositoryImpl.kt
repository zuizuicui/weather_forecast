package com.example.weather.data.repository

import com.example.weather.data.remote.SearchWeatherResponse
import com.example.weather.data.remote.weather.WeatherApi
import com.example.weather.data.repository.converter.WeatherElementConvert
import com.example.weather.data.repository.dispatcher.DataDispatchers
import com.example.weather.domain.entity.exception.CityNotFoundException
import com.example.weather.domain.repository.WeatherRepository
import com.google.gson.Gson
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi,
    private val dispatchers: DataDispatchers,
    private val weatherConverter: WeatherElementConvert,
) : WeatherRepository {
    override suspend fun searchWeather(keySearch: String) = withContext(dispatchers.io) {
        val weatherResponse = try {
            weatherApi.searchWeatherInfo(keySearch)
        } catch (e: HttpException) {
            e.errorBody()
        }
        if (weatherResponse.isSuccess()) {
            return@withContext weatherConverter.convertToListModel(
                weatherResponse.list ?: emptyList()
            )
        } else {
            throw CityNotFoundException()
        }
    }

    private fun HttpException.errorBody () : SearchWeatherResponse {
        val json: String = response()?.errorBody()?.string() ?: "{}"
        return Gson().fromJson(json, SearchWeatherResponse::class.java)
    }
}