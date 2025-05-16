package com.example.roomyweather.ui

import android.content.Intent
import android.content.ActivityNotFoundException
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.preference.PreferenceManager
import com.bumptech.glide.Glide
import com.example.roomyweather.R
import com.example.roomyweather.data.ForecastCity
import com.example.roomyweather.data.ForecastPeriod
import com.example.roomyweather.util.getTempUnitsDisplay
import com.example.roomyweather.util.getWindSpeedUnitsDisplay
import com.example.roomyweather.util.openWeatherEpochToDate
import com.google.android.material.snackbar.Snackbar

const val EXTRA_FORECAST_PERIOD = "com.example.android.roomyweather.FORECAST_PERIOD"
const val EXTRA_FORECAST_CITY = "com.example.android.roomyweather.FORECAST_CITY"

class ForecastDetailFragment : Fragment(R.layout.activity_forecast_detail) {
    private val args: ForecastDetailFragmentArgs by navArgs()

    private val viewModel: BookmarkedForecastsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)

        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(requireContext())
        val units = sharedPrefs.getString(getString(R.string.pref_units_key), null)
        val tempUnitsDisplay = getTempUnitsDisplay(units, requireContext())
        val windUnitsDisplay = getWindSpeedUnitsDisplay(units, requireContext())

        Glide.with(this)
            //.load(forecastPeriod!!.iconUrl)
            .load(args.savedCity.iconUrl)
            .into(view.findViewById(R.id.iv_forecast_icon))

        view.findViewById<TextView>(R.id.tv_forecast_date).text = getString(
            R.string.forecast_date_time, openWeatherEpochToDate(args.savedCity.epoch, args.forecastCity.tzOffsetSec)
        )

        view.findViewById<TextView>(R.id.tv_low_temp).text = getString(
            R.string.forecast_temp,
            args.savedCity.lowTemp,
            tempUnitsDisplay
        )

        view.findViewById<TextView>(R.id.tv_high_temp).text = getString(
            R.string.forecast_temp,
            args.savedCity.highTemp,
            tempUnitsDisplay
        )

        view.findViewById<TextView>(R.id.tv_pop).text = getString(R.string.forecast_pop, args.savedCity.pop)

        view.findViewById<TextView>(R.id.tv_clouds).text = getString(R.string.forecast_clouds, args.savedCity.cloudCover)

        view.findViewById<TextView>(R.id.tv_wind).text = getString(
            R.string.forecast_wind,
            args.savedCity.windSpeed,
            windUnitsDisplay
        )

        view.findViewById<ImageView>(R.id.iv_wind_dir).rotation = args.savedCity.windDirDeg.toFloat()
                //forecastPeriod!!.windDirDeg.toFloat()

        view.findViewById<TextView>(R.id.tv_forecast_description).text = args.savedCity.description
            //forecastPeriod!!.description
    }

//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        inflater.inflate(R.menu.activity_main, menu)
//        //val settings = menu.findItem(R.id.action_settings)
//        //viewModel.getBookmarkedLocationByName(args.SavedCity.)
//    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return when (item.itemId) {
//            R.id.action_map -> {
//                viewForecastCityOnMap()
//                true
//            }
//            R.id.action_settings -> {
//                val intent = Intent(this, SettingsActivity::class.java)
//                startActivity(intent)
//                true
//            }
//            else -> super.onOptionsItemSelected(item)
//        }
//    }

//    private fun viewForecastCityOnMap() {
//        if (args.forecastCity != null) {
//            val geoUri = Uri.parse(getString(
//                R.string.geo_uri,
//                args.forecastCity?.lat ?: 0.0,
//                args.forecastCity?.lon ?: 0.0,
//                11
//            ))
//            val intent = Intent(Intent.ACTION_VIEW, geoUri)
//            try {
//                startActivity(intent)
//            } catch (e: ActivityNotFoundException) {
//                Snackbar.make(
//                    view.findViewById(R.id.coordinator_layout),
//                    R.string.action_map_error,
//                    Snackbar.LENGTH_LONG
//                ).show()
//            }
//        }
//    }
}