package com.example.weather.di.module

import com.example.weather.weatherforecast.di.WeatherForecastComponent
import dagger.Module

@Module(subcomponents = [WeatherForecastComponent::class])
class SubcomponentsModule {}