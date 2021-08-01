package com.example.weather.weatherforecast.ui.searchweather

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.weather.domain.entity.exception.CityNotFoundException
import com.example.weather.domain.interaction.searchweather.GetKeySearchLengthUseCase
import com.example.weather.domain.interaction.searchweather.SearchWeatherInfoUseCase
import com.example.weather.weatherforecast.R
import com.example.weather.weatherforecast.WeatherForecastActivity
import com.example.weather.weatherforecast.mock.MockSearchWeather
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.coEvery
import io.mockk.mockk
import org.hamcrest.Matchers
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class SearchWeatherFragmentIntegrationTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @BindValue
    val getKeySearchLengthUseCase = mockk<GetKeySearchLengthUseCase>()

    @BindValue
    val searchWeatherInfoUseCase = mockk<SearchWeatherInfoUseCase>()

    @Before
    fun setup () {
        coEvery { getKeySearchLengthUseCase() } returns 3
    }

    @Test
    fun openScreen_showCorrectUi() {
        ActivityScenario.launch(WeatherForecastActivity::class.java)

        onView(withId(R.id.et_search_input))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.btn_get_weather))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.btn_get_weather))
            .check(ViewAssertions.matches(ViewMatchers.isNotEnabled()))
        onView(withId(R.id.empty_page))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun searchWeather_showNoResultPage() {
        val searchKey = "abnchdv"
        coEvery { searchWeatherInfoUseCase(searchKey) } throws CityNotFoundException()

        ActivityScenario.launch(WeatherForecastActivity::class.java)

        onView(withId(R.id.et_search_input)).perform(typeText(searchKey))
        onView(withId(R.id.btn_get_weather)).perform(click())

        onView(withId(R.id.loading))
            .check(ViewAssertions.matches(Matchers.not(ViewMatchers.isDisplayed())))
        onView(withId(R.id.no_result_page))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun searchWeather_showListWeather() {
        val searchKey = "hanoi"
        coEvery { searchWeatherInfoUseCase(searchKey) } returns listOf(MockSearchWeather.createWeatherResult())

        val activityScenario = ActivityScenario.launch(WeatherForecastActivity::class.java)

        onView(withId(R.id.et_search_input)).perform(typeText(searchKey))
        onView(withId(R.id.btn_get_weather)).perform(click())

        activityScenario.onActivity { activity ->
            // Disable animations in RecyclerView
            val itemShowing =
                (activity.findViewById<RecyclerView>(R.id.weatherList)).adapter?.itemCount
            Assert.assertEquals(1, itemShowing)
        }

    }
}