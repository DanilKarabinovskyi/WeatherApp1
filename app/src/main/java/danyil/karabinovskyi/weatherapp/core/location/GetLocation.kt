package danyil.karabinovskyi.weatherapp.core.location

import android.location.Location


interface GetLocation {
    suspend fun getCurrentLocation(): Location?
}