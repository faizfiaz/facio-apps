package com.facio.apps.ui.addTrip

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.facio.apps.BR
import com.facio.apps.R
import com.facio.apps.ViewModelProviderFactory
import com.facio.apps.databinding.FragmentAddTripBinding
import com.facio.apps.domain.models.Trips
import com.facio.apps.ui.base.BaseFragment
import com.facio.apps.ui.datePicker.CallbackDatePicker
import com.facio.apps.ui.datePicker.DatePickerDialogFragment
import javax.inject.Inject

class AddTripFragment : BaseFragment<FragmentAddTripBinding, AddTripViewModel>(), AddTripNavigator,
        CallbackDatePicker {

    @JvmField
    @Inject
    var factory: ViewModelProviderFactory? = null

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_add_trip
    override val viewModel: AddTripViewModel
        get() = ViewModelProvider(this, factory!!).get(AddTripViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setNavigator(this)
    }

    override fun showPicker() {
        val bundle = Bundle()
        val mDialog = DatePickerDialogFragment()
        mDialog.setCallback(this)
        mDialog.arguments = bundle
        mDialog.show(childFragmentManager, DatePickerDialogFragment::javaClass.name)
    }

    override fun successAddTrip(trips: Trips) {
        Toast.makeText(context, "Success Add Trip", Toast.LENGTH_SHORT).show()
        findNavController().previousBackStackEntry?.savedStateHandle?.set("data", trips)
        findNavController().popBackStack()
    }

    override fun handleError(throwable: Throwable?) {

    }

    override fun onPick(date: String) {
        viewModel.setPickDateTime(date)
    }
}