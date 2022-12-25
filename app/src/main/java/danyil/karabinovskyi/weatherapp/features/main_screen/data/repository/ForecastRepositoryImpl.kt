package danyil.karabinovskyi.weatherapp.features.main_screen.data.repository

import danyil.karabinovskyi.weatherapp.core.common.Resource
import danyil.karabinovskyi.weatherapp.features.main_screen.data.api.ForecastRemoteDataSource
import danyil.karabinovskyi.weatherapp.features.main_screen.data.db.CityLocalDataSource
import danyil.karabinovskyi.weatherapp.features.main_screen.data.db.ForecastLocalDataSource
import danyil.karabinovskyi.weatherapp.features.main_screen.data.mapper.CityEntityMapper
import danyil.karabinovskyi.weatherapp.features.main_screen.data.mapper.ForecastDtoMapper
import danyil.karabinovskyi.weatherapp.features.main_screen.data.mapper.ForecastEntityMapper
import danyil.karabinovskyi.weatherapp.features.main_screen.domain.model.City
import danyil.karabinovskyi.weatherapp.features.main_screen.domain.model.Forecast
import danyil.karabinovskyi.weatherapp.features.main_screen.domain.repository.ForecastRepository
import javax.inject.Inject

class ForecastRepositoryImpl @Inject constructor(
    private val forecastRemoteDataSource: ForecastRemoteDataSource,
    private val forecastLocalDataSource: ForecastLocalDataSource,
    private val cityLocalDataSource: CityLocalDataSource,
    private val dtoMapper: ForecastDtoMapper,
    private val entityMapper: ForecastEntityMapper,
    private val cityEntityMapper: CityEntityMapper
) : ForecastRepository {
    override suspend fun getForecastData(
        latitude: Double,
        longitude: Double
    ): Resource<Forecast> {
        return try {
            Resource.Success(
                dtoMapper.mapFromEntity(
                    forecastRemoteDataSource.getForecastData(
                        latitude,
                        longitude
                    )
                )
            )
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }

    override suspend fun getForecastDataWithCityName(cityName: String): Resource<Forecast> {
        return try {
            Resource.Success(
                dtoMapper.mapFromEntity(
                    forecastRemoteDataSource.getForecastDataWithCityName(cityName)
                )
            )
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }

    override suspend fun addForecastWeather(forecast: Forecast) {
        forecastLocalDataSource.addForecastWeather(
            entityMapper.entityFromModel(forecast)
        )
    }

    override suspend fun addCity(city: City) {
        forecastLocalDataSource.addCity(
            cityEntityMapper.entityFromModel(city)
        )
    }

    override fun getForecastWeather(): Forecast? {
        return if (forecastLocalDataSource.getForecastWeather().isNullOrEmpty()) {
            null
        } else {
            entityMapper.mapFromEntity(
                forecastLocalDataSource.getForecastWeather(),
                cityLocalDataSource.getCity()
            )
        }
    }

    override fun getCity(): City {
        return cityEntityMapper.mapFromEntity(forecastLocalDataSource.getCity())
    }

    override suspend fun updateForecastWeather(forecast: Forecast) {
        forecastLocalDataSource.updateForecastWeather(
            entityMapper.entityFromModel(forecast)
        )
    }

    override suspend fun updateCity(city: City) {
        forecastLocalDataSource.updateCity(
            cityEntityMapper.entityFromModel(city)
        )
    }
}