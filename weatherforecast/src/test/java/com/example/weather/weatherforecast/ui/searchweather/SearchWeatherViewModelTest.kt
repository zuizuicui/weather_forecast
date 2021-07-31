package com.example.weather.weatherforecast.ui.searchweather

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.weather.common.ui.CommonViewState
import com.example.weather.domain.interaction.searchweather.GetKeySearchLengthUseCase
import com.example.weather.domain.interaction.searchweather.WeatherResultItem
import com.example.weather.weatherforecast.mock.MockSearchUseCase.mockSearchUseCase
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

    private val getKeySearchLengthUseCase: GetKeySearchLengthUseCase = mockk()
    private lateinit var viewModel :SearchWeatherViewModel

    @Before
    fun setup() {
        coEvery { getKeySearchLengthUseCase() } returns 3
    }

    @Test
    fun getKeySearchLengthUseCase_return3() {
        viewModel = SearchWeatherViewModel(getKeySearchLengthUseCase, mockk())
        Assert.assertEquals (3, viewModel.minSearchKeyLength.getOrAwaitValue())
        Assert.assertEquals (CommonViewState.EMPTY, viewModel.viewState.getOrAwaitValue())
    }

    @Test
    fun searchWeather_returnEmpty() {
        val searchWeatherUseCase = mockSearchUseCase( listOf(
            createWeatherResult(),
            createWeatherResult()
        ))
        val keySearch = "hanoi"

        val expectedModel = listOf(
            createWeatherModel(),
            createWeatherModel()
        )

        viewModel = SearchWeatherViewModel(getKeySearchLengthUseCase, searchWeatherUseCase)

        viewModel.viewState.observeForTesting {
            viewModel.searchWeather(keySearch)
            coVerify { searchWeatherUseCase(keySearch) }

            verifySequence {
                it.onChanged(CommonViewState.EMPTY)
                it.onChanged(CommonViewState.LOADING)
                it.onChanged(CommonViewState.HAS_RESULT)
            }

            val weatherModel = viewModel.weatherElement.getOrAwaitValue()
            Assert.assertEquals (expectedModel, weatherModel)
        }
    }

    private fun createWeatherResult(
        date: Long = 1627272000,
        averageTemp: Double = 30.0,
        pressure: Long = 100,
        humidity: Long = 70,
        description: String = "moderate rain"
    ) = WeatherResultItem(
        date, averageTemp, pressure, humidity, description
    )

    private fun createWeatherModel(
        date: String = "Mon, 26 Jul 2021",
        averageTemp: String = "30",
        pressure: String = "100",
        humidity: String = "70",
        description: String = "moderate rain"
    ) = WeatherModel (
        date, averageTemp, pressure, humidity, description
    )
}