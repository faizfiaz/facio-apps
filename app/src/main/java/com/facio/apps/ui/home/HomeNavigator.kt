package com.facio.apps.ui.home

import com.facio.apps.ui.base.BaseNavigator

interface HomeNavigator : BaseNavigator {
    fun showError(message: String)
    fun movePage()
    fun displayAddTripPage()
}