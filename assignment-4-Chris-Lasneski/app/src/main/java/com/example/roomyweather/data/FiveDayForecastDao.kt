package com.example.roomyweather.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface FiveDayForecastDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(repo: SavedCity)

    @Delete
    suspend fun delete(repo: SavedCity)

    @Query("SELECT * FROM SavedCity")
    fun getAllRepos(): Flow<List<SavedCity>>

    @Query("SELECT * FROM SavedCity WHERE name = :name LIMIT 1")
    fun getRepoByName(name: String): Flow<SavedCity?>
}