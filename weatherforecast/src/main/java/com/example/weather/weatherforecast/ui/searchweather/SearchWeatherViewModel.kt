package com.example.weather.weatherforecast.ui.searchweather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.domain.interaction.GetKeySearchLengthUseCase
import com.example.weather.domain.interaction.SearchWeatherInfoUseCase
import com.example.weather.domain.model.WeatherElement
import com.example.weather.weatherforecast.ui.util.dateFormat
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.roundToInt

class SearchWeatherViewModel @Inject constructor (
    val getKeySearchLengthUseCase: GetKeySearchLengthUseCase,
    val searchWeatherInfoUseCase: SearchWeatherInfoUseCase
) : ViewModel() {

    private var _minSearchKeyLength = MutableLiveData(0)
    var minSearchKeyLength : LiveData<Int> = _minSearchKeyLength

    private var _weatherInfo = MutableLiveData<List<WeatherModel>>(listOf())
    var weatherElement : LiveData<List<WeatherModel>> = _weatherInfo

    init {
        getMinSearchKeyLength()
    }

    private fun getMinSearchKeyLength() {
        _minSearchKeyLength.value = getKeySearchLengthUseCase()
    }

    fun searchWeather(keySearch: String) = viewModelScope.launch {
        try {
            val result = searchWeatherInfoUseCase(keySearch)
            _weatherInfo.value = result.map {
                WeatherModel(
                    date = it.date.dateFormat(),
                    aveTemp = it.averageTemp.roundToInt().toString(),
                    pressure = it.pressure.toString(),
                    humidity = it.humidity.toString(),
                    description = it.description
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}