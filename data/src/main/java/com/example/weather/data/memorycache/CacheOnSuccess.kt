package com.example.weather.data.memorycache

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

/**
 * Cache the first non-error result from an async computation passed as [block].
 *
 * Usage:
 *
 * ```
 * val cachedSuccess: CacheOnSuccess<Int> = CacheOnSuccess(onErrorFallback = { 3 }) {
 *     delay(1_000) // compute value using coroutines
 *     5
 * }
 *
 * cachedSuccess.getOrAwait() // get the result from the cache, calling [block], or fallback on
 *                            // exception
 * ```
 *
 * @param onErrorFallback: Invoke this if [block] throws exception other than cancellation, the
 *        result of this lambda will be returned for this call to [getOrAwait] but will not be
 *        cached for future calls to [getOrAwait]
 * @param block Suspending lambda that produces the cached value. The first non-exceptional value
 *        returned by [block] will be cached, and future calls to [getOrAwait] will return the
 *        cached value or throw a [CancellationException].
 */
class CacheOnSuccess<T: Any>(
    private val onErrorFallback: (suspend () -> T)? = null,
    private val block: suspend (String) -> T,
    private val cache: ResponseCache
) {
    private val mutex =  Mutex()

    /**
     * Get the current cached value, or await the completion of [block].
     *
     * The result of [block] will be cached after the fist successful result, and future calls to
     * [getOrAwait] will return the cached value.
     *
     * If multiple coroutines call [getOrAwait] before [block] returns, then [block] will only
     * execute one time. If successful, they will all get the same success result. In the case of
     * error it will not cache, and a later call to [getOrAwait] will retry the [block].
     *
     * If [onErrorFallback] is not null, this function will *always* call the lambda in case of
     * error and will never cache the error result.
     *
     * @throws Throwable the exception thrown by [block] if [onErrorFallback] is not provided.
     * @throws CancellationException will throw a [CancellationException] if called in a cancelled
     *          coroutine context. This will happen even when reading the cached value.
     */
    suspend fun getOrAwait(key: String): T {
        return supervisorScope {
            // only allow one coroutine to try running block at a time by using a coroutine-base
            // Mutex
            val currentDeferred = mutex.withLock {
                cache.retrieveResponseFromCache<T>(key)?.let {
                    return@withLock  async {it}
                }

                async {
                    // Note: mutex is not held in this async block
                    block(key).also {
                        cache.saveResponseToCache(key, it)
                    }
                }
            }

            // await the result, with our custom error handling
            currentDeferred.safeAwait()
        }
    }

    private suspend fun Deferred<T>.safeAwait(): T {
        try {
            return await()
        } catch (throwable: Throwable) {

            if (throwable is CancellationException) {
                throw throwable
            }

            onErrorFallback?.let { fallback -> return fallback() }

            throw throwable
        }
    }

}