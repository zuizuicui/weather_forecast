package com.example.weather.data.repository

import com.example.weather.data.remote.WrapResponse.*
import com.example.weather.data.remote.weather.WeatherApi
import com.example.weather.data.repository.converter.WeatherElementConvert
import com.example.weather.data.repository.dispatcher.DataDispatchers
import com.example.weather.domain.entity.exception.CityNotFoundException
import com.example.weather.domain.repository.WeatherRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi,
    private val dispatchers: DataDispatchers,
    private val weatherConverter: WeatherElementConvert,
) : WeatherRepository {
    override suspend fun searchWeather(searchKey: String) = withContext(dispatchers.io) {
        val searchWeather = when(val response = weatherApi.searchWeather(searchKey)) {
            is Success -> response.body
            is ApiError -> throw CityNotFoundException()
            is OtherError ->  throw response.error
        }
        return@withContext weatherConverter.convertToListModel(
            searchWeather.list ?: emptyList()
        )
    }
}