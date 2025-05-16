package com.example.roomyweather.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SavedCity(
    @PrimaryKey val name: String,
    val lastViewed: Long = System.currentTimeMillis())
