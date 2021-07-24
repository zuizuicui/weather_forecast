package com.example.weather.domain.interaction

import com.example.weather.domain.repository.WeatherRepository
import com.example.weather.domain.model.WeatherElement
import javax.inject.Inject

class SearchWeatherInfoUseCase @Inject constructor (
    private val weatherRepository: WeatherRepository,
) {
    suspend operator fun invoke(keySearch: String): List<WeatherElement> {
        return weatherRepository.searchWeatherInfo(keySearch)
    }
}