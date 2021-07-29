package com.example.weather.domain.entity.exception

class NetworkErrorException : RuntimeException()
class FailRequestException : Exception()
class CityNotFoundException : Exception()
class InvalidInputException : Exception()