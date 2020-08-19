package com.facio.apps.ui.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.facio.apps.BR
import com.facio.apps.R
import com.facio.apps.ViewModelProviderFactory
import com.facio.apps.databinding.FragmentLoginBinding
import com.facio.apps.ui.base.BaseFragment

import javax.inject.Inject

class LoginFragment : BaseFragment<FragmentLoginBinding?, LoginViewModel>(), LoginNavigator {

    @JvmField
    @Inject
    var factory: ViewModelProviderFactory? = null

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_login
    override val viewModel: LoginViewModel
        get() = ViewModelProvider(this, factory!!).get(LoginViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setNavigator(this)
        viewModel.checkLogin()

        val callback: OnBackPressedCallback = object : OnBackPressedCallback(true /* enabled by default */) {
            override fun handleOnBackPressed() {
                activity?.finish()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListener()
    }

    private fun setListener() {
        viewModel.errorMail.observe(viewLifecycleOwner, Observer<Boolean> {
            if (!it) {
                viewDataBinding?.edtEmail?.error = "Email not valid"
            } else {
                viewDataBinding?.edtEmail?.error = null
            }
        })

        viewModel.errorPassword.observe(viewLifecycleOwner, Observer<Boolean> {
            if (!it) {
                viewDataBinding?.edtPassword?.error = "Password minimum 4 Character"
            } else {
                viewDataBinding?.edtPassword?.error = null
            }
        })
    }

    override fun showError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun successLogin() {
        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
    }

    override fun displayMainPage() {
        successLogin()
    }

    override fun handleError(throwable: Throwable?) {
        Toast.makeText(context, throwable?.message, Toast.LENGTH_SHORT).show()
    }
}