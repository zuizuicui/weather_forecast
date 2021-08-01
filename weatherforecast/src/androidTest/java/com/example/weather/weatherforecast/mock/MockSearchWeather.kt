package com.example.weather.weatherforecast.mock

import androidx.lifecycle.MutableLiveData
import com.example.weather.common.ui.CommonViewState
import com.example.weather.common.ui.ViewState
import com.example.weather.domain.interaction.searchweather.WeatherResultItem
import com.example.weather.weatherforecast.ui.searchweather.SearchWeatherViewModel
import com.example.weather.weatherforecast.ui.searchweather.WeatherModel
import io.mockk.every
import io.mockk.mockk

object MockSearchWeather {

    fun mockSearchWeatherViewModel() = mockk<SearchWeatherViewModel>(relaxed = true).also {
        val minSearchKeyLength = MutableLiveData(3)
        val weatherElements = MutableLiveData<List<WeatherModel>>(listOf())
        val viewState = MutableLiveData<ViewState>(CommonViewState.EMPTY)

        every { it.minSearchKeyLength } returns minSearchKeyLength
        every { it.weatherElements } returns weatherElements
        every { it.viewState } returns viewState
    }

    fun createWeatherModel(
        date: String = "Mon, 26 Jul 2021",
        averageTemp: String = "30",
        pressure: String = "100",
        humidity: String = "70",
        description: String = "moderate rain"
    ) = WeatherModel(
        date, averageTemp, pressure, humidity, description
    )

    fun createWeatherResult(
        date: Long = 1627272000,
        averageTemp: Double = 30.0,
        pressure: Long = 100,
        humidity: Long = 70,
        description: String = "moderate rain"
    ) = WeatherResultItem(
        date, averageTemp, pressure, humidity, description
    )
}