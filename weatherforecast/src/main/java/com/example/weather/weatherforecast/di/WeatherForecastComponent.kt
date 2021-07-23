package com.example.weather.weatherforecast.di

import com.example.weather.common.di.scope.ModuleScope
import com.example.weather.weatherforecast.ui.searchweather.SearchWeatherFragment
import dagger.Subcomponent

@ModuleScope
@Subcomponent(modules = [ViewModelModule::class])
interface WeatherForecastComponent {
    fun inject(weatherForecastFragment: SearchWeatherFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): WeatherForecastComponent
    }

}