package danyil.karabinovskyi.weatherapp.features.main_screen.domain.usecase.forecast

import danyil.karabinovskyi.weatherapp.features.main_screen.data.repository.ForecastRepositoryImpl
import danyil.karabinovskyi.weatherapp.features.main_screen.domain.model.Forecast
import danyil.karabinovskyi.weatherapp.features.main_screen.domain.model.ForecastWeather
import javax.inject.Inject

class UpdateForecastDbUseCase @Inject constructor(private val forecastRepositoryImpl: ForecastRepositoryImpl) {
    suspend fun updateForecastDbUseCase(forecast: Forecast, forecastSize: Int) {
        for (i in 1..forecastSize) {
            forecastRepositoryImpl.updateForecastWeather(
                Forecast(
                    listOf(
                        ForecastWeather(
                            id = i,
                            forecast.weatherList[i - 1].weatherData,
                            forecast.weatherList[i - 1].weatherStatus,
                            forecast.weatherList[i - 1].wind,
                            forecast.weatherList[i - 1].date,
                            forecast.weatherList[i - 1].cloudiness
                        )
                    ),
                    forecast.cityDtoData
                )
            )
        }
    }
}