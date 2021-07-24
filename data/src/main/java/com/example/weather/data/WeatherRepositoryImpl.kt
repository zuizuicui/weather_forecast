package com.example.weather.data

 import com.example.weather.data.converter.DataConverter
 import com.example.weather.data.remote.WeatherApi
 import com.example.weather.data.converter.WeatherElementConvert
 import com.example.weather.domain.model.CityNotFoundException
 import com.example.weather.domain.model.FailRequestException
 import com.example.weather.domain.model.NetworkErrorException
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
    override suspend fun searchWeather(keySearch: String) = withContext(ioDispatcher) {
        val weatherResponse = try {
            weatherApi.searchWeatherInfo(keySearch)
        } catch (e: Exception) {
            throw NetworkErrorException()
        }
        if (weatherResponse.isSuccess()) {
            return@withContext weatherConverter.convertToListModel(weatherResponse.list)
        } else {
            throw CityNotFoundException()
        }
    }
}