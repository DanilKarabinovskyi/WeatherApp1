package danyil.karabinovskyi.weatherapp.features.main_screen.data.db.room

import androidx.room.*
import danyil.karabinovskyi.weatherapp.features.main_screen.data.db.entity.CityEntity

@Dao
interface CityDao {
    @Insert
    suspend fun addCity(cityEntity: CityEntity)

    @Query("SELECT * FROM city_data")
    fun getCity(): CityEntity

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateCity(cityEntity: CityEntity)
}