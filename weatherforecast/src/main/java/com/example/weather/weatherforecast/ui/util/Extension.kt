package com.example.weather.weatherforecast.ui.util

import java.text.SimpleDateFormat
import java.util.*

const val DATE_FORMAT = "EEE, dd MMM yyyy"

fun Long.dateFormat(): String {
    val maxSecond = 10000000000L
    val miniSecond = if(this > maxSecond) this else this*1000
    val date = Date(miniSecond)
    val format = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
    return format.format(date)
}