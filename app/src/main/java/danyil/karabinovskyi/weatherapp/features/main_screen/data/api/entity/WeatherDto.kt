package danyil.karabinovskyi.weatherapp.features.main_screen.data.api.entity

import com.google.gson.annotations.SerializedName

data class WeatherDto(
    @SerializedName("main") val mainDescription: String,
    @SerializedName("description") val description: String
)
