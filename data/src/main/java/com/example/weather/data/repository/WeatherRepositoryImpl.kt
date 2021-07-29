package com.example.weather.data.repository

 import android.content.res.Resources
 import android.util.Log
 import com.example.weather.data.repository.converter.DataConverter
 import com.example.weather.data.repository.converter.WeatherElementConvert
 import com.example.weather.data.repository.dispatcher.DataDispatchers
 import com.example.weather.data.remote.weather.WeatherApi
 import com.example.weather.domain.entity.exception.CityNotFoundException
 import com.example.weather.domain.entity.exception.NetworkErrorException
 import com.example.weather.domain.repository.WeatherRepository
 import kotlinx.coroutines.withContext
 import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi,
    private val dispatchers: DataDispatchers,
    private val weatherConverter: WeatherElementConvert = DataConverter.weatherElementConvert,
) : WeatherRepository {
    override suspend fun searchWeather(keySearch: String) = withContext(dispatchers.io) {
        val weatherResponse = try {
            weatherApi.searchWeatherInfo(keySearch).apply {
                if(!this.isSuccess()) {
                    Log.d("error", this.toString())
                    throw Resources.NotFoundException()
                }
            }
        } catch (e: NetworkErrorException) {
            throw e
        } catch (e: Resources.NotFoundException) {
            throw e
        } catch (e: Exception) {
            throw CityNotFoundException()
        }
        return@withContext weatherConverter.convertToListModel(weatherResponse.list)
    }
}