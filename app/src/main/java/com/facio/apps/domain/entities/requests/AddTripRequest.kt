package com.facio.apps.domain.entities.requests

import com.google.gson.annotations.SerializedName

data class AddTripRequest(
        @SerializedName("start_station") var startStation: String? = null,
        @SerializedName("end_station") var endStation: String? = null,
        @SerializedName("driver_name") var driverName: String? = null,
        @SerializedName("status") var status: String? = null,
        @SerializedName("eta") var eta: String? = null,
        @SerializedName("created_at") var createdAt: String? = null
)