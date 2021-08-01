package com.example.weather.data.remote.config.wrapresponse

import okhttp3.Request
import okhttp3.ResponseBody
import okio.Timeout
import retrofit2.*
import java.io.IOException

internal class WrapResponseCall<S : Any>(
    private val delegate: Call<S>,
    private val errorConverter: Converter<ResponseBody, WrapResponse.ApiError>
) : Call<WrapResponse<S>> {

    override fun enqueue(callback: Callback<WrapResponse<S>>) {
        return delegate.enqueue(object : Callback<S> {
            override fun onResponse(call: Call<S>, response: Response<S>) {
                val body = response.body()
                val code = response.code()
                val error = response.errorBody()

                if (response.isSuccessful) {
                    if (body != null) {
                        callback.onResponse(
                            this@WrapResponseCall,
                            Response.success(WrapResponse.Success(body))
                        )
                    } else {
                        // Response is successful but the body is null
                        callback.onResponse(
                            this@WrapResponseCall,
                            Response.success(WrapResponse.UnknownError(NullPointerException()))
                        )
                    }
                } else {

                    val errorBody = when {
                        error == null -> null
                        error.contentLength() == 0L -> null
                        else -> try {
                            errorConverter.convert(error)
                        } catch (ex: Exception) {
                            null
                        }
                    }
                    if (errorBody != null) {
                        callback.onResponse(
                            this@WrapResponseCall,
                            Response.success(errorBody)
                        )
                    } else {
                        callback.onResponse(
                            this@WrapResponseCall,
                            Response.success(WrapResponse.UnknownError(Exception(response.message())))
                        )
                    }
                }
            }

            override fun onFailure(call: Call<S>, throwable: Throwable) {
                val networkResponse = when (throwable) {
                    is IOException -> WrapResponse.WrapError(throwable)
                    else -> WrapResponse.UnknownError(throwable)
                }
                callback.onResponse(this@WrapResponseCall, Response.success(networkResponse))
            }
        })
    }

    override fun isExecuted() = delegate.isExecuted

    override fun clone() : Call<WrapResponse<S>> = WrapResponseCall(delegate.clone(), errorConverter)

    override fun isCanceled() = delegate.isCanceled

    override fun cancel() = delegate.cancel()

    override fun execute(): Response<WrapResponse<S>> {
        throw UnsupportedOperationException("NetworkResponseCall doesn't support execute")
    }

    override fun request(): Request = delegate.request()
    override fun timeout(): Timeout = delegate.timeout()
}