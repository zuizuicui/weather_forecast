package com.example.weather.data.remote

import com.google.gson.annotations.SerializedName

interface ResponseCode {
    val code: String?
    val message: String?

    fun isSuccess() = code == "200"
}

data class SearchWeatherResponse(
    @SerializedName("cod")
    override var code: String?,
    override var message: String?,
    val city: CityDto?,
    @SerializedName("cnt")
    val count: Long?,
    @SerializedName("list")
    val list: List<WeatherElementDto>?
) : ResponseCode

data class CityDto(
    val id: Long?,
    val name: String?,
    @SerializedName("coord")
    val coordinate: CoordinateDto?,
    val country: String?,
    val population: Long?,
    val timezone: Long?
)

data class CoordinateDto(
    val lon: Double?,
    val lat: Double?
)

data class WeatherElementDto(
    @SerializedName("dt")
    val date: Long?,
    val sunrise: Long?,
    val sunset: Long?,
    @SerializedName("temp")
    val temperature: TemperatureDto?,
    @SerializedName("feels_like")
    val feelsLike: FeelsLikeDto?,
    val pressure: Long?,
    val humidity: Long?,
    val weather: List<WeatherDto>?,
    val speed: Double?,
    val deg: Long?,
    val gust: Double?,
    val clouds: Long?,
    val pop: Double?,
    val rain: Double?
)

data class FeelsLikeDto(
    val day: Double?,
    val night: Double?,
    val eve: Double?,
    val morn: Double?
)

data class TemperatureDto(
    val day: Double?,
    val min: Double?,
    val max: Double?,
    val night: Double?,
    val eve: Double?,
    val morn: Double?
)

data class WeatherDto(
    val id: Long?,
    val main: String?,
    val description: String?,
    val icon: String?
)