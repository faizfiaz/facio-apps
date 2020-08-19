package com.facio.apps.ui.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.facio.apps.BR
import com.facio.apps.R
import com.facio.apps.ViewModelProviderFactory
import com.facio.apps.databinding.FragmentHomeBinding
import com.facio.apps.domain.models.Trips
import com.facio.apps.ui.base.BaseFragment
import javax.inject.Inject


class HomeFragment : BaseFragment<FragmentHomeBinding?, HomeViewModel>(), HomeNavigator {

    @JvmField
    @Inject
    var factory: ViewModelProviderFactory? = null

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_home
    override val viewModel: HomeViewModel
        get() = ViewModelProvider(this, factory!!).get(HomeViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setNavigator(this)

        val callback: OnBackPressedCallback = object : OnBackPressedCallback(true /* enabled by default */) {
            override fun handleOnBackPressed() {
                activity?.finish()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.doGetTrips()

        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<Trips>("data")?.observe(viewLifecycleOwner)
        { data ->
            viewModel.addData(data)
        }
    }

    override fun showError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun movePage() {
//        findNavController().navigate(R.id.mainPageFragment)
    }

    override fun displayAddTripPage() {
        findNavController().navigate(R.id.action_homeFragment_to_addTripFragment)
    }

    override fun handleError(throwable: Throwable?) {
        Toast.makeText(context, throwable?.message, Toast.LENGTH_SHORT).show()
    }
}