package com.porto.anycook.ui.main.account

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.porto.anycook.ui.base.AnyCookFragment
import com.porto.anycook.R
import com.porto.anycook.ui.main.MainFragmentDirections
import com.porto.core.domain.user.models.User
import kotlinx.android.synthetic.main.account_fragment.*

class AccountFragment : AnyCookFragment() {

    override val layoutResourceId: Int = R.layout.account_fragment

    private val accountViewModel by activityViewModels<AccountViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        accountViewModel.onEventReceived(AccountViewModel.Event.InitializeAccountData)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bindViewModel()
        setEventAction()
    }

    private fun setEventAction() {
        btn_logout.setOnClickListener {
            accountViewModel.onEventReceived(AccountViewModel.Event.Logout)
        }
    }

    private fun bindViewModel(){
        accountViewModel.state.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled().let {state->
                when(state){
                    is AccountViewModel.State.ShowUser -> showUserData(state.user)
                    is AccountViewModel.State.ShowError -> showToast(state.message)
                    is AccountViewModel.State.GoToLoginPage -> fragmentInteractionListener
                            .navigateTo(MainFragmentDirections.actionMainFragmentToLoginFragment())
                }
            }
        })

    }

    private fun showUserData(user: User) {
        tv_username.text = user.name
        tv_email.text = user.email
        tv_phone.text = user.phoneNumber
        Glide.with(this).load(user.avatarUrl).into(img_avatar)
    }



}