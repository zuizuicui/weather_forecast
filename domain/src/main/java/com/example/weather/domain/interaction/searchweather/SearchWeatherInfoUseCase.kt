package com.example.weather.domain.interaction.searchweather

import com.example.weather.domain.entity.WeatherElement
import com.example.weather.domain.interaction.DomainDispatchers
import com.example.weather.domain.entity.LengthSearchKeyRule
import com.example.weather.domain.entity.Temperature
import com.example.weather.domain.entity.Temperature.Companion.defaultTemperatureToCelsius
import com.example.weather.domain.entity.Weather
import com.example.weather.domain.entity.exception.InvalidInputException
import com.example.weather.domain.interaction.SuspendUseCase
import com.example.weather.domain.repository.WeatherRepository
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchWeatherInfoUseCase @Inject constructor (
    private val weatherRepository: WeatherRepository,
    private val dispatchers: DomainDispatchers
) :SuspendUseCase<String, List<WeatherResultItem>> {

    override suspend operator fun invoke(keySearch: String): List<WeatherResultItem> = withContext(dispatchers.default) {
        val weatherElementList = weatherRepository.searchWeather(keySearch)

        if (keySearch.length < LengthSearchKeyRule.searchWeatherMinLength) {
            throw InvalidInputException()
        }

        return@withContext weatherElementList.map {
            ensureActive()
            toWeatherResult(it)
        }
    }

    private fun toWeatherResult(
        weatherElement: WeatherElement
    ) = WeatherResultItem (
        date = weatherElement.date,
        pressure = weatherElement.pressure,
        humidity = weatherElement.humidity,
        averageTemp = calculateTemperatureAverage(weatherElement.temperature),
        description = selectShowingWeather(weatherElement.weather)?.description ?: ""
    )

    private fun calculateTemperatureAverage(temperature: Temperature?) : Double {
        return temperature?.average()?.defaultTemperatureToCelsius() ?: Double.NaN
    }

    private fun selectShowingWeather(weathers: List<Weather>?) : Weather? {
        return weathers?.firstOrNull()
    }
}