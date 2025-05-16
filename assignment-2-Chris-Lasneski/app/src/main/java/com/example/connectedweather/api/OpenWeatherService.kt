package com.example.connectedweather.api

import com.example.connectedweather.data.WeatherResults
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherService {
    @GET("forecast")
    fun getWeather(
        @Query("q") query: String = "Corvallis,OR,US",
        @Query("appid") key: String = "5ecc8edc0705da24095d05b7c9e5653f",
        @Query("units") units: String = "imperial"
    ) : Call<WeatherResults>

    companion object {
        private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

        fun create(): OpenWeatherService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(OpenWeatherService::class.java)
        }
    }
}