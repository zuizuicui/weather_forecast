package com.example.weather.di.module

import com.example.weather.common.di.qualifier.DispatcherDefault
import com.example.weather.common.di.qualifier.DispatcherIO
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers

@Module
class DispatcherModule {

    @DispatcherIO
    @Provides
    internal fun provideDispatcherIO() = Dispatchers.IO

    @DispatcherDefault
    @Provides
    internal fun provideDispatcherDefault() = Dispatchers.Default
}