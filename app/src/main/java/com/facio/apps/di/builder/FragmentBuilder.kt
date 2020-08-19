package com.facio.apps.di.builder

import com.facio.apps.ui.addTrip.AddTripFragment
import com.facio.apps.ui.datePicker.DatePickerDialogFragment
import com.facio.apps.ui.login.LoginFragment
import com.facio.apps.ui.home.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class FragmentBuilder {

    @ContributesAndroidInjector(modules = [])
    abstract fun bindLoginFragment(): LoginFragment?

    @ContributesAndroidInjector(modules = [])
    abstract fun bindHomeFragment(): HomeFragment?

    @ContributesAndroidInjector(modules = [])
    abstract fun bindAddTripFragment(): AddTripFragment?

    @ContributesAndroidInjector(modules = [])
    abstract fun bindDatePickerDialog(): DatePickerDialogFragment?

}