package com.example.weather.weatherforecast.di

import com.example.weather.common.di.scope.ModuleScope
import dagger.Subcomponent

@ModuleScope
@Subcomponent(modules = [ViewModelModule::class, SubcomponentsModule::class])
interface WeatherForecastComponent {
    fun fragmentComponent(): FragmentComponent.Factory

    @Subcomponent.Factory
    interface Factory {
        fun create(): WeatherForecastComponent
    }

}