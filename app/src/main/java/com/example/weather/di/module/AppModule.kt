package com.example.weather.di.module

import android.app.Application
import android.content.Context
import com.example.weather.data.di.NetworkModule
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module(includes = [DispatcherModule::class])
class AppModule(private val application: Application) {

    @Provides
    @Named("application.context")
    internal fun provideContext(): Context {
        return application
    }

    @Provides
    internal fun provideApplication(): Application {
        return application
    }
}