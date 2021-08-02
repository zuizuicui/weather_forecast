package com.example.weather.data.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.weather.data.local.table.SearchResult
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class WeatherDaoTest {
    private lateinit var weatherDao: WeatherDao
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java
        ).build()
        weatherDao = db.weatherDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun readWeatherWhenDbEmpty() {
        val lastWeatherId = weatherDao.getLastWeatherKey()
        Assert.assertEquals(0, lastWeatherId)

        val searchKey = weatherDao.getSearchKey("hanoi")
        Assert.assertNull(searchKey)

        val searchResult = weatherDao.getSearchResult("hanoi")
        Assert.assertNull(searchResult)
    }

    @Test
    @Throws(Exception::class)
    fun writeWeatherAndReadInList() {
        weatherDao.insertSearchKey(Mock.searchKey)
        weatherDao.insertCity(Mock.city)
        weatherDao.insertWeather(listOf(Mock.weather))

        weatherDao.insertWeatherIcons(listOf(Mock.weatherIcon))
        weatherDao.insertWeatherWeatherItemCrossRef(listOf(Mock.weatherWeatherIconEntity))

        // test get search key
        val searchKey = weatherDao.getSearchKey("hanoi")
        Assert.assertEquals(Mock.searchKey, searchKey)

        // test get search result
        val result = weatherDao.getSearchResult("hanoi")
        Assert.assertEquals(Mock.result, result)

        // test delete
        weatherDao.deletedSearchKey(searchKey!!)
        val resultAfterDeleted = weatherDao.getSearchResult("hanoi")
        Assert.assertEquals(listOf<SearchResult>(), resultAfterDeleted)
    }
}