package com.example.weather.domain.entity

import com.example.weather.domain.model.Temperature
import javax.inject.Inject

class CalculateAverageTemperature @Inject constructor() {
    operator fun invoke(temperature: Temperature) : Double {
        val (day, min, max, night, eve, morn) = temperature
        return listOf(day, min, max, night, eve, morn).average()
    }
}