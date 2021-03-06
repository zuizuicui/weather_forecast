package com.example.weather.weatherforecast.ui.searchweather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.weatherforecast.databinding.ListItemWeatherBinding

class SearchWeatherAdapter : ListAdapter<WeatherModel, RecyclerView.ViewHolder>(WeatherDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return WeatherViewHolder(
            ListItemWeatherBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val weatherModel = getItem(position)
        (holder as WeatherViewHolder).bind(weatherModel)
    }

    class WeatherViewHolder(
        private val binding: ListItemWeatherBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: WeatherModel) {
            binding.apply {
                weather = item
                executePendingBindings()
            }
        }
    }
}

private class WeatherDiffCallback : DiffUtil.ItemCallback<WeatherModel>() {

    override fun areItemsTheSame(oldItem: WeatherModel, newItem: WeatherModel): Boolean {
        return oldItem.date == newItem.date
    }

    override fun areContentsTheSame(oldItem: WeatherModel, newItem: WeatherModel): Boolean {
        return oldItem == newItem
    }
}