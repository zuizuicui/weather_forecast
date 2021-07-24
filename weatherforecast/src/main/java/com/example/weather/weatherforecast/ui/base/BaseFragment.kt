package com.example.weather.weatherforecast.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import com.example.weather.common.ui.CommonFragment
import com.example.weather.weatherforecast.di.WeatherForecastComponent
import com.example.weather.weatherforecast.di.WeatherForecastComponentProvider

abstract class BaseFragment : CommonFragment() {
    override fun attachComponent(savedInstanceState: Bundle?) {
        context?.applicationContext?.let {
            val componentProvider = it as  WeatherForecastComponentProvider
            inject(componentProvider.provideWeatherForecastComponent())
        }
    }

    protected abstract fun inject(fragmentComponent: WeatherForecastComponent)
}