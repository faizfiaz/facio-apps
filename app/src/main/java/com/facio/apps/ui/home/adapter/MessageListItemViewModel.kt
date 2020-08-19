package com.facio.apps.ui.home.adapter


import androidx.databinding.ObservableField
import com.facio.apps.databinding.ItemTripBinding
import com.facio.apps.domain.models.Trips
import java.util.*

class UserListItemViewModel(itemData: Trips?, var actionDetail: (Trips, ItemTripBinding) -> Unit,
                            var binding: ItemTripBinding) : Observable() {

    val name = ObservableField<String>()
    val station = ObservableField<String>()
    val status = ObservableField<String>()

    var data: Trips? = itemData

    init {
        name.set(data?.driverName)
        station.set(String.format("%s - %s", data?.startStation, data?.endStation))
        status.set(data?.status)
    }

    fun goDetail() {

    }

}