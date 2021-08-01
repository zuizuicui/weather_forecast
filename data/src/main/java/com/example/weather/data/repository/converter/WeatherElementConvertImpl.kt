package com.example.weather.data.repository.converter

import com.example.weather.data.remote.FeelsLikeDto
import com.example.weather.data.remote.TemperatureDto
import com.example.weather.data.remote.WeatherDto
import com.example.weather.data.remote.WeatherElementDto
import com.example.weather.data.repository.dispatcher.DataDispatchers
import com.example.weather.domain.entity.FeelsLike
import com.example.weather.domain.entity.Temperature
import com.example.weather.domain.entity.Weather
import com.example.weather.domain.entity.WeatherElement
import kotlinx.coroutines.withContext
import javax.inject.Inject

open class WeatherElementConvertImpl @Inject constructor(private val dispatchers: DataDispatchers) : WeatherElementConvert {

    override suspend fun convertToListModel(weatherElements: List<WeatherElementDto>): List<WeatherElement> {
        return withContext(dispatchers.default) {
            return@withContext weatherElements.map { it.toWeatherElement() }
        }
    }

    private fun WeatherElementDto.toWeatherElement(): WeatherElement {
        return WeatherElement(
            date = date ?: 0,
            sunrise = sunrise ?: 0,
            sunset = sunset ?: 0,
            temperature = temperature?.toTemperature(),
            feelsLike = feelsLike?.toFeelsLike(),
            pressure = pressure ?: 0,
            humidity = humidity ?: 0,
            weather = weather?.map { it.toWeather() },
            speed = speed ?: 0.0,
            deg = deg ?: 0,
            gust = gust ?: 0.0,
            clouds = clouds ?: 0,
            pop = pop ?: 0.0,
            rain = rain ?: 0.0,
        )
    }

    private fun WeatherDto.toWeather(): Weather {
        return Weather(
            id = id ?: 0L,
            main = main ?: "",
            description = description ?: "",
            icon = icon ?: "",
        )
    }

    private fun TemperatureDto.toTemperature(): Temperature {
        return Temperature(
            day = day ?: 0.0,
            min = min ?: 0.0,
            max = max ?: 0.0,
            night = night ?: 0.0,
            eve = eve ?: 0.0,
            morn = morn ?: 0.0,
        )
    }

    private fun FeelsLikeDto.toFeelsLike(): FeelsLike {
        return FeelsLike(
            day = day ?: 0.0,
            night = night ?: 0.0,
            eve = eve ?: 0.0,
            morn = morn ?: 0.0,
        )
    }
}