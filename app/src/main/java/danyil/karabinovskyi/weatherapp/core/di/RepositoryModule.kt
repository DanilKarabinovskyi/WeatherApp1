package danyil.karabinovskyi.weatherapp.core.di

import danyil.karabinovskyi.weatherapp.features.main_screen.data.repository.ForecastRepositoryImpl
import danyil.karabinovskyi.weatherapp.features.main_screen.domain.repository.ForecastRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindForecastRepository(forecastRepositoryImpl: ForecastRepositoryImpl): ForecastRepository
}