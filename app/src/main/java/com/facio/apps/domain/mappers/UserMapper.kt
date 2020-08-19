package com.facio.apps.domain.mappers

import com.facio.apps.domain.entities.TripsEntity
import com.facio.apps.domain.exceptions.MapperException
import com.facio.apps.domain.models.Trips

class UserMapper : BaseMapper<TripsEntity?, Trips?>() {
    override fun createObject(): Trips? {
        return Trips()
    }

    override fun createEntity(): TripsEntity? {
        return TripsEntity()
    }

    @Throws(MapperException::class)
    override fun defineObject(`object`: Trips?): Trips? {
        `object`?._id = baseEntity?._id
        `object`?.startStation = baseEntity?.startStation
        `object`?.endStation = baseEntity?.endStation
        `object`?.status = baseEntity?.status
        `object`?.driverName = baseEntity?.driverName
        return `object`
    }

    @Throws(MapperException::class)
    override fun defineEntity(entity: TripsEntity?): TripsEntity? {
        return entity
    }
}