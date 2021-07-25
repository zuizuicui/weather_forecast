package com.example.weather.di

import android.app.Application
import android.content.Context
import com.example.weather.common.di.qualifier.ApplicationContext
import com.example.weather.common.di.CommonModule
import com.example.weather.di.module.AppModule
import com.example.weather.di.module.DataModule
import com.example.weather.di.module.DomainModule
import com.example.weather.di.module.SubcomponentsModule
import com.example.weather.weatherforecast.di.WeatherForecastComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, CommonModule::class, SubcomponentsModule::class, DataModule::class, DomainModule::class])
interface AppComponent {
    @ApplicationContext
    fun context(): Context

    fun application(): Application

    fun weatherForecastComponent(): WeatherForecastComponent.Factory

}