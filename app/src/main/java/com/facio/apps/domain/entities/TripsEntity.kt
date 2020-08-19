package com.facio.apps.domain.entities

import com.google.gson.annotations.SerializedName


//{
//    "_id": "5f3ba99a9a729803e8b2cd56",
//    "start_station": "Jakarta",
//    "end_station": "Medan",
//    "eta": "2020-10-10 00:00:00",
//    "status": "Pending",
//    "driver_name": "John Wick",
//    "created_at": "2020-08-18 17:12:00"
//}
data class TripsEntity(
        @SerializedName("_id") val _id: String? = null,
        @SerializedName("start_station") val startStation: String? = null,
        @SerializedName("end_station") val endStation: String? = null,
        @SerializedName("eta") val eta: String? = null,
        @SerializedName("status") val status: String? = null,
        @SerializedName("driver_name") val driverName: String? = null,
        @SerializedName("created_at") val createdAt: String? = null
) : BaseObjectEntity()