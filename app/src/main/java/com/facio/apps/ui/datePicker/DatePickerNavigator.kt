package com.facio.apps.ui.datePicker

import com.facio.apps.ui.base.BaseNavigator

interface DatePickerNavigator : BaseNavigator {
    fun dismissDialog()
    fun showTimePicker()
    fun sendDateTime(dateTime: String?)
}