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
    fun testGetUser() {
        runBlocking {
            var data = userUsecases!!.getUser("a", 1).test()
            data.assertComplete()
            Assert.assertTrue(data.values() is List<*>)
        }
    }

    @Test
    fun testGetUserInvalid() {
        runBlocking {
            var data = try {
                userUsecases!!.getUser("", 1).test()
            } catch (e: Exception) {
                Assert.assertTrue(e.message.equals("HTTP 422 Unprocessable Entity"))
            }
        }
    }

    @Test
    fun testGetUserMaxReach() {
        runBlocking {
            var data = try {
                userUsecases!!.getUser("a", 1).test()
            } catch (e: Exception) {
                Assert.assertTrue(e.message.equals("HTTP 403 rate limit exceeded"))
            }
            (data as TestObserver<*>).assertComplete()
        }
    }

    @Test
    fun checkResponseError() {
        var responseError = UserResponse()
        responseError.message = "error"
        responseError.errors = arrayListOf(ErrorEntity("test", "test", "422"))
        var data = userUsecases!!.checkResponse(responseError)
        Assert.assertTrue(data.blockingGet() is String)

    }
}