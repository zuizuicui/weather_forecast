package com.example.weather.weatherforecast.ui.searchweather

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.weather.weatherforecast.R
import com.example.weather.weatherforecast.databinding.SearchWeatherFragmentBinding
import com.example.weather.weatherforecast.di.WeatherForecastComponent
import com.example.weather.weatherforecast.ui.base.BaseFragment
import javax.inject.Inject

class SearchWeatherFragment : BaseFragment(R.layout.search_weather_fragment) {

    companion object {
        fun newInstance() = SearchWeatherFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: SearchWeatherViewModel

    override fun inject(fragmentComponent: WeatherForecastComponent) {
        fragmentComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(
            SearchWeatherViewModel::class.java
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = SearchWeatherFragmentBinding.bind(view)
        val adapter = SearchWeatherAdapter()
        binding.weatherList.adapter = adapter
        subscribeUi(adapter, binding)
    }

    private fun subscribeUi(adapter: SearchWeatherAdapter, binding: SearchWeatherFragmentBinding) {
        viewModel.weatherInfo.observe(viewLifecycleOwner) { result ->
            binding.hasWeatherInfo = !result.isNullOrEmpty()
            adapter.submitList(result)
        }
    }
}