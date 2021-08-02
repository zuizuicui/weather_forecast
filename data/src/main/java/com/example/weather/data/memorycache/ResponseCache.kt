package com.example.weather.data.memorycache

interface ResponseCache {
    suspend fun saveResponseToCache(key: String, response: Any?)

    suspend fun<T> retrieveResponseFromCache(key: String): T?
}