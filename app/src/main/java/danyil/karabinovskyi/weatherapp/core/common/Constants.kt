package danyil.karabinovskyi.weatherapp.core.common

object NetworkService {
    const val BASE_URL: String = "https://api.openweathermap.org"
    const val API_KEY: String = "1f754c06fca3ee06f292871b1724ae57"
    const val UNITS: String = "metric"
    const val FORECAST_END_POINT = "/data/2.5/forecast"
}

object Database {
    const val forecast_table = "forecast_data"
    const val database_name = "weather_data.db"
    const val city_table = "city_data"
}


object AppStrings {

    const val hourly_forecast = "Hourly Forecast"
    const val daily_forecast = "Daily Forecast"

    const val temp = "🌡 TEMP"
    const val feels_like = "🌡 FEELS LIKE"
    const val cloudiness = "☁ CLOUDINESS"
    const val humidity = "💧 HUMIDITY"
    const val sunrise = "🌇 SUNRISE"
    const val sunset = "🌆 SUNSET"
    const val wind = "🌬 WIND"
    const val metric = "KM"
    const val pressure = "⏲ PRESSURE"
    const val degree = "°"

}

object WeatherConditions {
    const val CLEAR_SKY = "clear sky"
    const val FEW_CLOUDS = "few clouds"
    const val SCATTERED_CLOUDS = "scattered clouds"
    const val BROKEN_CLOUDS = "broken clouds"
    const val SHOWER_RAIN = "shower rain"
    const val RAIN = "rain"
    const val THUNDERSTORM = "thunderstorm"
    const val SNOW = "snow"
    const val MIST = "mist"
}

object MainWeatherConditions {
    const val CLOUDS = "Clouds"
    const val SNOW = "Snow"
    const val RAIN = "Rain"
    const val THUNDERSTORM = "Thunderstorm"
    const val CLEAR = "Clear"
}