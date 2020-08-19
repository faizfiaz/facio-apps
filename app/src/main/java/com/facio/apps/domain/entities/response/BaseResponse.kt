package com.facio.apps.domain.entities.response

import com.facio.apps.domain.entities.BaseObjectEntity

open class BaseResponse<E> : BaseObjectEntity() {
    var data: E? = null
    var error: String? = null

}


