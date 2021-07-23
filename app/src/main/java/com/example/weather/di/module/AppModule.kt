package com.example.weather.di.module

import android.app.Application
import android.content.Context
import com.example.weather.common.di.qualifier.ApplicationContext
import dagger.Module
import dagger.Provides

@Module(includes = [NetworkModule::class, RepositoryModule::class, DispatcherModule::class])
class AppModule(private val application: Application) {

    @Provides
    @ApplicationContext
    internal fun provideContext(): Context {
        return application
    }

    @Provides
    internal fun provideApplication(): Application {
        return application
    }
}