package com.example.weather.weatherforecast.ui.searchweather

import android.accounts.NetworkErrorException
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.weather.common.ui.CommonViewState
import com.example.weather.domain.entity.exception.CityNotFoundException
import com.example.weather.domain.entity.exception.InvalidInputException
import com.example.weather.domain.interaction.searchweather.GetSearchKeyLengthUseCase
import com.example.weather.weatherforecast.mock.MockSearchUseCase.mockSearchUseCase
import com.example.weather.weatherforecast.mock.MockWeatherModel
import com.example.weather.weatherforecast.util.MainCoroutineRule
import com.example.weather.weatherforecast.util.getOrAwaitValue
import com.example.weather.weatherforecast.util.observeForTesting
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.lang.Exception

@RunWith(JUnit4::class)
class SearchWeatherViewModelTest {

    // Run tasks synchronously
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    // Sets the main coroutines dispatcher to a TestCoroutineScope for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val getSearchKeyLengthUseCase: GetSearchKeyLengthUseCase = mockk()
    private lateinit var viewModel :SearchWeatherViewModel

    @Before
    fun setup() {
        coEvery { getSearchKeyLengthUseCase() } returns 3
    }

    @Test
    fun getSearchKeyLengthUseCase_return3() {
        viewModel = SearchWeatherViewModel(getSearchKeyLengthUseCase, mockk())
        Assert.assertEquals (3, viewModel.minSearchKeyLength.getOrAwaitValue())
        Assert.assertEquals (CommonViewState.EMPTY, viewModel.viewState.getOrAwaitValue())
    }

    @Test
    fun searchWeather_returnListWeather() {
        val searchWeatherUseCase = mockSearchUseCase( listOf(
            MockWeatherModel.createWeatherResult(),
            MockWeatherModel.createWeatherResult()
        ))
        val searchKey = "hanoi"

        val expectedModel = listOf(
            MockWeatherModel.createWeatherModel(),
            MockWeatherModel.createWeatherModel()
        )

        viewModel = SearchWeatherViewModel(getSearchKeyLengthUseCase, searchWeatherUseCase)

        viewModel.viewState.observeForTesting {
            viewModel.searchWeather(searchKey)
            coVerify { searchWeatherUseCase(searchKey) }

            verifySequence {
                it.onChanged(CommonViewState.EMPTY)
                it.onChanged(CommonViewState.LOADING)
                it.onChanged(CommonViewState.HAS_RESULT)
            }

            val weatherModel = viewModel.weathers.getOrAwaitValue()
            Assert.assertEquals (expectedModel, weatherModel)
        }
    }

    @Test
    fun searchWeather_networkException() {
        val searchWeatherUseCase = mockSearchUseCase( exception =  NetworkErrorException())
        val searchKey = "hanoi"

        val expectedModel = emptyList<WeatherModel>()

        viewModel = SearchWeatherViewModel(getSearchKeyLengthUseCase, searchWeatherUseCase)

        viewModel.viewState.observeForTesting {
            viewModel.searchWeather(searchKey)
            coVerify { searchWeatherUseCase(searchKey) }

            verifySequence {
                it.onChanged(CommonViewState.EMPTY)
                it.onChanged(CommonViewState.LOADING)
                it.onChanged(CommonViewState.UN_KNOW_ERROR)
            }

            val weatherModel = viewModel.weathers.getOrAwaitValue()
            Assert.assertEquals (expectedModel, weatherModel)
        }
    }

    @Test
    fun searchWeather_invalidInputException() {
        val searchWeatherUseCase = mockSearchUseCase( exception =  InvalidInputException())
        val searchKey = "hanoi"

        val expectedModel = emptyList<WeatherModel>()

        viewModel = SearchWeatherViewModel(getSearchKeyLengthUseCase, searchWeatherUseCase)

        viewModel.viewState.observeForTesting {
            viewModel.searchWeather(searchKey)
            coVerify { searchWeatherUseCase(searchKey) }

            verifySequence {
                it.onChanged(CommonViewState.EMPTY)
                it.onChanged(CommonViewState.LOADING)
                it.onChanged(SearchWeatherViewModel.SearchKeyInvalid())
            }

            val weatherModel = viewModel.weathers.getOrAwaitValue()
            Assert.assertEquals (expectedModel, weatherModel)
        }
    }

    @Test
    fun searchWeather_cityNotFoundException() {
        val searchWeatherUseCase = mockSearchUseCase( exception =  CityNotFoundException())
        val searchKey = "hanoi"

        val expectedModel = emptyList<WeatherModel>()

        viewModel = SearchWeatherViewModel(getSearchKeyLengthUseCase, searchWeatherUseCase)

        viewModel.viewState.observeForTesting {
            viewModel.searchWeather(searchKey)
            coVerify { searchWeatherUseCase(searchKey) }

            verifySequence {
                it.onChanged(CommonViewState.EMPTY)
                it.onChanged(CommonViewState.LOADING)
                it.onChanged(CommonViewState.NO_RESULT_RESPONSE)
            }

            val weatherModel = viewModel.weathers.getOrAwaitValue()
            Assert.assertEquals (expectedModel, weatherModel)
        }
    }


    @Test
    fun searchWeather_unKnowException() {
        val searchWeatherUseCase = mockSearchUseCase( exception =  Exception())
        val searchKey = "hanoi"

        val expectedModel = emptyList<WeatherModel>()

        viewModel = SearchWeatherViewModel(getSearchKeyLengthUseCase, searchWeatherUseCase)

        viewModel.viewState.observeForTesting {
            viewModel.searchWeather(searchKey)
            coVerify { searchWeatherUseCase(searchKey) }

            verifySequence {
                it.onChanged(CommonViewState.EMPTY)
                it.onChanged(CommonViewState.LOADING)
                it.onChanged(CommonViewState.UN_KNOW_ERROR)
            }

            val weatherModel = viewModel.weathers.getOrAwaitValue()
            Assert.assertEquals (expectedModel, weatherModel)
        }
    }
}