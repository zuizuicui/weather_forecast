package com.example.weather.domain.entity

import com.example.weather.domain.model.Weather
import javax.inject.Inject

class SelectShowingWeather @Inject constructor() {
    operator fun invoke(weathers: List<Weather>) : Weather? {
        return weathers.firstOrNull()
    }
}