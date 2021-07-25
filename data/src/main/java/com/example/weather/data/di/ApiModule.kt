package com.example.weather.data.di

import com.example.weather.data.remote.weather.WeatherApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class ApiModule {
    @Provides
    internal fun provideWeatherApi(retrofit: Retrofit) = retrofit.create(WeatherApi::class.java)
}