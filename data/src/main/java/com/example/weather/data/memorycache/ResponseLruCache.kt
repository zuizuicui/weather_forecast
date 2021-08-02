package com.example.weather.data.memorycache

import android.util.LruCache
import javax.inject.Inject

class ResponseLruCache @Inject constructor() : ResponseCache {
    companion object {
        const val CACHE_SIZE = 4 * 1024 * 1024 // 4MiB
        const val DEFAULT_EXPIRED_DURATION = 3 * 60 * 1000 // 3 Mins
    }

    private val lruCache = LruCache<String, CacheExpired>(CACHE_SIZE)

    override suspend fun saveResponseToCache(key: String, response: Any?) {
        try {
            lruCache.remove(key)

            response?.let {
                lruCache.put(key, CacheExpired(response = response, expiredTime = defaultExpiredTime()))
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun<T> retrieveResponseFromCache(key: String): T? {
        return try {
            getCacheExpired(key)?.response?.let { it as T  }
        } catch (e: Exception) {
            null
        }
    }

    private fun getCacheExpired(key: String) : CacheExpired? {
        val value = lruCache.get(key)
        return value?.expiredTime?.let { expiredTime ->
            if (expiredTime < System.currentTimeMillis()) {
                lruCache.remove(key)
                return null
            }
            value
        }
    }

    private fun defaultExpiredTime() : Long {
        return System.currentTimeMillis() + DEFAULT_EXPIRED_DURATION
    }
}