package com.example.connectedweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.connectedweather.api.OpenWeatherService
import com.example.connectedweather.data.ForecastPeriod
import com.example.connectedweather.data.WeatherResults
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.squareup.moshi.Moshi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    private var forecasts = mutableListOf<ForecastPeriod>()
    private val openWeatherService = OpenWeatherService.create()
    private val openWeatherServiceAdapter = ForecastAdapter()

    private lateinit var forecastListRV: RecyclerView
    private lateinit var errorMessageTV: TextView
    private lateinit var loadingIndicator: CircularProgressIndicator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        errorMessageTV = findViewById(R.id.tv_error_message)
        loadingIndicator = findViewById(R.id.loading_indicator)

        forecastListRV = findViewById<RecyclerView>(R.id.rv_forecast_list)
        forecastListRV.layoutManager = LinearLayoutManager(this)
        forecastListRV.setHasFixedSize(true)

        //val forecastDataItems = this.initForecastPeriods()
        weatherSearch()
        forecastListRV.adapter = ForecastAdapter()

        //forecastListRV.scrollToPosition(0)
    }

    private fun weatherSearch() {
        loadingIndicator.visibility = View.VISIBLE

        openWeatherService.getWeather()
            .enqueue(object : Callback<WeatherResults> {
                override fun onResponse(call: Call<WeatherResults>, response: Response<WeatherResults>) {
                    loadingIndicator.visibility = View.INVISIBLE

                    Log.d("MainActivity", "Status code: ${response.code()}")
                    if(response.isSuccessful) {
                        openWeatherServiceAdapter.updateList(response.body()?.list)
                        forecastListRV.visibility = View.VISIBLE
                        errorMessageTV.visibility = View.INVISIBLE
                    }
                    else {
                        errorMessageTV.text = "Error: ${response.errorBody()?.string()}"
                        forecastListRV.visibility = View.INVISIBLE
                        errorMessageTV.visibility = View.VISIBLE
                    }
                }

                override fun onFailure(call: Call<WeatherResults>, t: Throwable) {
                    errorMessageTV.text = "error: ${t.message}"
                    loadingIndicator.visibility = View.INVISIBLE
                    forecastListRV.visibility = View.INVISIBLE
                    errorMessageTV.visibility = View.VISIBLE
                    Log.d("MainActivity", "Error making API call: ${t.message}")
                }
            })
    }

//    private fun parseForecast(body: WeatherResults?): MutableList<ForecastPeriod> {
//        var parsed = mutableListOf<ForecastPeriod>()
//
//        if(body != null) {
//            for (i in body.list) {
//                val date = i.dateTime
//                //val calendar = Calendar.getInstance()
//            }
//        }
//    }

    /*
     * This function simply initializes a list of dummy weather data.  You won't need this anymore
     * once you start fetching data from the OpenWeather API.
     */
//    private fun initForecastPeriods(): MutableList<ForecastPeriod> {
//        return mutableListOf(
////            ForecastPeriod(
////                year = 2023,
////                month = 0,
////                day = 14,
////                highTemp = 51,
////                lowTemp = 43,
////                pop = 0.25,
////                shortDesc = "Mostly sunny",
////                longDesc = "Mostly sunny with clouds increasing in the evening"
////            ),
////            ForecastPeriod(
////                year = 2023,
////                month = 0,
////                day = 15,
////                highTemp = 55,
////                lowTemp = 39,
////                pop = 0.8,
////                shortDesc = "AM showers",
////                longDesc = "Morning showers receding in the afternoon, with patches of sun later in the day"
////            ),
////            ForecastPeriod(
////                year = 2023,
////                month = 0,
////                day = 16,
////                highTemp = 47,
////                lowTemp = 39,
////                pop = 0.1,
////                shortDesc = "AM fog/PM clouds",
////                longDesc = "Cooler, with morning fog lifting into a cloudy day"
////            ),
////            ForecastPeriod(
////                year = 2023,
////                month = 0,
////                day = 17,
////                highTemp = 53,
////                lowTemp = 36,
////                pop = 0.6,
////                shortDesc = "AM showers",
////                longDesc = "Chance of light rain in the morning"
////            ),
////            ForecastPeriod(
////                year = 2023,
////                month = 0,
////                day = 18,
////                highTemp = 49,
////                lowTemp = 33,
////                pop = 0.1,
////                shortDesc = "Partly cloudy",
////                longDesc = "Early clouds clearing as the day goes on; nighttime temperatures approaching freezing"
////            ),
////            ForecastPeriod(
////                year = 2023,
////                month = 0,
////                day = 19,
////                highTemp = 49,
////                lowTemp = 36,
////                pop = 0.15,
////                shortDesc = "Partly cloudy",
////                longDesc = "Clouds increasing throughout the day with periods of sun interspersed"
////            ),
////            ForecastPeriod(
////                year = 2023,
////                month = 0,
////                day = 20,
////                highTemp = 48,
////                lowTemp = 38,
////                pop = 0.3,
////                shortDesc = "Mostly cloudy",
////                longDesc = "Cloudy all day, with a slight chance of rain late in the evening"
////            ),
////            ForecastPeriod(
////                year = 2023,
////                month = 0,
////                day = 21,
////                highTemp = 45,
////                lowTemp = 35,
////                pop = 0.5,
////                shortDesc = "Showers",
////                longDesc = "Cooler with periods of rain throughout the day"
////            ),
////            ForecastPeriod(
////                year = 2023,
////                month = 0,
////                day = 22,
////                highTemp = 45,
////                lowTemp = 30,
////                pop = 0.3,
////                shortDesc = "AM showers",
////                longDesc = "Cool with a chance of rain in the morning; nighttime temperatures just below freezing"
////            ),
////            ForecastPeriod(
////                year = 2023,
////                month = 0,
////                day = 23,
////                highTemp = 44,
////                lowTemp = 31,
////                pop = 0.5,
////                shortDesc = "Few showers",
////                longDesc = "Cool with a chance rain throughout the day; nighttime temperatures just below freezing"
////            )
//        )
//    }
}