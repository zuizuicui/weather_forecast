package com.example.weather.domain.entity

import com.example.weather.domain.model.Temperature
import javax.inject.Inject

class CalculateAverageTemperature @Inject constructor() {
    operator fun invoke(temperature: Temperature) : Double {
        val (day, min, max, night, eve, morn) = temperature
        return listOf(day, min, max, night, eve, morn).average().convertKToC()
    }

    companion object {
        private const val CONVERT_K_TO_C = - 272.15
        fun Double.convertKToC() = this + CONVERT_K_TO_C
    }
}