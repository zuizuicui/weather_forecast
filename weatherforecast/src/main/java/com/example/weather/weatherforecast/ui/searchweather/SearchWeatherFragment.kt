package com.example.weather.weatherforecast.ui.searchweather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import com.example.weather.weatherforecast.R
import com.example.weather.weatherforecast.databinding.SearchWeatherFragmentBinding
import com.example.weather.weatherforecast.di.WeatherForecastComponent
import com.example.weather.weatherforecast.ui.base.BaseFragment
import javax.inject.Inject

class SearchWeatherFragment : BaseFragment() {

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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = SearchWeatherFragmentBinding.inflate(inflater, container, false)

        val itemDivider = DividerItemDecoration(context, VERTICAL)
        val adapter = SearchWeatherAdapter()

        binding.weatherList.addItemDecoration(itemDivider)
        binding.weatherList.adapter = adapter
        binding.searchText = ""

        binding.btnGetWeather.setOnClickListener {_ ->
            binding.searchText?.let {
                viewModel.searchWeather(it)
            }
        }

        subscribeUi(adapter, binding)
        return binding.root
    }

    private fun subscribeUi(adapter: SearchWeatherAdapter, binding: SearchWeatherFragmentBinding) {
        viewModel.weatherElement.observe(viewLifecycleOwner) { result ->
            binding.hasWeatherInfo = !result.isNullOrEmpty()
            adapter.submitList(result)
        }

        viewModel.minSearchKeyLength.observe(viewLifecycleOwner) { minSearchKeyLength ->
            binding.minSearchKeyLength = minSearchKeyLength
        }
    }
}