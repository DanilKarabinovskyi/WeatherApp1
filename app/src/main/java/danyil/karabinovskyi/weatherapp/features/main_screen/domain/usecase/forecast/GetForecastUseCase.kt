package danyil.karabinovskyi.weatherapp.features.main_screen.domain.usecase.forecast

import danyil.karabinovskyi.weatherapp.features.main_screen.data.repository.ForecastRepositoryImpl
import danyil.karabinovskyi.weatherapp.core.common.Resource
import danyil.karabinovskyi.weatherapp.features.main_screen.domain.model.Forecast
import javax.inject.Inject

class GetForecastUseCase @Inject constructor(private val forecastRepositoryImpl: ForecastRepositoryImpl) {
    suspend fun getForecast(latitude: Double, longitude: Double): Resource<Forecast> =
        forecastRepositoryImpl.getForecastData(latitude, longitude)
}