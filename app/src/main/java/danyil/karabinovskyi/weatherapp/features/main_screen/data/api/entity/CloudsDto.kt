package danyil.karabinovskyi.weatherapp.features.main_screen.data.api.entity

import com.google.gson.annotations.SerializedName

data class CloudsDto(
    @SerializedName("all") val cloudiness: Int
)
