package com.example.weather.data

 import android.content.res.Resources
 import android.util.Log
 import com.example.weather.data.converter.DataConverter
 import com.example.weather.data.converter.WeatherElementConvert
 import com.example.weather.data.remote.WeatherApi
 import com.example.weather.domain.model.CityNotFoundException
 import com.example.weather.domain.model.NetworkErrorException
 import com.example.weather.domain.repository.WeatherRepository
 import kotlinx.coroutines.CoroutineDispatcher
 import kotlinx.coroutines.Dispatchers
 import kotlinx.coroutines.withContext
 import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val weatherConverter: WeatherElementConvert = DataConverter.weatherElementConvert,
) : WeatherRepository {
    override suspend fun searchWeather(keySearch: String) = withContext(ioDispatcher) {
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