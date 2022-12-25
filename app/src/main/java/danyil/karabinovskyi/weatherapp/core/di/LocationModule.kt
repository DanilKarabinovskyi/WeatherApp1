package danyil.karabinovskyi.weatherapp.core.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import danyil.karabinovskyi.weatherapp.core.location.GetLocation
import danyil.karabinovskyi.weatherapp.core.location.LocationTracker
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocationModule {

    @Binds
    @Singleton
    abstract fun bindLocationTracker(defaultLocationTracker: LocationTracker): GetLocation
}