//package com.example.weather.data.memorycache
//
//import androidx.test.ext.junit.runners.AndroidJUnit4
//import com.example.weather.data.remote.SearchWeatherResponse
//import kotlinx.coroutines.test.TestCoroutineDispatcher
//import kotlinx.coroutines.test.runBlockingTest
//import org.junit.Assert.assertEquals
//import org.junit.Test
//import org.junit.runner.RunWith
//
//@RunWith(AndroidJUnit4::class)
//class ResponseLruCacheTest {
//    private val testDispatcher = TestCoroutineDispatcher()
//
//    private val response = SearchWeatherResponse(
//        code = "200",
//        message = "",
//        city = null,
//        count = 1,
//        list = listOf()
//    )
//
//    @Test
//    fun testSaveCache() {
//        val key = "hanoi"
//
//        val cache = ResponseLruCache()
//
//        cache.saveResponseToCache(key, response)
//        assertEquals(response, cache.retrieveResponseFromCache(key))
//    }
//
//}