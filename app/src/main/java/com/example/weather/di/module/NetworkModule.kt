package com.example.weather.di.module

import android.content.Context
import com.example.weather.common.di.qualifier.ApplicationContext
import com.example.weather.data.config.CacheControlInterceptor
import com.example.weather.data.config.RetrofitConfig
import com.example.weather.data.remote.WeatherApi
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
    internal fun provideOkHttpClient(
        certPinner: CertificatePinner,
        cache: Cache,
        cacheControlInterceptor: CacheControlInterceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .certificatePinner(certPinner)
            .cache(cache)
            .addInterceptor(cacheControlInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    internal fun provideCache(@ApplicationContext context: Context) =
        RetrofitConfig.cache(context)

    @Provides
    internal fun provideCacheControlInterceptor(@ApplicationContext context: Context) =
        RetrofitConfig.cacheControlInterceptor(context)

    @Provides
    internal fun provideHttpLoggingInterceptor()  =
        RetrofitConfig.httpLoggingInterceptor

    @Provides
    internal fun provideCertificatePinner() =
        CertificatePinner.Builder()
            .add("api.openweathermap.org", "sha256/axmGTWYycVN5oCjh3GJrxWVndLSZjypDO6evrHMwbXg=")
            .add("api.openweathermap.org", "sha256/4a6cPehI7OG6cuDZka5NDZ7FR8a60d3auda+sKfg4Ng=")
            .add("api.openweathermap.org", "sha256/x4QzPSC810K5/cMjb05Qm4k3Bw5zBn4lTdO/nEW/Td4=")
            .build()

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create(Gson())
    }

    @Provides
    internal fun provideWeatherApi(retrofit: Retrofit) = retrofit.create(WeatherApi::class.java)
}