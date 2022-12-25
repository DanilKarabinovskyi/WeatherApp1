package danyil.karabinovskyi.weatherapp.features.main_screen.domain.usecase.forecast

import danyil.karabinovskyi.weatherapp.features.main_screen.data.repository.ForecastRepositoryImpl
import danyil.karabinovskyi.weatherapp.features.main_screen.domain.model.City
import javax.inject.Inject

class UpdateCityDbUseCase @Inject constructor(private val forecastRepositoryImpl: ForecastRepositoryImpl) {
    suspend fun updateCityDb(city: City) = forecastRepositoryImpl.updateCity(city)
}