package com.example.weather.data.remote.config.wrapresponse

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class WrapResponseAdapterFactory : CallAdapter.Factory() {
    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {

        // suspend functions wrap the response type in `Call`
        if (Call::class.java != getRawType(returnType)) {
            return null
        }

        // check first that the return type is `ParameterizedType`
        check(returnType is ParameterizedType) {
            "return type must be parameterized as Call<WrapResponse<<Foo>> or Call<WarpResponse<out Foo>>"
        }

        // get the response type inside the `Call` type
        val responseType = getParameterUpperBound(0, returnType)
        // if the response type is not ApiResponse then we can't handle this type, so we return null
        if (getRawType(responseType) != WrapResponse::class.java) {
            return null
        }

        // the response type is ApiResponse and should be parameterized
        check(responseType is ParameterizedType) { "Response must be parameterized as NetworkResponse<Foo> or NetworkResponse<out Foo>" }

        val successBodyType = getParameterUpperBound(0, responseType)
        val errorBodyType = WrapResponse.ApiError::class.java

        val errorBodyConverter =
            retrofit.nextResponseBodyConverter<WrapResponse.ApiError>(null, errorBodyType, annotations)

        return NetworkResponseAdapter<Any>(successBodyType, errorBodyConverter)
    }
}


class NetworkResponseAdapter<S : Any>(
    private val successType: Type,
    private val errorBodyConverter: Converter<ResponseBody, WrapResponse.ApiError>
) : CallAdapter<S, Call<WrapResponse<S>>> {

    override fun responseType(): Type = successType

    override fun adapt(call: Call<S>): Call<WrapResponse<S>> {
        return WrapResponseCall(call, errorBodyConverter)
    }
}

