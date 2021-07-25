package com.example.weather.weatherforecast.di

import androidx.fragment.app.Fragment
import com.example.weather.common.di.scope.FeatureScope
import com.example.weather.weatherforecast.ui.searchweather.SearchWeatherFragment
import dagger.BindsInstance
import dagger.Subcomponent

@FeatureScope
@Subcomponent(modules = [ViewModelModule::class])
interface FragmentComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance fragment: Fragment): FragmentComponent
    }

    fun inject(weatherForecastFragment: SearchWeatherFragment)
}