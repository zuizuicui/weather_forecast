package com.example.weather.domain.interaction

import com.example.weather.domain.entity.LengthWeatherSearchKeyRule
import org.junit.Assert.*
import org.junit.Test

class GetKeySearchLengthUseCaseTest {
    @Test
    fun testGetLengthKeySearch_shouldReturn3() {
        val sut = GetKeySearchLengthUseCase(LengthWeatherSearchKeyRule())
        assertEquals(3, sut())
    }
}