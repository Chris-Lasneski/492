package com.example.connectedweather.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class WeatherResults (
    val list: List<ForecastPeriod>
) : java.io.Serializable