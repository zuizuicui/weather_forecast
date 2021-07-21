package com.example.weather.domain.interaction

import com.example.weather.domain.repository.WeatherRepository
import com.example.weather.domain.model.WeatherInfo

class SearchWeatherInfoUseCase (
    private val weatherRepository: WeatherRepository,
) {
    suspend operator fun invoke(keySearch: String): List<WeatherInfo> {
        return weatherRepository.searchWeatherInfo(keySearch)
    }
}