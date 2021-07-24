package com.example.weather.data.di

import com.example.weather.data.converter.DataConverter
import dagger.Module
import dagger.Provides

@Module
class ConverterModule {
    @Provides
    internal fun provideWeatherElementConvert() = DataConverter.weatherElementConvert
}