package com.example.weather.data.converter

import com.example.weather.data.remote.*
import com.example.weather.domain.model.*
import org.mapstruct.InheritInverseConfiguration
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers

object DataConverter {
    val cityConverter = Mappers.getMapper(CityConverter::class.java)
    val weatherElementConvert = Mappers.getMapper(WeatherElementConvert::class.java)!!
}

@Mapper (uses = [CoordinateConverter::class])
interface CityConverter {
    fun convertToDto(city: City) : CityDto

    @InheritInverseConfiguration
    fun convertToModel(cityDto: CityDto) : City
}


@Mapper(uses = [TempConvert::class, FeelsLikeConvert::class, WeatherConvert::class])
interface CoordinateConverter {
    fun convertToDto(coord: Coordinate) : CoordinateDto

    @InheritInverseConfiguration
    fun convertToModel(coordDto: CoordinateDto) : Coordinate
}


@Mapper
interface WeatherElementConvert {

    fun convertToDto(weatherElement: WeatherElement) : WeatherElementDto

    @InheritInverseConfiguration
    fun convertToModel(weatherElementDto: WeatherElementDto) : WeatherElement

    fun convertToListDto(weatherElementDtos: List<WeatherElement>) : List<WeatherElementDto>

    fun convertToListModel(weatherElements: List<WeatherElementDto>) : List<WeatherElement>
}

@Mapper
interface FeelsLikeConvert {
    fun convertToDto(feelsLike: FeelsLike) : FeelsLikeDto

    @InheritInverseConfiguration
    fun convertToModel(feelsLikeDto: FeelsLikeDto) : FeelsLike
}

@Mapper
interface TempConvert {
    fun convertToDto(temp: Temp) : TempDto

    @InheritInverseConfiguration
    fun convertToModel(tempDto: TempDto) : Temp
}

@Mapper
interface WeatherConvert {

    fun convertToDto(weather: Weather) : WeatherDto

    @InheritInverseConfiguration
    fun convertToModel(weatherDto: WeatherDto) : Weather

    fun convertToListDto(weathers: List<Weather>) : List<WeatherDto>

    fun convertToListModel(weathers: List<WeatherDto>) : List<Weather>
}

