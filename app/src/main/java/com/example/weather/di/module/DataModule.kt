package com.example.weather.di.module

import com.example.weather.common.di.qualifier.DispatcherDefault
import com.example.weather.common.di.qualifier.DispatcherIO
import com.example.weather.data.di.ApiModule
import com.example.weather.data.di.ConverterModule
import com.example.weather.data.di.NetworkModule
import com.example.weather.data.di.RepositoryModule
import com.example.weather.data.dispatcher.DataDispatchers
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher

@Module( includes = [ConverterModule::class, RepositoryModule::class, ApiModule::class, NetworkModule::class])
class DataModule {
    @Provides
    internal fun domainDispatcherProvider(
        @DispatcherIO dispatcherIO: CoroutineDispatcher,
        @DispatcherDefault dispatcherDefault: CoroutineDispatcher
    ): DataDispatchers {
        return DataDispatchers(
            io = dispatcherIO,
            default = dispatcherDefault
        )
    }

}