package com.facio.apps.ui.addTrip

import com.facio.apps.domain.models.Trips
import com.facio.apps.ui.base.BaseNavigator

interface AddTripNavigator : BaseNavigator {
    fun showPicker()
    fun successAddTrip(trips: Trips)
}