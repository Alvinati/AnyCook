package com.porto.anycook.ui.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.porto.anycook.R
import com.porto.anycook.ui.base.AnyCookFragment
import kotlinx.android.synthetic.main.login_fragment.*

class LoginFragment : AnyCookFragment() {

    private val loginViewModel by activityViewModels<LoginViewModel> { viewModelFactory }

    override val layoutResourceId: Int = R.layout.login_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureViewEvents()
        bindViewModel()
    }


    private fun configureViewEvents() {
        btn_login?.setOnClickListener {
            loginViewModel.onEventReceived(LoginViewModel.Event.LoginButtonTapped(tie_email.text.toString(),
                tie_password.text.toString()))
        }
    }
    private fun bindViewModel() {
        loginViewModel.state.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled().let { state ->
                when(state) {
                    is LoginViewModel.State.ShowError -> showToast(state.message)
                    is LoginViewModel.State.ShowMainPage -> goToMainPage()
                    is LoginViewModel.State.ShowFieldEmptyError -> alertFieldEmpty()
                }
            }
        })

    }

    private fun goToMainPage() {
        fragmentInteractionListener.navigateTo(LoginFragmentDirections
                .actionLoginFragmentToMainFragment())
    }

}