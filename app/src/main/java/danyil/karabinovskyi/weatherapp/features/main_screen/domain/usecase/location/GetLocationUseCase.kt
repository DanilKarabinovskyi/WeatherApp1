package danyil.karabinovskyi.weatherapp.features.main_screen.domain.usecase.location

import danyil.karabinovskyi.weatherapp.core.location.LocationTracker
import javax.inject.Inject

class GetLocationUseCase @Inject constructor(private val defaultLocationTracker: LocationTracker) {
    suspend fun getLocation() = defaultLocationTracker.getCurrentLocation()
}