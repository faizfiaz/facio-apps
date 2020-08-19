package com.facio.apps.data.remote

import com.facio.apps.domain.entities.TripsEntity
import com.facio.apps.domain.entities.requests.AddTripRequest
import com.facio.apps.domain.entities.requests.LoginRequest
import com.facio.apps.domain.entities.response.UserResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Url

/**
 * API Backend Service
 */
interface RemoteAPI {
    /******************************* USER **************************/
    @POST("api/login")
    fun login(@Body loginRequest: LoginRequest): Single<UserResponse?>

    @GET
    fun trips(@Url url: String): Single<List<TripsEntity>?>

    @POST
    fun trips(@Url url: String, @Body addTripRequest: AddTripRequest): Single<TripsEntity?>

}