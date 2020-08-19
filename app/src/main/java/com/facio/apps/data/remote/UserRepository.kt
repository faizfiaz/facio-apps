package com.facio.apps.data.remote

import com.facio.apps.domain.entities.TripsEntity
import com.facio.apps.domain.entities.requests.AddTripRequest
import com.facio.apps.domain.entities.requests.LoginRequest
import com.facio.apps.domain.entities.response.UserResponse
import io.reactivex.Single

class UserRepository private constructor() : BaseRepository<UserResponse?>() {

    fun login(loginRequest: LoginRequest): Single<UserResponse?>? {
        return remoteAPI.login(loginRequest)
    }

    fun getTrips(): Single<List<TripsEntity>?>? {
        return remoteAPI.trips("https://crudcrud.com/api/87bbbf684c7d452c9eb9da18c4090420/trips")
    }

    fun addTrips(addTripRequest: AddTripRequest): Single<TripsEntity?> {
        return remoteAPI.trips("https://crudcrud.com/api/87bbbf684c7d452c9eb9da18c4090420/trips",
                addTripRequest)
    }

    companion object {
        @JvmStatic
        var instance: UserRepository? = null
            get() {
                if (field == null) {
                    field = UserRepository()
                }
                return field
            }
            private set
    }

    override fun get(): Single<List<UserResponse?>?>? {
        return null
    }

    override fun getById(id: Int): Single<List<UserResponse?>?>? {
        return null
    }

}