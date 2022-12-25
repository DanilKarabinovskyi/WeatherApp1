package danyil.karabinovskyi.weatherapp.features.main_screen.domain.mapper

interface Mapper<Entity, Model> {

    fun mapFromEntity(entity: Entity) : Model

    fun entityFromModel(model: Model) : Entity
}