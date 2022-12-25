package danyil.karabinovskyi.weatherapp.features.main_screen.data.api.entity

import com.google.gson.annotations.SerializedName

data class CoordDto(
    @SerializedName("lat") val latitude: Double,
    @SerializedName("lon") val longitude: Double
)
