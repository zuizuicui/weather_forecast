package com.example.weather.data.local.table

import androidx.room.*
import androidx.room.ForeignKey.CASCADE

@Entity(
    foreignKeys =
    [
        ForeignKey(
            entity = SearchKeyEntity::class,
            parentColumns = ["searchKey"],
            childColumns = ["searchKey"],
            onDelete = CASCADE
        )
    ],
    indices = [Index("searchKey")]
)
data class CityEntity(
    @PrimaryKey
    val id: Long,
    val name: String?,
    @Embedded
    val coordinate: CoordinateEntity?,
    val country: String?,
    val population: Long?,
    val timezone: Long?,
    val searchKey: String
)

data class CoordinateEntity(
    @ColumnInfo(name = "lon")
    val lon: Double?,
    @ColumnInfo(name = "lat")
    val lat: Double?
)

@Entity(
    foreignKeys =
    [
        ForeignKey(
            entity = SearchKeyEntity::class,
            parentColumns = ["searchKey"],
            childColumns = ["searchKey"],
            onDelete = CASCADE
        )
    ],
    indices = [Index("searchKey")]
)
data class WeatherEntity(
    @ColumnInfo(name = "weather_id")
    @PrimaryKey(autoGenerate = true) var id: Long,
    val date: Long?,
    val sunrise: Long?,
    val sunset: Long?,
    @Embedded
    val temperature: TemperatureEntity?,
    @Embedded
    val feelsLike: FeelsLikeEntity?,
    val pressure: Long?,
    val humidity: Long?,
    val speed: Double?,
    val deg: Long?,
    val gust: Double?,
    val clouds: Long?,
    val pop: Double?,
    val rain: Double?,
    val searchKey: String
)

data class FeelsLikeEntity(
    @ColumnInfo(name = "feels_like_day")
    val day: Double?,
    @ColumnInfo(name = "feels_like_night")
    val night: Double?,
    @ColumnInfo(name = "feels_like_eve")
    val eve: Double?,
    @ColumnInfo(name = "feels_like_morn")
    val morn: Double?
)

data class TemperatureEntity(
    @ColumnInfo(name = "temp_day")
    val day: Double?,
    @ColumnInfo(name = "temp_min")
    val min: Double?,
    @ColumnInfo(name = "temp_max")
    val max: Double?,
    @ColumnInfo(name = "temp_night")
    val night: Double?,
    @ColumnInfo(name = "temp_even")
    val eve: Double?,
    @ColumnInfo(name = "temp_morn")
    val morn: Double?
)

@Entity(
    primaryKeys = ["weather_id", "icon_id"],
    foreignKeys =
    [
        ForeignKey(
            entity = WeatherEntity::class,
            parentColumns = ["weather_id"],
            childColumns = ["weather_id"],
            onDelete = CASCADE
        ),
        ForeignKey(
            entity = WeatherIconEntity::class,
            parentColumns = ["icon_id"],
            childColumns = ["icon_id"],
            onDelete = CASCADE
        )
    ],
    indices = [
        Index("weather_id"),
        Index("icon_id")
    ]

)
data class WeatherWeatherIconCrossRef(
    @ColumnInfo(name = "weather_id")
    val weatherId: Long,
    @ColumnInfo(name = "icon_id")
    val iconId: Long
)

@Entity
data class WeatherIconEntity(
    @ColumnInfo(name = "icon_id")
    @PrimaryKey val id: Long,
    val main: String?,
    val description: String?,
    val icon: String?
)

data class WeatherWithIcons(
    @Embedded val weather: WeatherEntity,
    @Relation(
        parentColumn = "weather_id",
        entityColumn = "icon_id",
        associateBy = Junction(WeatherWeatherIconCrossRef::class)
    )
    val icons: List<WeatherIconEntity>
)
