package com.example.weather.weatherforecast

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.weather.weatherforecast.ui.searchweather.SearchWeatherFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherForecastActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.weather_forecast_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, SearchWeatherFragment.newInstance())
                .commitNow()
        }
    }
}