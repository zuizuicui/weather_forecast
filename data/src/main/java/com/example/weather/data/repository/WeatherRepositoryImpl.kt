package com.example.weather.data.repository

 import com.example.weather.data.remote.weather.WeatherApi
 import com.example.weather.data.repository.converter.WeatherElementConvert
 import com.example.weather.data.repository.dispatcher.DataDispatchers
 import com.example.weather.domain.entity.exception.CityNotFoundException
 import com.example.weather.domain.entity.exception.NetworkErrorException
 import com.example.weather.domain.entity.exception.UnKnowException
 import com.example.weather.domain.repository.WeatherRepository
 import kotlinx.coroutines.withContext
 import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi,
    private val dispatchers: DataDispatchers,
    private val weatherConverter: WeatherElementConvert,
) : WeatherRepository {
    override suspend fun searchWeather(keySearch: String) = withContext(dispatchers.io) {
        val weatherResponse = weatherApi.searchWeatherInfo(keySearch)
        if (weatherResponse.isSuccess()) {
            return@withContext weatherConverter.convertToListModel(
                weatherResponse.list ?: emptyList()
            )
        } else {
            throw CityNotFoundException()
        }
    }
}