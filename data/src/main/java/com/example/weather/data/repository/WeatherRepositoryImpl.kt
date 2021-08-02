package com.example.weather.data.repository

import com.example.weather.data.local.WeatherDao
import com.example.weather.data.local.table.SearchKeyEntity
import com.example.weather.data.remote.SearchWeatherResponse
import com.example.weather.data.remote.config.wrapresponse.WrapResponse.*
import com.example.weather.data.remote.weather.WeatherApi
import com.example.weather.data.repository.converter.WeatherElementConvert
import com.example.weather.data.repository.dispatcher.DataDispatchers
import com.example.weather.domain.entity.Weather
import com.example.weather.domain.entity.WeatherElement
import com.example.weather.domain.entity.exception.CityNotFoundException
import com.example.weather.domain.repository.WeatherRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi,
    private val dispatchers: DataDispatchers,
    private val weatherConverter: WeatherElementConvert,
    private val weatherDao: WeatherDao
) : WeatherRepository {
    override suspend fun searchWeather(searchKey: String) = withContext(dispatchers.io) {
        val cache = getCache(searchKey)

        if(cache.isNotEmpty()) {
            return@withContext cache
        }

        val searchWeather = when (val response = weatherApi.searchWeather(searchKey)) {
            is Success -> response.body
            is ApiError -> throw CityNotFoundException()
            is OtherError -> throw response.error
        }
        return@withContext weatherConverter.convertToListModel(
            searchWeather.list ?: emptyList()
        )
    }

    suspend fun getCache(searchKey: String) : List<WeatherElement> = withContext(dispatchers.io) {
        val searchKeyEntity = checkSearchKeyCacheExist(searchKey) ?: return@withContext listOf()
        val result = weatherDao.getSearchResult(searchKey)

        return@withContext if (result == null) {
            weatherDao.deletedSearchKey(searchKeyEntity)
            listOf()
        } else {
            weatherConverter.convertEntityToListModel(result.weatherWithIcons)
        }
    }

    private fun checkSearchKeyCacheExist(searchKey: String): SearchKeyEntity? {
        val currentTime = System.currentTimeMillis()
        val searchKeyEntity = weatherDao.getSearchKey(searchKey) ?: return null

        if (searchKeyEntity.expiredTime < currentTime) return searchKeyEntity

        weatherDao.deletedSearchKey(searchKeyEntity)
        return null
    }

    suspend fun saveCache(searchKey: String, response: SearchWeatherResponse) = withContext(dispatchers.io) {
//        val lastWeatherId = weatherDao.getLastWeatherKey()
//        val searchKey =
    }
}