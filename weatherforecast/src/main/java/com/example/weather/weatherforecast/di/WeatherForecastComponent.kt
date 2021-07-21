package com.example.weather.weatherforecast.di

import com.example.weather.common.di.ModuleScope
import com.example.weather.weatherforecast.ui.weatherforecast.WeatherForecastFragment
import dagger.Subcomponent

@ModuleScope
@Subcomponent(modules = [ViewModelModule::class])
interface WeatherForecastComponent {
    fun inject(weatherForecastFragment: WeatherForecastFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): WeatherForecastComponent
    }

}