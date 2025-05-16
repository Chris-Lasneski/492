package com.example.connectedweather.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ForecastPeriod(
    @Json(name = "dt") val dateTime: Long,
    @Json(name = "pop") val precip: Double,
    @Json(name = "main") val temps: MainClass,
    @Json(name = "weather") val description: List<WeatherClass>
) : java.io.Serializable

@JsonClass(generateAdapter = true)
data class MainClass(
    //val temp: Int,
    //val feelsLike: Int,
    @Json(name = "temp_min") val tempMin: Double,
    @Json(name = "temp_max") val tempMax: Double,
    //val pressure: Int,
    //val seaLevel: Int,
    //val grndLevel: Int,
    //val humidity: Int,
    //val tempKF: Int
) : java.io.Serializable

@JsonClass(generateAdapter = true)
data class WeatherClass(
    @Json(name = "main") val shortDesc: String,
    @Json(name = "description") val longDesc: String
) : java.io.Serializable