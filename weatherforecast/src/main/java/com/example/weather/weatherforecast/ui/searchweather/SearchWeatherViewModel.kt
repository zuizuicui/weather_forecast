package com.example.weather.weatherforecast.ui.searchweather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.weather.common.ui.CommonViewModel
import com.example.weather.common.ui.CommonViewState
import com.example.weather.domain.interaction.searchweather.GetKeySearchLengthUseCase
import com.example.weather.domain.interaction.searchweather.SearchWeatherInfoUseCase
import com.example.weather.domain.entity.exception.FailRequestException
import com.example.weather.domain.interaction.searchweather.WeatherResultItem
import com.example.weather.weatherforecast.ui.util.dateFormat
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject
import kotlin.math.roundToInt

@HiltViewModel
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
        try {
            _minSearchKeyLength.value = getKeySearchLengthUseCase()
        } catch (e: Exception) {
            // will not happen
        }
    }

    fun searchWeather(keySearch: String) = viewModelScope.launch {
        try {
            _viewState.value = CommonViewState.LOADING
            _weatherInfo.value = searchWeatherInfoUseCase(keySearch).map { toWeatherModelView(it) }
            _viewState.value = CommonViewState.HAS_RESULT
        } catch (e: FailRequestException) {
            _viewState.value = CommonViewState.NO_RESULT
        } catch (e: Exception) {
            e.printStackTrace()
            _viewState.value = CommonViewState.ERROR
        }
    }

    private fun toWeatherModelView(result: WeatherResultItem) : WeatherModel {
        return WeatherModel(
            date = result.date.dateFormat(),
            aveTemp = result.averageTemp.roundToInt().toString(),
            pressure = result.pressure.toString(),
            humidity = result.humidity.toString(),
            description = result.description
        )
    }

}