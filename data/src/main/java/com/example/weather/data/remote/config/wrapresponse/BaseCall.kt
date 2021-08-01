package com.example.weather.data.remote.config.wrapresponse

import okhttp3.Request
import okhttp3.ResponseBody
import okio.Timeout
import retrofit2.*

internal abstract class BaseCall<S : Any, U : Any>(
    private val delegate: Call<S>,
) : Call<U> {
    override fun enqueue(callback: Callback<U>) {
        return delegate.enqueue(object : Callback<S> {
            override fun onResponse(call: Call<S>, response: Response<S>) {
                val body = response.body()
                val error = response.errorBody()
                val message = response.message()

                if (response.isSuccessful) {
                    callback.onResponse(
                        this@BaseCall,
                        adaptResponseSuccess(body)
                    )
                } else {
                    callback.onResponse(
                        this@BaseCall,
                        adaptResponseError(error, message)
                    )
                }
            }

            override fun onFailure(call: Call<S>, throwable: Throwable) {
                callback.onResponse(this@BaseCall, adaptOnFail(throwable))
            }
        })
    }

    abstract fun adaptResponseSuccess(body: S?): Response<U>

    abstract fun adaptResponseError(errorBody: ResponseBody?, message: String) : Response<U>

    abstract fun adaptOnFail(throwable: Throwable) : Response<U>

    override fun isExecuted() = delegate.isExecuted

    override fun isCanceled() = delegate.isCanceled

    override fun cancel() = delegate.cancel()

    override fun execute(): Response<U> {
        throw UnsupportedOperationException("NetworkResponseCall doesn't support execute")
    }

    override fun request(): Request = delegate.request()
    override fun timeout(): Timeout = delegate.timeout()
}