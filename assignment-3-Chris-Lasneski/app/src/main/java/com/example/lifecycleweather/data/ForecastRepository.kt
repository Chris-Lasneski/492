package com.example.lifecycleweather.data

import com.example.lifecycleweather.api.OpenWeatherService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ForecastRepository(
    private val service: OpenWeatherService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,

) {
    suspend fun loadRepositories(
        query: String,
        units: String,
        apiKey: String
    ): Result<FiveDayForecast> = withContext(dispatcher) {
            var first: Boolean = true
            var preLoc: String
            var preUnit: String
            // WHY DO YOU NOT LIKE NESTING THE RESPONSE STUFF?? LET ME CACHE YOU
            if (first) {
                preLoc = query
                preUnit = units
            }
            val response = service.loadFiveDayForecast(query, units, apiKey)
            if (response.isSuccessful) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception(response.errorBody()?.string()))
            }
    }
//    private fun buildForecastQuery(
//        query: String,
//        units: String,
//        apiKey: String
//    ) : String {
//        var fullQuery = query
//        if(!TextUtils.isEmpty(units)) {
//            fullQuery += "&units=$units"
//        }
//        fullQuery
//    }
}