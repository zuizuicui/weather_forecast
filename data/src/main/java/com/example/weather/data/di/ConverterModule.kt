package com.example.weather.data.di

import com.example.weather.data.repository.converter.WeatherElementConvert
import com.example.weather.data.repository.converter.WeatherElementConvertImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class ConverterModule {
    @Provides
    internal fun provideWeatherElementConvert(impl : WeatherElementConvertImpl) : WeatherElementConvert = impl
}