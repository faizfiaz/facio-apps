package com.facio.apps.ui.addTrip

import android.text.Editable
import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.facio.apps.domain.models.Trips
import com.facio.apps.domain.usecases.user.IUserUsecases
import com.facio.apps.ui.base.BaseViewModel
import com.facio.apps.utils.SchedulerProvider
import kotlinx.coroutines.launch

class AddTripViewModel(baseUsecase: IUserUsecases, schedulerProvider: SchedulerProvider)
    : BaseViewModel<IUserUsecases, AddTripNavigator>(baseUsecase, schedulerProvider) {

    var fromStation = ObservableField<String>()
    var toStation = ObservableField<String>()
    var driverName = ObservableField<String>()
    var eta = ObservableField<String>()
    var status = ObservableField<String>()

    override fun defineLayout() {

    }

    override fun onSuccess(o: Any?) {

    }

    fun afterFromStationChanged(s: Editable) {
        fromStation.set(s.toString())
    }

    fun afterToStationChanged(s: Editable) {
        toStation.set(s.toString())
    }

    fun afterDriverNameChanged(s: Editable) {
        driverName.set(s.toString())
    }

    fun createTrip() {

        viewModelScope.launch {
            isLoading(true)
            try {
                val responseApi = baseUsecase.addTrip(
                        fromStation.get(),
                        toStation.get(),
                        driverName.get(),
                        status.get(),
                        eta.get()
                )
                checkResponse(responseApi.blockingGet())
            } catch (e: Exception) {
                onError(e)
            }
        }

    }

    private fun checkResponse(responseApi: Any?) {
        isLoading(false)
        if (responseApi is Boolean) {
            navigator?.handleError(Throwable("Something wrong"))
        } else {
            navigator?.successAddTrip(responseApi as Trips)
        }
    }

    fun openDatePicker() {
        navigator?.showPicker()
    }

    fun setPickDateTime(date: String) {
        eta.set(date)

    }
}