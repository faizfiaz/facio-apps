package com.facio.apps.domain.usecases.user

import com.facio.apps.data.remote.UserRepository
import com.facio.apps.domain.exceptions.MapperException
import com.facio.apps.domain.mappers.UserMapper
import com.facio.apps.domain.usecases.base.BaseUsecase
import io.reactivex.Single

abstract class IUserUsecases(mapper: UserMapper, userRepository: UserRepository?) :
        BaseUsecase<UserMapper, UserRepository>(mapper, userRepository) {

    @Throws(MapperException::class)
    abstract suspend fun login(identifier: String, password: String): Single<Any?>

    abstract fun checkToken(): Boolean

    @Throws(MapperException::class)
    abstract suspend fun getTrips(): Single<Any?>
    abstract suspend fun addTrip(fromStation: String?, toStation: String?,
                                 driverName: String?, status: String?, eta: String?): Single<Any?>

}