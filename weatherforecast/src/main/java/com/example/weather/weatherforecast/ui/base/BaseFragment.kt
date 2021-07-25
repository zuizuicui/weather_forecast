package com.example.weather.weatherforecast.ui.base

import android.os.Bundle
import com.example.weather.common.ui.CommonFragment
import com.example.weather.weatherforecast.di.FragmentComponent
import com.example.weather.weatherforecast.di.WeatherForecastComponentProvider

abstract class BaseFragment : CommonFragment() {
    override fun attachComponent(savedInstanceState: Bundle?) {
        context?.applicationContext?.let {
            val componentProvider = it as  WeatherForecastComponentProvider
            val weatherForecastComponent = componentProvider.provideWeatherForecastComponent()
            val fragmentComponent = weatherForecastComponent.fragmentComponent().create(this)
            inject(fragmentComponent)
        }
    }

    protected abstract fun inject(fragmentComponent: FragmentComponent)
}