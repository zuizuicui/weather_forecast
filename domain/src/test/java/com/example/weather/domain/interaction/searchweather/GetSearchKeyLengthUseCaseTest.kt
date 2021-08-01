package com.example.weather.domain.interaction.searchweather

import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Test

class GetSearchKeyLengthUseCaseTest {
    private val testDispatcher = TestCoroutineDispatcher()

    @Test
    fun testGetLengthSearchKey_shouldReturn3() {
        val sut = GetSearchKeyLengthUseCase()
        testDispatcher.runBlockingTest {
            assertEquals(3, sut())
        }
    }
}