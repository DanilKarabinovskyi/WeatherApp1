package danyil.karabinovskyi.weatherapp.features.main_screen.domain.model

data class Forecast(
    val weatherList: List<ForecastWeather>,
    val cityDtoData: City
)
