package danyil.karabinovskyi.weatherapp.features.main_screen.data.api.entity

import com.google.gson.annotations.SerializedName

data class SysDto(
    @SerializedName("sunrise") val sunrise: Long,
    @SerializedName("sunset") val sunset: Long,
)