package com.facio.apps.domain.entities

data class ErrorEntity(
        var resource: String? = null,
        var field: String? = null,
        var code: String? = null
)