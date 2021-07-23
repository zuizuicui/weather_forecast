package com.example.weather.data

 import com.example.weather.data.remote.WeatherApi
import com.example.weather.domain.repository.WeatherRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
 import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi,
    private val ioDispatcher: CoroutineDispatcher
) : WeatherRepository {
    override suspend fun searchWeatherInfo(keySearch: String) = withContext(ioDispatcher) {
        weatherApi.searchWeatherInfo(keySearch)
    }
}