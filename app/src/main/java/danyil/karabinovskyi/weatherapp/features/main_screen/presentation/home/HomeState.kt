package danyil.karabinovskyi.weatherapp.features.main_screen.presentation.home

import danyil.karabinovskyi.weatherapp.features.main_screen.domain.model.Forecast

sealed interface HomeState {
    data class SuccessOwnCity(val forecast: Forecast?): HomeState
    data class SuccessSearchCity(val forecast: Forecast?): HomeState
    data class Error(val errorMessage: String?): HomeState
    object Loading: HomeState
}