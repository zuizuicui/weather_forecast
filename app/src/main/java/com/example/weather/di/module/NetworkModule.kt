package com.example.weather.di.module

import com.example.weather.data.remote.WeatherApi
import com.google.gson.Gson
//import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
//import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
class NetworkModule {

    @Provides
    internal fun provideRetrofit(client: OkHttpClient, factory: GsonConverterFactory): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org")
            .client(client)
            .addConverterFactory(factory)
            .build()

    @Provides
    internal fun provideOkHttpClient(certPinner: CertificatePinner): OkHttpClient {
        return OkHttpClient.Builder()
//            .certificatePinner(certPinner)
            .build()
    }

    @Provides
    internal fun provideCertificatePinner() =
        CertificatePinner.Builder()
            .add("klara.tech", "")
            .build()

    @Provides
    @Singleton
    fun provideGsonConverterFactory(gson: Gson?): GsonConverterFactory? {
        return GsonConverterFactory.create(gson)
    }
//    @Provides
//    internal fun provideMoshi(): Moshi = Moshi.Builder().build()

    @Provides
    internal fun provideWeatherApi(retrofit: Retrofit) = retrofit.create(WeatherApi::class.java)
}