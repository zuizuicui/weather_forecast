package com.example.weather.data.di

import com.example.weather.data.memorycache.ResponseCache
import com.example.weather.data.memorycache.ResponseLruCache
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class CacheModule {
    @Provides
    @Singleton
    internal fun provideResponseCache(impl: ResponseLruCache) : ResponseCache = impl
}