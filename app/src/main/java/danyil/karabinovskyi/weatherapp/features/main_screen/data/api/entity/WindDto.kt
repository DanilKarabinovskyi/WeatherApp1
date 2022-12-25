package danyil.karabinovskyi.weatherapp.features.main_screen.data.api.entity

import com.google.gson.annotations.SerializedName

data class WindDto(
    @SerializedName("speed") val speed: Double,
)
