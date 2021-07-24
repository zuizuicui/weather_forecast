package com.example.weather.domain.entity

import javax.inject.Inject

class LengthWeatherSearchKeyRule @Inject constructor() {
    operator fun invoke() = 3
}