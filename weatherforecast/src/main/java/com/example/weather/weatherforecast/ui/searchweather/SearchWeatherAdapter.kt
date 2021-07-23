package com.example.weather.weatherforecast.ui.searchweather

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.domain.model.WeatherInfo
import com.example.weather.weatherforecast.databinding.ListItemWeatherBinding

class SearchWeatherAdapter : ListAdapter<WeatherInfo, RecyclerView.ViewHolder>(PlantDiffCallback()) {

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

            fun bind(item: WeatherInfo) {
                binding.apply {
                    weather = item
                    executePendingBindings()
                }
            }
        }
    }

    private class PlantDiffCallback : DiffUtil.ItemCallback<WeatherInfo>() {

        override fun areItemsTheSame(oldItem: WeatherInfo, newItem: WeatherInfo): Boolean {
            return oldItem.date == newItem.date
        }

        override fun areContentsTheSame(oldItem: WeatherInfo, newItem: WeatherInfo): Boolean {
            return oldItem == newItem
        }
    }