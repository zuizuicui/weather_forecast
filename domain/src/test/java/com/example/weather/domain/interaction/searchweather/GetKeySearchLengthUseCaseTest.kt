package com.example.weather.domain.interaction.searchweather

import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Test

class GetKeySearchLengthUseCaseTest {
    private val testDispatcher = TestCoroutineDispatcher()

    @Test
    fun testGetLengthKeySearch_shouldReturn3() {
        val sut = GetKeySearchLengthUseCase()
        testDispatcher.runBlockingTest {
            assertEquals(3, sut())
        }
    }
}