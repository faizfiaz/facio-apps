package com.facio.apps.domain.mappers

import com.facio.apps.domain.entities.TripsEntity
import com.facio.apps.domain.exceptions.MapperException
import com.facio.apps.domain.models.Trips
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.junit.MockitoJUnit

class TripsMapperTest {
    @Rule
    @JvmField
    var rule = MockitoJUnit.rule()

    @InjectMocks
    var userMapper: UserMapper? = null
    private var trips: Trips? = null
    private var tripsEntity: TripsEntity? = null

    @Before
    fun setUp() {
        trips = Trips()
        trips!!.id = 1
        tripsEntity = TripsEntity()
        tripsEntity!!.id = 1
    }

    @Test
    fun createObjectValid() {
        Assert.assertNotNull(userMapper!!.createObject())
    }

    @Test
    fun createEntityValid() {
        Assert.assertNotNull(userMapper!!.createEntity())
    }

    @Test
    @Throws(MapperException::class)
    fun defineObjectValid() {
        Assert.assertSame(trips, userMapper!!.defineObject(trips))
    }

    @Test
    @Throws(MapperException::class)
    fun defineEntityValid() {
        Assert.assertSame(tripsEntity, userMapper!!.defineEntity(tripsEntity))
    }
}