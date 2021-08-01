package com.example.weather.weatherforecast.ui.searchweather

import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.weather.common.ui.CommonViewState
import com.example.weather.weatherforecast.R
import com.example.weather.weatherforecast.WeatherForecastActivity
import com.example.weather.weatherforecast.mock.MockSearchWeather.createWeatherModel
import com.example.weather.weatherforecast.mock.MockSearchWeather.mockSearchWeatherViewModel
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.every
import io.mockk.verify
import org.hamcrest.Matchers.not
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class SearchWeatherFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @BindValue
    val viewModel = mockSearchWeatherViewModel()

    @Test
    fun openScreen_showCorrectUi() {
        ActivityScenario.launch(WeatherForecastActivity::class.java)

        onView(withId(R.id.et_search_input)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_get_weather)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_get_weather)).check(matches(isNotEnabled()))
        onView(withId(R.id.empty_page)).check(matches(isDisplayed()))
    }

    @Test
    fun inputSearchKey_buttonNotShow() {
        ActivityScenario.launch(WeatherForecastActivity::class.java)

        onView(withId(R.id.et_search_input)).perform(typeText("ha"))
        onView(withId(R.id.btn_get_weather)).check(matches(isNotEnabled()))
    }


    @Test
    fun inputSearchKey_buttonShow() {
        ActivityScenario.launch(WeatherForecastActivity::class.java)

        onView(withId(R.id.et_search_input)).perform(typeText("han"))
        onView(withId(R.id.btn_get_weather)).check(matches(isEnabled()))
    }

    @Test
    fun searchWeather_triggerSearchWeatherOnViewModel() {
        ActivityScenario.launch(WeatherForecastActivity::class.java)

        val searchKey = "hanoi"

        onView(withId(R.id.et_search_input)).perform(typeText(searchKey))
        onView(withId(R.id.btn_get_weather)).perform(click())

        verify { viewModel.searchWeather(searchKey) }
    }

    @Test
    fun searchWeather_showProgressLoading() {
        every { viewModel.viewState } returns MutableLiveData(CommonViewState.LOADING)

        ActivityScenario.launch(WeatherForecastActivity::class.java)

        onView(withId(R.id.loading)).check(matches(isDisplayed()))
    }


    @Test
    fun searchWeather_showNoResultPage() {
        every { viewModel.viewState } returns MutableLiveData(CommonViewState.NO_RESULT_RESPONSE)

        ActivityScenario.launch(WeatherForecastActivity::class.java)

        onView(withId(R.id.loading)).check(matches(not(isDisplayed())))
        onView(withId(R.id.no_result_page)).check(matches(isDisplayed()))
    }

    @Test
    fun searchWeather_showListOfWeather() {
        every { viewModel.weatherElements } returns MutableLiveData(listOf(createWeatherModel()))
        every { viewModel.viewState } returns MutableLiveData(CommonViewState.HAS_RESULT)

        val activityScenario = ActivityScenario.launch(WeatherForecastActivity::class.java)

        onView(withId(R.id.loading)).check(matches(not(isDisplayed())))

        activityScenario.onActivity { activity ->
            // Disable animations in RecyclerView
            val itemShowing =
                (activity.findViewById<RecyclerView>(R.id.weatherList)).adapter?.itemCount
            Assert.assertEquals(1, itemShowing)
        }
    }
}