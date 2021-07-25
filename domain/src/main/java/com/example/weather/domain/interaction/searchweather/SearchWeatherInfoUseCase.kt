package com.example.weather.domain.interaction.searchweather

import com.example.weather.domain.entity.CalculateAverageTemperature
import com.example.weather.domain.entity.SelectShowingWeather
import com.example.weather.domain.model.WeatherElement
import com.example.weather.domain.dispatcher.DomainDispatchers
import com.example.weather.domain.repository.WeatherRepository
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchWeatherInfoUseCase @Inject constructor (
    private val weatherRepository: WeatherRepository,
    private val selectShowingWeather: SelectShowingWeather,
    private val calculateAverageTemperature: CalculateAverageTemperature,
    private val dispatchers: DomainDispatchers
) {
    suspend operator fun invoke(keySearch: String): List<WeatherResultElement> = withContext(dispatchers.default) {
        val weatherElementList = weatherRepository.searchWeather(keySearch)
        return@withContext weatherElementList.map {
            ensureActive()
            toWeatherResult(it)
        }
    }

    private fun toWeatherResult(
        weatherElement: WeatherElement
    ) = WeatherResultElement (
        date = weatherElement.date,
        pressure = weatherElement.pressure,
        humidity = weatherElement.humidity,
        averageTemp = calculateAverageTemperature(weatherElement.temperature),
        description = selectShowingWeather(weatherElement.weather)?.description ?: ""
    )
}