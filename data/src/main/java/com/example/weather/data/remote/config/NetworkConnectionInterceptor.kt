package com.example.weather.data.remote.config

import android.content.Context
import com.example.weather.data.remote.config.NetworkUtil.hasNetworkConnection
import com.example.weather.domain.entity.exception.NetworkErrorException
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class NetworkConnectionInterceptor(private val context: Context) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request().newBuilder().build()
        if (!hasNetworkConnection(context)) {
            throw NetworkErrorException()
        }
        return chain.proceed(request)
    }


}