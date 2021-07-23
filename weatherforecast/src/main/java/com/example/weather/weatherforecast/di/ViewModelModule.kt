package com.example.weather.weatherforecast.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weather.common.di.ViewModelKey
import com.example.weather.common.ui.ViewModelFactory
import com.example.weather.weatherforecast.ui.searchweather.SearchWeatherViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SearchWeatherViewModel::class)
    abstract fun bindWeatherForecastModel(searchWeatherViewModel: SearchWeatherViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}