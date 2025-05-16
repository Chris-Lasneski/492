package com.example.roomyweather.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.roomyweather.data.*
import kotlinx.coroutines.launch

class BookmarkedForecastsViewModel(application: Application): AndroidViewModel(application) {
    private val repository = SavedCityRepository(
        AppDatabase.getInstance(application).fiveDayForecastDao())

    val bookmarkedForecasts = repository.getAllBookmarkedLocations().asLiveData()

    fun addBookmarkedLocation(repo: SavedCity) =
        viewModelScope.launch {
            repository.insertBookMarkedLocation(repo)
        }

    fun removeBookmarkedLocation(repo: SavedCity) =
        viewModelScope.launch {
            repository.deleteBookMarkedLocation(repo)
        }

    fun getBookmarkedLocationByName(name: String) =
        repository.getBookmarkedLocation(name).asLiveData()
}