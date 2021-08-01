package com.example.weather.data.remote.config.wrapresponse

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Converter
import retrofit2.Response
import java.io.IOException

internal class WrapResponseCall<S : Any> (
    private val delegate: Call<S>,
    private val errorConverter: Converter<ResponseBody, WrapResponse.ApiError>
) : BaseCall<S, WrapResponse<S>> (delegate)  {

    override fun adaptResponseSuccess(body: S?): Response<WrapResponse<S>> {
        return if (body != null) {
            Response.success(WrapResponse.Success(body))
        } else {
            // Response is successful but the body is null
            Response.success(WrapResponse.UnknownError(NullPointerException()))
        }
    }

    override fun adaptResponseError(errorBody: ResponseBody?, message: String): Response<WrapResponse<S>> {
        val error = when {
            errorBody == null -> null
            errorBody.contentLength() == 0L -> null
            else -> try {
                errorConverter.convert(errorBody)
            } catch (ex: Exception) {
                null
            }
        }
        return if (error != null) {
            Response.success(error)
        } else {
            Response.success(WrapResponse.UnknownError(Exception(message)))
        }
    }

    override fun adaptOnFail(throwable: Throwable): Response<WrapResponse<S>> {
        val networkResponse = when (throwable) {
            is IOException -> WrapResponse.WrapError(throwable)
            else -> WrapResponse.UnknownError(throwable)
        }
        return Response.success(networkResponse)
    }


    override fun clone() : Call<WrapResponse<S>> = WrapResponseCall(delegate.clone(), errorConverter)

}