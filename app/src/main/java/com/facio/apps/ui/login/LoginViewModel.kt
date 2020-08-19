package com.facio.apps.ui.login

import android.text.Editable
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.facio.apps.domain.usecases.user.IUserUsecases
import com.facio.apps.ui.base.BaseViewModel
import com.facio.apps.utils.SchedulerProvider
import com.facio.apps.utils.Validator
import kotlinx.coroutines.launch

open class LoginViewModel(baseUsecase: IUserUsecases, schedulerProvider: SchedulerProvider)
    : BaseViewModel<IUserUsecases, LoginNavigator>(baseUsecase, schedulerProvider) {

    var identifier = ObservableField<String>()
    var password = ObservableField<String>()

    var errorMail = MutableLiveData<Boolean>()
    var errorPassword = MutableLiveData<Boolean>()

    override fun defineLayout() {

    }

    fun afterIdentifierChanged(s: Editable) {
        identifier.set(s.toString())
        errorMail.postValue(Validator.isValidEmail(identifier.get()))
    }


    fun afterPasswordChanged(s: Editable) {
        password.set(s.toString())
        errorPassword.postValue(Validator.isMinimumLength(password.get(), 4))
    }

    fun doLogin() {
        if ((errorMail.value != null && errorPassword.value != null) && (errorMail.value!! && errorPassword.value!!)) {
            viewModelScope.launch {
                isLoading(true)
                try {
                    val responseApi = baseUsecase.login(identifier.get()!!, password.get()!!)
                    checkResponse(responseApi.blockingGet())
                } catch (e: Exception) {
                    onError(e)
                }
            }
        } else {
            navigator?.showError("All field must be valid")
            return
        }
    }

    private fun checkResponse(responseApi: Any?) {
        isLoading(false)
        if (responseApi is Boolean) {
            navigator?.successLogin()
        } else {
            navigator?.showError(responseApi.toString())
        }
    }

    override fun onSuccess(o: Any?) {
        isLoading(false)
        if (o is Boolean) {
            navigator?.successLogin()
        } else {
            navigator?.showError(o.toString())
        }
    }

    fun checkLogin() {
        if (baseUsecase.checkToken()) {
            navigator?.displayMainPage()
        }
    }


}