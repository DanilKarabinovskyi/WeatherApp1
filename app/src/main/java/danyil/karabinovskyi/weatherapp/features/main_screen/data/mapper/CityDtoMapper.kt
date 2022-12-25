package danyil.karabinovskyi.weatherapp.features.main_screen.data.mapper

import danyil.karabinovskyi.weatherapp.features.main_screen.data.api.entity.CityDto
import danyil.karabinovskyi.weatherapp.features.main_screen.data.api.entity.CoordDto
import danyil.karabinovskyi.weatherapp.features.main_screen.domain.mapper.Mapper
import danyil.karabinovskyi.weatherapp.features.main_screen.domain.model.City
import danyil.karabinovskyi.weatherapp.features.main_screen.domain.model.Coord
import javax.inject.Inject

class CityDtoMapper @Inject constructor() : Mapper<CityDto, City> {
    override fun mapFromEntity(entity: CityDto): City {
        return City(
            entity.country,
            entity.timezone,
            entity.sunrise,
            entity.sunset,
            entity.cityName,
            Coord(
                entity.coordinate.latitude,
                entity.coordinate.longitude
            )
        )
    }

    override fun entityFromModel(model: City): CityDto {
        return CityDto(
            model.country,
            model.timezone,
            model.sunrise,
            model.sunset,
            model.cityName,
            CoordDto(
                model.coordinate.latitude,
                model.coordinate.longitude
            )
        )
    }

}