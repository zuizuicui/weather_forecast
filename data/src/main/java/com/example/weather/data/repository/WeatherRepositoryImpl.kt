package com.example.weather.data.repository

import com.example.weather.data.memorycache.CacheOnSuccess
import com.example.weather.data.memorycache.ResponseCache
import com.example.weather.data.remote.SearchWeatherResponse
import com.example.weather.data.remote.config.wrapresponse.WrapResponse
import com.example.weather.data.remote.config.wrapresponse.WrapResponse.*
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
    responseCache: ResponseCache
) : WeatherRepository {

    private var searchWeatherCache =
        CacheOnSuccess(cache = responseCache, block = {
            when(val response = weatherApi.searchWeather(it)) {
                is Success -> response.body
                is ApiError -> throw CityNotFoundException()
                is OtherError ->  throw response.error
            }
        })

    override suspend fun searchWeather(searchKey: String) = withContext(dispatchers.io) {
        val response =  searchWeatherCache.getOrAwait(searchKey)
        return@withContext weatherConverter.convertToListModel(
            response.list ?: emptyList()
        )
    }
}