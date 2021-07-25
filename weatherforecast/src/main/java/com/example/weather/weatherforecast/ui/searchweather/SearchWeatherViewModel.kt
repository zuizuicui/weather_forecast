package com.example.weather.weatherforecast.ui.searchweather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.weather.common.ui.CommonViewModel
import com.example.weather.common.ui.CommonViewState
import com.example.weather.domain.interaction.searchweather.GetKeySearchLengthUseCase
import com.example.weather.domain.interaction.searchweather.SearchWeatherInfoUseCase
import com.example.weather.domain.model.FailRequestException
import com.example.weather.weatherforecast.ui.util.dateFormat
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject
import kotlin.math.roundToInt

class SearchWeatherViewModel @Inject constructor (
    val getKeySearchLengthUseCase: GetKeySearchLengthUseCase,
    val searchWeatherInfoUseCase: SearchWeatherInfoUseCase
) : CommonViewModel() {

    private var _minSearchKeyLength = MutableLiveData(0)
    var minSearchKeyLength : LiveData<Int> = _minSearchKeyLength

    private var _weatherInfo = MutableLiveData<List<WeatherModel>>(listOf())
    var weatherElement : LiveData<List<WeatherModel>> = _weatherInfo

    init {
        getMinSearchKeyLength()
    }

    private fun getMinSearchKeyLength() = viewModelScope.launch {
        _minSearchKeyLength.value = getKeySearchLengthUseCase()
    }

    fun searchWeather(keySearch: String) = viewModelScope.launch {
        _viewState.value = CommonViewState.LOADING
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
            _viewState.value = CommonViewState.HAS_RESULT
        } catch (e: FailRequestException) {
            _viewState.value = CommonViewState.NO_RESULT
        } catch (e: Exception) {
            e.printStackTrace()
            _viewState.value = CommonViewState.ERROR
        }
    }

}