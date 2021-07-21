package com.example.weather.weatherforecast.ui.weatherforecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.weather.common.ui.CommonFragment
import com.example.weather.weatherforecast.R
import com.example.weather.weatherforecast.di.WeatherForecastComponent
import com.example.weather.weatherforecast.di.WeatherForecastComponentProvider
import javax.inject.Inject

abstract class BaseFragment : CommonFragment() {
    override fun attachComponent(savedInstanceState: Bundle?) {
        context?.applicationContext?.let {
            val componentProvider = it as  WeatherForecastComponentProvider
            inject(componentProvider.provideWeatherForecastComponent())
        }
    }

    protected abstract fun inject(fragmentComponent: WeatherForecastComponent)
}