package com.facio.apps.data.remote

import com.facio.apps.domain.entities.TripsEntity
import com.facio.apps.domain.entities.response.UserResponse
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit

class TripsRepositoryTest {
    @Rule
    @JvmField
    var rule = MockitoJUnit.rule()

    @Mock
    var remoteAPI: RemoteAPI? = null

    @InjectMocks
    var repository: UserRepository? = null

    @Before
    @Throws(Exception::class)
    fun setUp() {

    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
    }

    @Test
    fun testGetSuccessful() {
        runBlocking {
            val observer = repository!!.getTrips()!!.test()
            observer.awaitTerminalEvent()
            observer.assertNoErrors().assertValue { r: List<TripsEntity> -> r.isNotEmpty() }
        }
    }

}