package com.example.weather

import android.app.Application
import com.example.weather.di.DaggerAppComponent
import com.example.weather.weatherforecast.di.WeatherForecastComponent
import com.example.weather.weatherforecast.di.WeatherForecastComponentProvider

class WeatherApplication : Application(), WeatherForecastComponentProvider {

    private val appComponent = DaggerAppComponent.builder().build()

    override fun provideWeatherForecastComponent(): WeatherForecastComponent {
        return appComponent.weatherForecastComponent().create()
    }
}