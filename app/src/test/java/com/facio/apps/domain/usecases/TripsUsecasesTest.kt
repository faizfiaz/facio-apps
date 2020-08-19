package com.facio.apps.domain.usecases

import com.facio.apps.data.remote.UserRepository
import com.facio.apps.domain.entities.ErrorEntity
import com.facio.apps.domain.entities.response.UserResponse
import com.facio.apps.domain.mappers.UserMapper
import com.facio.apps.domain.usecases.user.UserUsecases
import io.reactivex.observers.TestObserver
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.junit.MockitoJUnit

class TripsUsecasesTest {

    @Rule
    @JvmField
    var rule = MockitoJUnit.rule()
    var userUsecases: UserUsecases? = null

    @InjectMocks
    var userMapper: UserMapper? = null

    @InjectMocks
    var userRepository: UserRepository? = null

    @Before
    fun setUp() {
        userUsecases = UserUsecases(userMapper!!, userRepository)
    }

    @Test
    fun testGetTrips() {
        runBlocking {
            var data = userUsecases!!.getTrips().test()
            data.assertComplete()
            Assert.assertTrue(data.values() is List<*>)
        }
    }
}