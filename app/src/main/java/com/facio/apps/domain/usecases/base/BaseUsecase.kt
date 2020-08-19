package com.facio.apps.domain.usecases.base

import com.facio.apps.data.remote.BaseRepository
import com.facio.apps.data.remote.UserRepository
import com.facio.apps.domain.mappers.BaseMapper

abstract class BaseUsecase<M : BaseMapper<*, *>?, R :
BaseRepository<*>?>(protected var mapper: M, protected var repository: UserRepository?) {

    fun isErrorCode(statusCode: Int): Boolean {
        if (statusCode > 200) {
            return true
        }
        return false
    }
}