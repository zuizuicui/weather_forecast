package com.example.weather.weatherforecast.ui.searchweather

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.weather.weatherforecast.R
import com.example.weather.weatherforecast.WeatherForecastActivity
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.mockk
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class SearchWeatherFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @BindValue
    val mockMyViewModel= mockk<SearchWeatherViewModel>(relaxed = true)

    @Test
    fun showUi() {
        ActivityScenario.launch(WeatherForecastActivity::class.java)
        onView(withId(R.id.et_search_input)).perform(ViewActions.typeText("hanoi"))
        onView(withId(R.id.btn_get_weather)).perform(ViewActions.click())
        onView(withId(R.id.loading)).check(matches(isDisplayed()))
    }
}