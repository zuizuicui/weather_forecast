package com.example.weather.di.module

import com.example.weather.data.di.ApiModule
import com.example.weather.data.di.ConverterModule
import com.example.weather.data.di.RepositoryModule
import dagger.Module

@Module( includes = [ConverterModule::class, RepositoryModule::class, ApiModule::class])
interface DataModule