package com.example.weather.data.config

import android.content.Context
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.util.concurrent.TimeUnit

class CacheControlInterceptor(val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = if (NetworkUtil.hasNetworkConnection(context))
            request.newBuilder()
                .cacheControl(
                    CacheControl.Builder()
                        .maxAge(5, TimeUnit.SECONDS)
                        .build()
                )
                .build()
        else
            request.newBuilder()
                .cacheControl(
                    CacheControl.Builder()
                        .onlyIfCached()
                        .maxStale( 1, TimeUnit.DAYS)
                        .build()

                )
                .build()

        return chain.proceed(request)
    }

}