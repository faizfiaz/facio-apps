package com.facio.apps.ui.home

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.facio.apps.R
import com.facio.apps.databinding.ItemTripBinding
import com.facio.apps.domain.models.Trips
import com.facio.apps.domain.usecases.user.IUserUsecases
import com.facio.apps.ui.base.BaseViewModel
import com.facio.apps.ui.home.adapter.TripListAdapter
import com.facio.apps.utils.AndroidUtils
import com.facio.apps.utils.SchedulerProvider
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

open class HomeViewModel(baseUsecase: IUserUsecases, schedulerProvider: SchedulerProvider)
    : BaseViewModel<IUserUsecases, HomeNavigator>(baseUsecase, schedulerProvider) {

    var search = ObservableField<String>()
    var isEmpty = ObservableBoolean(true)
    var isLoadingLoadMore = false

    var tripListAdapter = TripListAdapter(ArrayList(), ::goToDetailTrip)


    override fun defineLayout() {
        appBarTitle.set(AndroidUtils.getString(R.string.app_name))
    }

    fun getAdapter(): TripListAdapter {
        return tripListAdapter
    }

    private fun goToDetailTrip(trips: Trips, binding: ItemTripBinding) {

    }

    fun doGetTrips() {
        viewModelScope.launch {
            isLoading(true)
            try {
                val responseApi = baseUsecase.getTrips()
                checkResponse(responseApi.blockingGet())
            } catch (e: Exception) {
                onError(e)
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun checkResponse(responseApi: Any?) {
        isLoading(false)
        if (responseApi is String) {
            navigator?.showError(responseApi.toString())
        } else {
            populateData(responseApi as List<Trips>)
        }
        if (isLoadingLoadMore) {
            isLoadingLoadMore = false
        }
    }

    private fun populateData(list: List<Trips>) {
        isEmpty.set(list.isEmpty())
        if (!isLoadingLoadMore) {
            tripListAdapter.clearItems()
        } else {
            tripListAdapter.clearLatest()
        }
        tripListAdapter.addItems(list)
        runBlocking { }
    }

    override fun onSuccess(o: Any?) {
        isLoading(false)
        if (o is Boolean) {
            navigator?.movePage()
        } else {
            navigator?.showError(o.toString())
        }
    }

    fun clickAddTrip() {
        navigator?.displayAddTripPage()
    }

    fun addData(data: Trips?) {
        tripListAdapter.addItem(data!!)
    }


}