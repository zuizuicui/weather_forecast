package com.example.weather.weatherforecast.ui.searchweather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.weather.common.ui.CommonViewModel
import com.example.weather.common.ui.CommonViewState.*
import com.example.weather.common.ui.ErrorEvent
import com.example.weather.domain.entity.exception.CityNotFoundException
import com.example.weather.domain.entity.exception.InvalidInputException
import com.example.weather.domain.interaction.searchweather.GetSearchKeyLengthUseCase
import com.example.weather.domain.interaction.searchweather.SearchWeatherUseCase
import com.example.weather.domain.interaction.searchweather.WeatherResultItem
import com.example.weather.weatherforecast.ui.util.dateFormat
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.roundToInt

@HiltViewModel
class SearchWeatherViewModel @Inject constructor (
    val getSearchKeyLengthUseCase: GetSearchKeyLengthUseCase,
    val searchWeatherUseCase: SearchWeatherUseCase
) : CommonViewModel() {

    private var _minSearchKeyLength = MutableLiveData(0)
    var minSearchKeyLength : LiveData<Int> = _minSearchKeyLength

    private var _weathers = MutableLiveData<List<WeatherModel>>(listOf())
    var weathers : LiveData<List<WeatherModel>> = _weathers

    init {
        getMinSearchKeyLength()
    }

    private fun getMinSearchKeyLength() = viewModelScope.launch {
        try {
            _minSearchKeyLength.value = getSearchKeyLengthUseCase()
        } catch (e: Exception) {
            // will not happen
        }
    }

    fun searchWeather(searchKey: String) = viewModelScope.launch {
        try {
            setViewState(LOADING)
            _weathers.value = searchWeatherUseCase(searchKey).map { toWeatherModelView(it) }
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