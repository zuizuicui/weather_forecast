package com.example.weather.weatherforecast.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weather.common.di.ViewModelKey
import com.example.weather.weatherforecast.ui.weatherforecast.WeatherForecastModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(WeatherForecastModel::class)
    abstract fun bindWeatherForecastModel(weatherForecastModel: WeatherForecastModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: WeatherViewModelFactory): ViewModelProvider.Factory
}