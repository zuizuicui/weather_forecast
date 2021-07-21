package com.example.weather.weatherforecast.di

import androidx.lifecycle.ViewModel
import com.example.weather.common.di.ModuleScope
import com.example.weather.common.ui.ViewModelFactory
import javax.inject.Inject
import javax.inject.Provider

@ModuleScope
class WeatherViewModelFactory@Inject constructor(creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>)
    : ViewModelFactory(creators)