package danyil.karabinovskyi.weatherapp.features.main_screen.data.db

import danyil.karabinovskyi.weatherapp.features.main_screen.data.db.room.CityDao
import javax.inject.Inject

class CityLocalDataSource @Inject constructor(private val cityDao: CityDao) {

    fun getCity() = cityDao.getCity()
}