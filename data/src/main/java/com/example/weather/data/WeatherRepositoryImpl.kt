package com.example.weather.data

 import com.example.weather.data.converter.DataConverter
 import com.example.weather.data.remote.WeatherApi
 import com.example.weather.data.converter.WeatherElementConvert
 import com.example.weather.domain.repository.WeatherRepository
import kotlinx.coroutines.CoroutineDispatcher
 import kotlinx.coroutines.Dispatchers
 import kotlinx.coroutines.withContext
 import java.lang.RuntimeException
 import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val weatherConverter: WeatherElementConvert = DataConverter.weatherElementConvert,
) : WeatherRepository {
    override suspend fun searchWeatherInfo(keySearch: String) = withContext(ioDispatcher) {
        try {
            val weatherResponse = weatherApi.searchWeatherInfo(keySearch)
            if (weatherResponse.isSuccess()) {
                return@withContext weatherConverter.convertToListModel(weatherResponse.list)
            } else {
                throw RuntimeException(weatherResponse.message)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }
}