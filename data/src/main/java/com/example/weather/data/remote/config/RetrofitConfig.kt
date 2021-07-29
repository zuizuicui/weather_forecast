package com.example.weather.data.remote.config

import android.content.Context
import com.example.weather.data.BuildConfig
import okhttp3.Cache
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import okhttp3.logging.HttpLoggingInterceptor.Level.NONE

object RetrofitConfig {
    private const val CACHE_SIZE = (5 * 1024 * 1024).toLong()

    val cache = { context : Context ->
        Cache(context.cacheDir, CACHE_SIZE)
    }

    val cacheControlInterceptor = {context : Context ->
        CacheControlInterceptor(context)
    }

    val httpLoggingInterceptor : HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) BODY else NONE
        }
}