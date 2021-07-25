package com.example.weather.weatherforecast.ui.searchweather

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.weather.common.ui.CommonViewState
import com.example.weather.domain.interaction.searchweather.GetKeySearchLengthUseCase
import com.example.weather.domain.interaction.searchweather.SearchWeatherInfoUseCase
import com.example.weather.domain.interaction.searchweather.WeatherResultElement
import com.example.weather.weatherforecast.util.MainCoroutineRule
import com.example.weather.weatherforecast.util.getOrAwaitValue
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
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

    private val searchWeatherUseCase: SearchWeatherInfoUseCase = mockk()
    private val getKeySearchLengthUseCase: GetKeySearchLengthUseCase = mockk()
    private lateinit var viewModel :SearchWeatherViewModel

    @Before
    fun setup() {
        coEvery { getKeySearchLengthUseCase() } returns 3
        viewModel = SearchWeatherViewModel(getKeySearchLengthUseCase, searchWeatherUseCase)
    }

    @Test
    fun getKeySearchLengthUseCase_return3() {
        Assert.assertEquals (3, viewModel.minSearchKeyLength.getOrAwaitValue())
    }

    @Test
    fun searchWeather_returnEmpty() {
        coEvery { searchWeatherUseCase(any()) } coAnswers {listOf(
            createWeatherResult(),
            createWeatherResult())
        }

        val keySearch = "ave"
        viewModel.searchWeather(keySearch)

        coVerify { searchWeatherUseCase(keySearch) }
        Assert.assertEquals (2, viewModel.weatherElement.getOrAwaitValue().size)
    }

    @Test
    fun viewState_empty() {
        Assert.assertEquals (CommonViewState.EMPTY, viewModel.viewState.getOrAwaitValue())
    }

    private fun createWeatherResult(
        date: Long = 1627272000,
        averageTemp: Double = 30.0,
        pressure: Long = 100,
        humidity: Long = 70,
        description: String = ""
    ) = WeatherResultElement(
        date, averageTemp, pressure, humidity, description
    )
}