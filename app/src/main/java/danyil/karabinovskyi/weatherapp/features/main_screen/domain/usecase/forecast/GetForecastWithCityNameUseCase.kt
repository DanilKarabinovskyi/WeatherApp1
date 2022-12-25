package danyil.karabinovskyi.weatherapp.features.main_screen.domain.usecase.forecast

import danyil.karabinovskyi.weatherapp.features.main_screen.data.repository.ForecastRepositoryImpl
import javax.inject.Inject

class GetForecastWithCityNameUseCase @Inject constructor(private val forecastRepositoryImpl: ForecastRepositoryImpl) {
    suspend fun getForecast(cityName: String) =
        forecastRepositoryImpl.getForecastDataWithCityName(cityName)
}