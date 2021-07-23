package com.example.weather.domain.interaction

import com.example.weather.domain.repository.WeatherRepository
import com.example.weather.domain.model.WeatherInfo
import javax.inject.Inject

class SearchWeatherInfoUseCase @Inject constructor (
    private val weatherRepository: WeatherRepository,
) {
    suspend operator fun invoke(keySearch: String): List<WeatherInfo> {
        return weatherRepository.searchWeatherInfo(keySearch)
    }
}