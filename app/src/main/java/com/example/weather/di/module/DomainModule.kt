package com.example.weather.di.module

import com.example.weather.common.di.qualifier.DispatcherDefault
import com.example.weather.domain.interaction.DomainDispatchers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher

@InstallIn(SingletonComponent::class)
@Module
class DomainModule {
    @Provides
    internal fun domainDispatcherProvider(@DispatcherDefault dispatcher: CoroutineDispatcher): DomainDispatchers {
        return DomainDispatchers(dispatcher)
    }
}