package com.example.weather.data.repository.converter

import com.example.weather.data.mock.model.MockWeatherElement
import com.example.weather.data.mock.model.MockWeatherElementDto
import com.example.weather.data.repository.dispatcher.DataDispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class DataConverterTest {
    private val testDispatcher = TestCoroutineDispatcher()
    lateinit var converter : WeatherElementConvert

    @Before
    fun setup() {
        converter = WeatherElementConvertImpl(DataDispatchers(testDispatcher, testDispatcher) )
    }

    @Test
    fun `test convert weatherElementDto to weather element` () {
        testDispatcher.runBlockingTest {
            val result =
                converter.convertToListModel(listOf(MockWeatherElementDto.weatherElementDto))
            assertEquals(listOf(MockWeatherElement.weatherElement), result)
        }
    }

    @Test
    fun `test null_object_nested_field to default` () {
        testDispatcher.runBlockingTest {
            val result =
                converter.convertToListModel(listOf(MockWeatherElementDto.nullObjectWithNestedField))
            assertNotNull(result)
        }
    }

    @Test
    fun `test null_all_field convert to default` () {
        testDispatcher.runBlockingTest {
            val result = converter.convertToListModel(listOf(MockWeatherElementDto.nullAllField))
            assertNotNull(result)
        }
    }
}