package com.facio.apps.domain.usecases.user

import com.facio.apps.data.local.PreferencesManager
import com.facio.apps.data.remote.UserRepository
import com.facio.apps.domain.entities.TripsEntity
import com.facio.apps.domain.entities.requests.AddTripRequest
import com.facio.apps.domain.entities.requests.LoginRequest
import com.facio.apps.domain.entities.response.UserResponse
import com.facio.apps.domain.mappers.UserMapper
import com.facio.apps.utils.FormatterDate
import io.reactivex.Single
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import java.util.*

open class UserUsecases(mapper: UserMapper, repository: UserRepository?) : IUserUsecases(mapper, repository) {

    var preferencesManager: PreferencesManager? = PreferencesManager.instance

    override suspend fun login(identifier: String, password: String): Single<Any?> = withContext(Dispatchers.IO) {
        var loginRequest = LoginRequest(identifier, password)
        val response = async { repository?.login(loginRequest) }
        try {
            checkResponse(response.await()?.blockingGet())
        } catch (e: Exception) {
            throw java.lang.Exception(e.message)
        }
    }

    override fun checkToken(): Boolean {
        return !preferencesManager?.prefToken.isNullOrBlank()
    }

    override suspend fun getTrips(): Single<Any?> = withContext(Dispatchers.IO) {
        val response = async { repository?.getTrips() }
        try {
            checkTripsResponse(response.await()?.blockingGet())
        } catch (e: Exception) {
            throw java.lang.Exception(e.message)
        }

    }

    override suspend fun addTrip(fromStation: String?, toStation: String?, driverName: String?,
                                 status: String?, eta: String?): Single<Any?> = withContext(Dispatchers.IO) {
        var addTripRequest = AddTripRequest(fromStation, toStation, driverName, status,
                eta, FormatterDate.formatDateTime(Date().time))
        val response = async { repository?.addTrips(addTripRequest) }
        try {
            checkAddtripsResponse(response.await()?.blockingGet())
        } catch (e: Exception) {
            throw java.lang.Exception(e.message)
        }

    }

    fun checkAddtripsResponse(response: TripsEntity?): Single<Any?> {
        if (response == null) {
            return Single.just(false)
        }
        var trips = mapper.convertToObject(response)
        return Single.just(trips)
    }

    private fun checkTripsResponse(response: List<TripsEntity>?): Single<Any?> {
        if (response == null) {
            return Single.just("Something wrong")
        }
        var list = mapper.convertToObjectList(response)
        return Single.just(list)
    }

    fun checkResponse(response: UserResponse?): Single<Any?> {
        if (response?.error != null) {
            return Single.just(response.message)
        }
        preferencesManager?.prefToken = response?.token
        return Single.just(true)
    }

}