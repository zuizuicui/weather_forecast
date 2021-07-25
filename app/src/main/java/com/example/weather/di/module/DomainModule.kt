package com.example.weather.di.module

import com.example.weather.common.di.qualifier.DispatcherDefault
import com.example.weather.domain.dispatcher.DomainDispatchers
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher

@Module
class DomainModule {
    @Provides
    internal fun domainDispatcherProvider(@DispatcherDefault dispatcher: CoroutineDispatcher): DomainDispatchers {
        return DomainDispatchers(dispatcher)
    }
}