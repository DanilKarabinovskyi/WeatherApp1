package danyil.karabinovskyi.weatherapp.features.main_screen.data.api

import danyil.karabinovskyi.weatherapp.features.main_screen.data.api.weatherapi.WeatherApi
import javax.inject.Inject

class ForecastRemoteDataSource @Inject constructor(private val api: WeatherApi) {
    suspend fun getForecastData(latitude: Double, longitude: Double) =
        api.getForecastData(latitude, longitude)

    suspend fun getForecastDataWithCityName(cityName: String) =
        api.getForecastDataWithCityName(cityName)
}