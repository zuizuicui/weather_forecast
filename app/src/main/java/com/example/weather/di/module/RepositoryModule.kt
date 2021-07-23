package com.example.weather.di.module

import com.example.weather.data.WeatherRepositoryImpl
import com.example.weather.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    internal fun provideWeatherRepository(impl: WeatherRepositoryImpl) : WeatherRepository = impl
}