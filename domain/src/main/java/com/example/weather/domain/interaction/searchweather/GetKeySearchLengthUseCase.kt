package com.example.weather.domain.interaction.searchweather

import com.example.weather.domain.dispatcher.DomainDispatchers
import com.example.weather.domain.entity.LengthWeatherSearchKeyRule
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetKeySearchLengthUseCase @Inject constructor (
    private val lengthWeatherSearchKeyRule: LengthWeatherSearchKeyRule,
    private val dispatchers: DomainDispatchers
) {
    suspend operator fun invoke() = withContext(dispatchers.default) {
        lengthWeatherSearchKeyRule()
    }
}