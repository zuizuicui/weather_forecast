package com.example.weather.weatherforecast.ui.searchweather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import com.example.weather.common.ui.CommonFragment
import com.example.weather.weatherforecast.databinding.SearchWeatherFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchWeatherFragment : CommonFragment() {
    companion object {
        fun newInstance() = SearchWeatherFragment()
    }

    private val viewModel: SearchWeatherViewModel by viewModels()

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

        binding.btnGetWeather.setOnClickListener {v ->
            doSearch(v, binding.searchText)
        }

        subscribeUi(adapter, binding)
        return binding.root
    }

    private fun doSearch(v: View, searchKey: String?) {
        searchKey?.let {
            viewModel.searchWeather(it)
            dismissKeyboard(v.windowToken)
            viewModel.searchWeather(searchKey)
        }
        // Dismiss keyboard
        dismissKeyboard(v.windowToken)
    }

    private fun subscribeUi(adapter: SearchWeatherAdapter, binding: SearchWeatherFragmentBinding) {
        viewModel.weatherElement.observe(viewLifecycleOwner) { result ->
            adapter.submitList(result)
        }

        viewModel.viewState.observe(viewLifecycleOwner) {
            binding.hasWeatherInfo = it
        }

        viewModel.minSearchKeyLength.observe(viewLifecycleOwner) { minSearchKeyLength ->
            binding.minSearchKeyLength = minSearchKeyLength
        }
    }
}