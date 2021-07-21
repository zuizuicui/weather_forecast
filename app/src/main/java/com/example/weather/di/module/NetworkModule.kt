package com.example.weather.di.module

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
class NetworkModule {
    @Provides

    @Singleton
    internal fun provideRetrofit(client: OkHttpClient, moshi: Moshi): Retrofit =
        Retrofit.Builder()
            .baseUrl("")
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

    internal fun provideOkHttpClient() = OkHttpClient.Builder().build()

    internal fun provideMoshi(): Moshi = Moshi.Builder().build()
}