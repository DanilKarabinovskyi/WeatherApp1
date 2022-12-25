package danyil.karabinovskyi.weatherapp.features.main_screen.data.db.room

import androidx.room.*
import danyil.karabinovskyi.weatherapp.features.main_screen.data.db.entity.ForecastEntity

@Dao
interface ForecastDao {
    @Insert
    suspend fun addForecastWeather(forecastEntity: ForecastEntity)

    @Query("SELECT * FROM forecast_data")
    fun getForecastWeather(): List<ForecastEntity>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateForecastWeather(forecastEntity: ForecastEntity)
}