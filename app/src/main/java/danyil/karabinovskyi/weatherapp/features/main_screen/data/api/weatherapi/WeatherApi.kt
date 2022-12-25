package danyil.karabinovskyi.weatherapp.features.main_screen.data.api.weatherapi

import danyil.karabinovskyi.weatherapp.core.common.NetworkService
import danyil.karabinovskyi.weatherapp.features.main_screen.data.api.entity.ForecastDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET(NetworkService.FORECAST_END_POINT)
    suspend fun getForecastData(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") apiKey: String = NetworkService.API_KEY,
        @Query("units") units: String = NetworkService.UNITS,
    ): ForecastDto

    @GET(NetworkService.FORECAST_END_POINT)
    suspend fun getForecastDataWithCityName(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String = NetworkService.API_KEY,
        @Query("units") units: String = NetworkService.UNITS,
    ): ForecastDto
}