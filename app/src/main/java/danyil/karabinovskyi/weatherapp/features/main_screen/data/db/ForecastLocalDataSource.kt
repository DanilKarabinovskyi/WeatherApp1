package danyil.karabinovskyi.weatherapp.features.main_screen.data.db

import danyil.karabinovskyi.weatherapp.features.main_screen.data.db.entity.CityEntity
import danyil.karabinovskyi.weatherapp.features.main_screen.data.db.entity.ForecastEntity
import danyil.karabinovskyi.weatherapp.features.main_screen.data.db.room.CityDao
import danyil.karabinovskyi.weatherapp.features.main_screen.data.db.room.ForecastDao
import javax.inject.Inject

class ForecastLocalDataSource @Inject constructor(
    private val forecastDao: ForecastDao,
    private val cityDao: CityDao
) {

    suspend fun addForecastWeather(forecastEntity: ForecastEntity) =
        forecastDao.addForecastWeather(forecastEntity)

    suspend fun addCity(cityEntity: CityEntity) =
        cityDao.addCity(cityEntity)

    fun getForecastWeather() = forecastDao.getForecastWeather()
    fun getCity() = cityDao.getCity()

    suspend fun updateForecastWeather(forecastEntity: ForecastEntity) =
        forecastDao.updateForecastWeather(forecastEntity)

    suspend fun updateCity(cityEntity: CityEntity) =
        cityDao.updateCity(cityEntity)
}