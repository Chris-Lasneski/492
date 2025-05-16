package com.example.roomyweather.data

class SavedCityRepository(private val dao: FiveDayForecastDao) {
    suspend fun insertBookMarkedLocation(repo: SavedCity) = dao.insert(repo)
    suspend fun deleteBookMarkedLocation(repo: SavedCity) = dao.delete(repo)

    fun getAllBookmarkedLocations() = dao.getAllRepos()

    fun getBookmarkedLocation(name: String) = dao.getRepoByName(name)
}