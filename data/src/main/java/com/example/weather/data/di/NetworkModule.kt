package com.example.weather.data.di

import com.example.weather.data.remote.config.CacheControlInterceptor
import com.example.weather.data.remote.config.RetrofitConfig
import com.example.weather.data.remote.config.wrapresponse.WrapResponseAdapterFactory
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Provides
    internal fun provideRetrofit(
        client: OkHttpClient,
        factory: GsonConverterFactory,
        wrapResponseFactory: CallAdapter.Factory
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org")
            .client(client)
            .addConverterFactory(factory)
            .addCallAdapterFactory(wrapResponseFactory)
            .build()

    @Provides
    internal fun provideOkHttpClient(
        certPinner: CertificatePinner,
        cacheControlInterceptor: CacheControlInterceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .certificatePinner(certPinner)
            .addInterceptor(cacheControlInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    internal fun provideAdapterFactory() : CallAdapter.Factory = WrapResponseAdapterFactory()

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
}