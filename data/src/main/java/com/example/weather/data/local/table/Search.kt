package com.example.weather.data.local.table

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity
data class SearchKeyEntity (
    @PrimaryKey
    val searchKey: String,
    val createdTime: Long,
    val expiredTime: Long
)

data class SearchResult(
    @Embedded val searchResult: SearchKeyEntity,
    @Relation(
        entity = WeatherEntity::class,
        parentColumn = "searchKey",
        entityColumn = "searchKey"
    )
    val weatherWithIcons: List<WeatherWithIcons>,
    @Relation(
        entity = CityEntity::class,
        parentColumn = "searchKey",
        entityColumn = "searchKey"
    )
    val city: CityEntity
)