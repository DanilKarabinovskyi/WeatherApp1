package danyil.karabinovskyi.weatherapp.features.main_screen.domain.repository

import danyil.karabinovskyi.weatherapp.features.main_screen.domain.model.MyCity

interface MyCityRepository {
    suspend fun addMyCity(myCity: MyCity)

    fun getMyCity() : List<MyCity>

    suspend fun updateMyCity(myCity: MyCity)

    suspend fun getSpecificCity(cityName: String) : Boolean
}