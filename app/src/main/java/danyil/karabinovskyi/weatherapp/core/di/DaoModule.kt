package danyil.karabinovskyi.weatherapp.core.di

import android.content.Context
import androidx.room.Room
import danyil.karabinovskyi.weatherapp.features.main_screen.data.db.room.CityDao
import danyil.karabinovskyi.weatherapp.features.main_screen.data.db.room.ForecastDao
import danyil.karabinovskyi.weatherapp.features.main_screen.data.db.room.WeatherDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import danyil.karabinovskyi.weatherapp.core.common.Database
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {

    @Provides
    @Singleton
    fun bindCurrentWeatherDao(weatherDatabase: WeatherDatabase): CityDao =
        weatherDatabase.cityDao()

    @Provides
    @Singleton
    fun bindForecastDao(weatherDatabase: WeatherDatabase): ForecastDao =
        weatherDatabase.forecastWeatherDao()

    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context: Context): WeatherDatabase {
        return Room.databaseBuilder(context, WeatherDatabase::class.java, Database.database_name)
            .allowMainThreadQueries()
            .build()
    }
}