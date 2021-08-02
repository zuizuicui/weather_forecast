package com.example.weather.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.weather.data.local.table.*

@Database(
    version = 1,
    entities = [
        SearchKeyEntity::class,
        CityEntity::class,
        WeatherEntity::class,
        WeatherIconEntity::class,
        WeatherWeatherIconCrossRef::class]
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao

    companion object {
        private var INSTANCE: AppDatabase? = null
        fun getAppDatabase(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = buildDatabase(context)
            }
            return INSTANCE!!
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "weather"
            )
                .allowMainThreadQueries()
                .build()
        }
    }
}