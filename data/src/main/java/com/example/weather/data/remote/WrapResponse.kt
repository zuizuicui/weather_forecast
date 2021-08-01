package com.example.weather.data.remote

import java.io.IOException

sealed class WrapResponse<out T : Any> {
    /**
     * Success response with body
     */
    data class Success<T : Any>(val body: T) : WrapResponse<T>()

    /**
     * Failure response with body
     */
    data class ApiError(val body: String, val code: Int) : WrapResponse<Nothing>()


    sealed class OtherError(open val error: Throwable) : WrapResponse<Nothing>()

    /**
     * Network error
     */
    data class WrapError(override  val error: IOException) : OtherError(error)

    /**
     * For example, json parsing error
     */
    data class UnknownError(override val error: Throwable) : OtherError(error)
}