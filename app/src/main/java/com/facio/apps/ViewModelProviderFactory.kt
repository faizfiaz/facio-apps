package com.facio.apps

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import com.facio.apps.data.remote.UserRepository
import com.facio.apps.domain.mappers.UserMapper
import com.facio.apps.domain.usecases.user.IUserUsecases
import com.facio.apps.domain.usecases.user.UserUsecases
import com.facio.apps.ui.addTrip.AddTripViewModel
import com.facio.apps.ui.datePicker.DatePickerViewModel

import com.facio.apps.ui.home.HomeViewModel
import com.facio.apps.ui.login.LoginViewModel
import com.facio.apps.utils.SchedulerProvider
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ViewModelProviderFactory @Inject constructor(private val schedulerProvider: SchedulerProvider) : NewInstanceFactory() {
    private val userUsecases: IUserUsecases

    init {
        userUsecases = UserUsecases(UserMapper(), UserRepository.instance!!)
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(userUsecases, schedulerProvider) as T
            }
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(userUsecases, schedulerProvider) as T
            }
            modelClass.isAssignableFrom(AddTripViewModel::class.java) -> {
                AddTripViewModel(userUsecases, schedulerProvider) as T
            }
            modelClass.isAssignableFrom(DatePickerViewModel::class.java) -> {
                DatePickerViewModel(userUsecases, schedulerProvider) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }


}