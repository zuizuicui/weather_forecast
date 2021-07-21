package com.example.weather.weatherforecast.ui.weatherforecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.weather.weatherforecast.R
import com.example.weather.weatherforecast.di.WeatherForecastComponent
import javax.inject.Inject

class WeatherForecastFragment : BaseFragment() {

    companion object {
        fun newInstance() = WeatherForecastFragment()
    }

    private lateinit var viewModel: WeatherForecastModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun inject(fragmentComponent: WeatherForecastComponent) {
        fragmentComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(
            WeatherForecastModel::class.java
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.weather_forecast_fragment, container, false)
    }
}