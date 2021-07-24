package com.example.weather.weatherforecast.ui.searchweather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.domain.model.WeatherElement
import com.example.weather.weatherforecast.databinding.ListItemWeatherBinding

class SearchWeatherAdapter : ListAdapter<WeatherElement, RecyclerView.ViewHolder>(PlantDiffCallback()) {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return WeatherInfoViewHolder(
                ListItemWeatherBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val plant = getItem(position)
            (holder as WeatherInfoViewHolder).bind(plant)
        }

        class WeatherInfoViewHolder(
            private val binding: ListItemWeatherBinding
        ) : RecyclerView.ViewHolder(binding.root) {

            fun bind(item: WeatherElement) {
                binding.apply {
                    weather = item
                    executePendingBindings()
                }
            }
        }
    }

    private class PlantDiffCallback : DiffUtil.ItemCallback<WeatherElement>() {

        override fun areItemsTheSame(oldItem: WeatherElement, newItem: WeatherElement): Boolean {
            return oldItem.date == newItem.date
        }

        override fun areContentsTheSame(oldItem: WeatherElement, newItem: WeatherElement): Boolean {
            return oldItem == newItem
        }
    }