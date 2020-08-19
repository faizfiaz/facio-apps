package com.facio.apps.ui.login

import com.facio.apps.ui.base.BaseNavigator

interface LoginNavigator : BaseNavigator {
    fun showError(message: String)
    fun successLogin()
    fun displayMainPage()
}