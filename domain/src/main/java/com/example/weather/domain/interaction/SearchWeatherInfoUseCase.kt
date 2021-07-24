package com.example.weather.domain.interaction

import com.example.weather.domain.entity.CalculateAverageTemperature
import com.example.weather.domain.entity.SelectShowingWeather
import com.example.weather.domain.interaction.searchweather.WeatherResultElement
import com.example.weather.domain.repository.WeatherRepository
import com.example.weather.domain.model.WeatherElement
import javax.inject.Inject

class SearchWeatherInfoUseCase @Inject constructor (
    private val weatherRepository: WeatherRepository,
    private val selectShowingWeather: SelectShowingWeather,
    private val calculateAverageTemperature: CalculateAverageTemperature
) {
    suspend operator fun invoke(keySearch: String): List<WeatherResultElement> {
        val weatherElementList = weatherRepository.searchWeather(keySearch)
        return weatherElementList.map(::toWeatherResult)
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