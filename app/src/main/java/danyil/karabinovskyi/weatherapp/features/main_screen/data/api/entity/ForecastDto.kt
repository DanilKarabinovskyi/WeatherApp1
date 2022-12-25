package danyil.karabinovskyi.weatherapp.features.main_screen.data.api.entity

import com.google.gson.annotations.SerializedName
import danyil.karabinovskyi.weatherapp.features.main_screen.data.api.entity.CityDto

data class ForecastDto(
    @SerializedName("list") val weatherList: List<ForecastWeatherDto>,
    @SerializedName("city") val cityDtoData: CityDto
)
