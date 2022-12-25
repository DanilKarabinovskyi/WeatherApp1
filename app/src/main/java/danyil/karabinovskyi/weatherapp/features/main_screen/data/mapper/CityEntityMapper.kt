package danyil.karabinovskyi.weatherapp.features.main_screen.data.mapper

import danyil.karabinovskyi.weatherapp.features.main_screen.data.db.entity.CityEntity
import danyil.karabinovskyi.weatherapp.features.main_screen.domain.mapper.Mapper
import danyil.karabinovskyi.weatherapp.features.main_screen.domain.model.City
import danyil.karabinovskyi.weatherapp.features.main_screen.domain.model.Coord
import javax.inject.Inject

class CityEntityMapper @Inject constructor() : Mapper<CityEntity, City> {
    override fun mapFromEntity(entity: CityEntity): City {
        return City(
            entity.country,
            entity.timezone,
            entity.sunrise,
            entity.sunset,
            entity.cityName,
            Coord(
                entity.latitude,
                entity.longitude
            )
        )
    }

    override fun entityFromModel(model: City): CityEntity {
        return CityEntity(
            id = 1,
            country = model.country,
            timezone = model.timezone,
            sunrise = model.sunrise,
            sunset = model.sunset,
            cityName = model.cityName,
            latitude = model.coordinate.latitude,
            longitude = model.coordinate.longitude
        )
    }
}