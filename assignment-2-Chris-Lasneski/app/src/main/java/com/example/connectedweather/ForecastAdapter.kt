package com.example.connectedweather

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.connectedweather.data.ForecastPeriod
import com.google.android.material.snackbar.Snackbar
import java.util.Calendar
import java.util.*

class ForecastAdapter : RecyclerView.Adapter<ForecastAdapter.ViewHolder>() {

    var forecastPeriodList = listOf<ForecastPeriod>()

    fun updateList(newForecastList: List<ForecastPeriod>?) {
        forecastPeriodList = newForecastList ?: listOf()
        notifyDataSetChanged()
    }

    override fun getItemCount() = forecastPeriodList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.forecast_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(forecastPeriodList[position])
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val monthTV: TextView = view.findViewById(R.id.tv_month)
        private val dayTV: TextView = view.findViewById(R.id.tv_day)
        private val highTempTV: TextView = view.findViewById(R.id.tv_high_temp)
        private val lowTempTV: TextView = view.findViewById(R.id.tv_low_temp)
        private val shortDescTV: TextView = view.findViewById(R.id.tv_short_description)
        private val popTV: TextView = view.findViewById(R.id.tv_pop)
        private lateinit var currentForecastPeriod: ForecastPeriod

        init {
            view.setOnClickListener {
                Snackbar.make(
                    view,
                    currentForecastPeriod.description[0].longDesc,
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }

        fun bind(forecastPeriod: ForecastPeriod) {
            currentForecastPeriod = forecastPeriod

            val cal = Calendar.getInstance()
            cal.timeInMillis - currentForecastPeriod.dateTime * 1000
            //cal.set(forecastPeriod.year, forecastPeriod.month, forecastPeriod.day)

            monthTV.text = cal.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.getDefault())
            println("KALJKDJALKJFLKAJDFS      $monthTV.text")
            dayTV.text = cal.get(Calendar.DAY_OF_MONTH).toString()
            highTempTV.text = forecastPeriod.temps.tempMax.toString() + "°F"
            lowTempTV.text = forecastPeriod.temps.tempMin.toString() + "°F"
            popTV.text = (forecastPeriod.precip * 100.0).toInt().toString() + "% precip."
            shortDescTV.text = forecastPeriod.description[0].shortDesc
        }
    }
}