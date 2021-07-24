package com.example.weather.weatherforecast.ui.searchweather

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.weather.weatherforecast.R
import com.example.weather.weatherforecast.WeatherForecastActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SearchWeatherFragmentTest {
    @Rule
    @JvmField
    val activityTestRule = ActivityTestRule(WeatherForecastActivity::class.java)

    @Test
    fun showUi() {
        onView(withId(R.id.btn_get_weather)).perform(ViewActions.click())
    }
}