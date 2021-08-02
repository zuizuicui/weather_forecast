package com.example.weather.di.module

import com.example.weather.common.di.qualifier.DispatcherDefault
import com.example.weather.common.di.qualifier.DispatcherIO
import com.example.weather.data.di.ApiModule
import com.example.weather.data.di.ConverterModule
import com.example.weather.data.di.LocalModule
import com.example.weather.data.di.RepositoryModule
import com.example.weather.data.repository.dispatcher.DataDispatchers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher

@InstallIn(SingletonComponent::class)
@Module( includes = [ConverterModule::class, RepositoryModule::class, ApiModule::class, LocalModule::class])
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