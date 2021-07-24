package com.example.weather.domain.interaction

import com.example.weather.domain.entity.LengthWeatherSearchKeyRule
import javax.inject.Inject

class GetKeySearchLengthUseCase @Inject constructor (
    private val lengthWeatherSearchKeyRule: LengthWeatherSearchKeyRule
) {
    operator fun invoke() = lengthWeatherSearchKeyRule()
}