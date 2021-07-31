package com.example.weather.weatherforecast.ui.searchweather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.weather.common.ui.CommonViewModel
import com.example.weather.common.ui.CommonViewState.*
import com.example.weather.common.ui.ErrorEvent
import com.example.weather.domain.entity.exception.CityNotFoundException
import com.example.weather.domain.entity.exception.InvalidInputException
import com.example.weather.domain.interaction.searchweather.GetKeySearchLengthUseCase
import com.example.weather.domain.interaction.searchweather.SearchWeatherInfoUseCase
import com.example.weather.domain.interaction.searchweather.WeatherResultItem
import com.example.weather.weatherforecast.ui.util.dateFormat
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
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
            setViewState(LOADING)
            _weatherInfo.value = searchWeatherInfoUseCase(keySearch).map { toWeatherModelView(it) }
            setViewState(HAS_RESULT)
        } catch (e: InvalidInputException) {
            setViewState(SearchKeyInvalid())
        } catch (e: CityNotFoundException) {
            setViewState(NO_RESULT_RESPONSE)
        } catch (e: Exception) {
            setViewState(handleCommonError(e))
        }
    }

    data class SearchKeyInvalid(private val errorResource: Int = 1) : ErrorEvent {
        override fun getErrorResource() = errorResource
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