package danyil.karabinovskyi.weatherapp.features.main_screen.data.db.room

import androidx.room.Database
import androidx.room.RoomDatabase
import danyil.karabinovskyi.weatherapp.features.main_screen.data.db.entity.CityEntity
import danyil.karabinovskyi.weatherapp.features.main_screen.data.db.entity.ForecastEntity

@Database(entities = [CityEntity::class, ForecastEntity::class], version = 1)
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun cityDao(): CityDao

    abstract fun forecastWeatherDao(): ForecastDao
}