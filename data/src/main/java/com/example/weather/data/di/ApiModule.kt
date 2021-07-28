package com.example.weather.data.di

import com.example.weather.data.remote.weather.WeatherApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@InstallIn(SingletonComponent::class)
@Module
class ApiModule {
    @Provides
    internal fun provideWeatherApi(retrofit: Retrofit) = retrofit.create(WeatherApi::class.java)
}