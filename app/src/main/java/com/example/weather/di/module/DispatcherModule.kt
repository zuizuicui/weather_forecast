package com.example.weather.di.module

import com.example.weather.common.di.qualifier.DispatcherDefault
import com.example.weather.common.di.qualifier.DispatcherIO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers

@InstallIn(SingletonComponent::class)
@Module
class DispatcherModule {

    @DispatcherIO
    @Provides
    internal fun provideDispatcherIO() = Dispatchers.IO

    @DispatcherDefault
    @Provides
    internal fun provideDispatcherDefault() = Dispatchers.Default
}