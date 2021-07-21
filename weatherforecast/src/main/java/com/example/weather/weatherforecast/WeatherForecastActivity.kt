package com.example.weather.weatherforecast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.weather.weatherforecast.ui.weatherforecast.WeatherForecastFragment

class WeatherForecastActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.weather_forecast_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, WeatherForecastFragment.newInstance())
                .commitNow()
        }
    }
}