package com.example.weather.weatherforecast.ui.searchweather

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.weather.domain.interaction.searchweather.SearchWeatherInfoUseCase
import com.example.weather.domain.model.WeatherElement
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
    private lateinit var viewModel :SearchWeatherViewModel

    @Before
    fun setup() {
        viewModel = SearchWeatherViewModel(searchWeatherUseCase)
    }

    @Test
    fun searchWeather_returnempty() {
        coEvery { searchWeatherUseCase(any()) } coAnswers {listOf(
            WeatherElement(1),
            WeatherElement(2))
        }

        val keySearch = "ave"
        viewModel.searchWeather(keySearch)


        coVerify { searchWeatherUseCase(keySearch) }
        Assert.assertEquals (2, viewModel.weatherElement.getOrAwaitValue().size)
    }
}