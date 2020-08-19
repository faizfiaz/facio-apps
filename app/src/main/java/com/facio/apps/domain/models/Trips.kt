package com.facio.apps.domain.models

import android.os.Parcel
import android.os.Parcelable

data class Trips(
        var _id: String? = null,
        var startStation: String? = null,
        var endStation: String? = null,
        var eta: String? = null,
        var status: String? = null,
        var driverName: String? = null
) : BaseObject(), Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(_id)
        parcel.writeString(startStation)
        parcel.writeString(endStation)
        parcel.writeString(eta)
        parcel.writeString(status)
        parcel.writeString(driverName)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Trips> {
        override fun createFromParcel(parcel: Parcel): Trips {
            return Trips(parcel)
        }

        override fun newArray(size: Int): Array<Trips?> {
            return arrayOfNulls(size)
        }
    }
}