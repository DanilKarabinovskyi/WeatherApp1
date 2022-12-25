package danyil.karabinovskyi.weatherapp.features.main_screen.domain.repository

import danyil.karabinovskyi.weatherapp.core.common.Resource
import danyil.karabinovskyi.weatherapp.features.main_screen.domain.model.City
import danyil.karabinovskyi.weatherapp.features.main_screen.domain.model.Forecast

interface ForecastRepository {
    suspend fun getForecastData(latitude: Double, longitude: Double, ): Resource<Forecast>

    suspend fun getForecastDataWithCityName(cityName: String): Resource<Forecast>

    suspend fun addForecastWeather(forecast: Forecast)

    suspend fun addCity(city: City)

    fun getForecastWeather() : Forecast?

    fun getCity() : City

    suspend fun updateForecastWeather(forecast: Forecast)

    suspend fun updateCity(city: City)
}