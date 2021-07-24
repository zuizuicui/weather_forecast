package com.example.weather.weatherforecast.ui.searchweather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.domain.interaction.SearchWeatherInfoUseCase
import com.example.weather.domain.model.WeatherElement
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchWeatherViewModel @Inject constructor (
    val searchWeatherInfoUseCase: SearchWeatherInfoUseCase
) : ViewModel() {

    private var _weatherInfo = MutableLiveData<List<WeatherElement>>(listOf())

    var weatherElement : LiveData<List<WeatherElement>> = _weatherInfo

    fun searchWeather(keySearch: String) {
         viewModelScope.launch {
             try {
                 val result = searchWeatherInfoUseCase(keySearch)
                 _weatherInfo.value = result
             } catch (e: Exception) {

             }

         }
    }
}