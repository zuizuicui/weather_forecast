package com.example.weather.data.mock.cache

import com.example.weather.data.memorycache.ResponseCache
import com.example.weather.data.remote.SearchWeatherResponse
import com.example.weather.data.remote.WeatherElementDto

object MockCache {
    fun <T> fakeCache(data: T? = null): ResponseCache {
        val cache = object : ResponseCache {
            override suspend fun saveResponseToCache(key: String, response: Any?) {

            }

            override suspend fun <T> retrieveResponseFromCache(key: String): T? {
                return data?.let { it as T }
            }

        }
        return cache
    }

    fun createResponse(list: List<WeatherElementDto>) = SearchWeatherResponse(
        code = "200",
        message = "",
        city = null,
        count = 1,
        list = list
    )
}