package com.example.weather.data.local.table.typeconvert

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun fromTimestamp(listId: List<Long>?): String? {
        return listId?.let { it.map { it.toString() }.joinToString { SEPARATOR } }
    }

    @TypeConverter
    fun dateToTimestamp(listId: String?): List<Long>? {
        return listId?.split(SEPARATOR)?.map { it.toLong() }?.toList()
    }

    companion object {
        const val SEPARATOR = ", "
    }
}