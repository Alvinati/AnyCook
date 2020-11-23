package com.porto.anycook.ui.login

import com.porto.anycook.ui.base.AnyCookViewModel
import com.porto.commons.models.Result
import com.porto.core.domain.authentication.LoginUseCase
import com.porto.core.domain.user.models.InitialPage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val loginUseCase : LoginUseCase)
    : AnyCookViewModel<LoginViewModel.Event, LoginViewModel.State>()  {

    sealed class Event {
        class LoginButtonTapped(val email: String, val password: String) : Event()
    }

    sealed class State {
        class ShowError(val message: String?) : State()
        object ShowMainPage : State()
        object ShowFieldEmptyError : State()
    }

    override fun onEventReceived(event: Event) {
        when(event) {
            is Event.LoginButtonTapped -> login(event.email, event.password)
        }
    }

    private fun login(email : String, password: String) = launch{

        if(!isAllFieldValid(email, password))
            return@launch

        val result = withContext(Dispatchers.IO) {
            loginUseCase.execute(LoginUseCase.Params(email, password))
        }

        when(result) {
            is Result.Success -> {
                when(result.value){
                    is InitialPage.Home ->
                        pushState(State.ShowMainPage)
                    else -> pushState(State.ShowError("Login Gagal"))
                }
            }
            is Result.Error -> {
                pushState(State.ShowError(result.errorMessage))
            }

        }


    }

    private fun isAllFieldValid(email: String, password: String) : Boolean {
       return if(email.isEmpty() || password.isEmpty()){
            pushState(State.ShowFieldEmptyError)
             false
        } else  true
    }


}