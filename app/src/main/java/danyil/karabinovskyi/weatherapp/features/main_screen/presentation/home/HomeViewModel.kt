package danyil.karabinovskyi.weatherapp.features.main_screen.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import danyil.karabinovskyi.weatherapp.core.common.Resource
import danyil.karabinovskyi.weatherapp.features.main_screen.domain.model.City
import danyil.karabinovskyi.weatherapp.features.main_screen.domain.model.Forecast
import danyil.karabinovskyi.weatherapp.features.main_screen.domain.usecase.forecast.*
import danyil.karabinovskyi.weatherapp.features.main_screen.domain.usecase.location.GetLocationUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val addForecastDb: AddForecastToDbUseCase,
    private val addCityDb: AddCityToDbUseCase,
    private val updateCityDbUseCase: UpdateCityDbUseCase,
    private val getForecastDb: GetForecastFromDbUseCase,
    private val updateForecastDb: UpdateForecastDbUseCase,
    private val getForecast: GetForecastUseCase,
    private val getCurrentLocation: GetLocationUseCase,
    private val getForecastWithCityName: GetForecastWithCityNameUseCase,
) : ViewModel() {


    private val _homeState = MutableStateFlow<HomeState>(HomeState.Loading)
    val homeForecastState = _homeState.asStateFlow()

    var searchFieldValue by mutableStateOf("")
        private set

    fun searchCity() {
        _homeState.value = HomeState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                fetchForecastWithCityName(searchFieldValue)
            } catch (e: Exception) {
                _homeState.value = HomeState.Error(e.message)
            }
        }
    }

    private suspend fun fetchForecastWithCityName(cityName: String) {
        when (val result = getForecastWithCityName.getForecast(cityName)) {
            is Resource.Success -> {
                _homeState.value = HomeState.SuccessSearchCity(result.data)
            }
            is Resource.Error -> {
                _homeState.value = HomeState.Error(result.message)
            }
        }
    }

    fun updateSearchField(input: String) {
        searchFieldValue = input
    }
    fun loadLocation() {
        _homeState.value = HomeState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val locationData = getCurrentLocation.getLocation()
                if (locationData != null) {
                    fetchForecast(locationData.latitude, locationData.longitude)
                } else if (isForecastCached()) {
                    getCachedForecast()
                } else {
                    _homeState.value = HomeState.Error("No internet")
                }
            } catch (e: Exception) {
                if (isForecastCached()) {
                    getCachedForecast()
                } else {
                    _homeState.value = HomeState.Error(e.message)
                }
            }
        }
    }

    private suspend fun fetchForecast(latitude: Double, longitude: Double) {
        when (val result = getForecast.getForecast(latitude, longitude)) {
            is Resource.Success -> {
                _homeState.value = HomeState.SuccessOwnCity(result.data)
                if (result.data != null) {
                    if (!isForecastCached()) {
                        cacheForecast(result.data, result.data.cityDtoData)
                    } else {
                        updateCachedForecast(result.data, result.data.cityDtoData)
                    }
                }
            }
            is Resource.Error -> {
                _homeState.value = HomeState.Error(result.message)
            }
        }
    }

    private suspend fun cacheForecast(forecast: Forecast, city: City) {
        addForecastDb.addForecastToDbUseCase(
            forecast,
            forecast.weatherList.size
        )
        addCityDb.addCityDb(city)
    }

    private suspend fun updateCachedForecast(forecast: Forecast, city: City) {
        updateForecastDb.updateForecastDbUseCase(
            forecast,
            forecast.weatherList.size
        )
        updateCityDbUseCase.updateCityDb(city)
    }

    private fun getCachedForecast() {
        _homeState.value =
            HomeState.SuccessOwnCity(getForecastDb.getForecastFromDbUseCase())
    }

    private fun isForecastCached(): Boolean {
        return getForecastDb.getForecastFromDbUseCase() != null
    }
}