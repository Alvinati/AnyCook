package com.porto.anycook.ui.main.account

import com.porto.anycook.ui.base.AnyCookViewModel
import com.porto.commons.models.Result
import com.porto.core.domain.authentication.LogoutUseCase
import com.porto.core.domain.user.GetUserUseCase
import com.porto.core.domain.user.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AccountViewModel @Inject constructor(private val getUserUseCase: GetUserUseCase,
                                           private val logoutUseCase: LogoutUseCase)
    : AnyCookViewModel<AccountViewModel.Event, AccountViewModel.State>() {

    sealed class Event {
        object InitializeAccountData : Event()
        object Logout : Event()
    }

    sealed class State {
        class ShowUser(val user: User) : State()
        class ShowError(val message : String) : State()
        object GoToLoginPage : State()
    }

    override fun onEventReceived(event: Event) {
        when(event){
            is Event.InitializeAccountData -> loadAndShowUserData()
            is Event.Logout -> doLogout()
        }
    }

    private fun loadAndShowUserData() = launch {
        val result = withContext(Dispatchers.IO) {
            getUserUseCase.execute()
        }

        when(result){
            is Result.Success -> pushState(State.ShowUser(result.value))
            is Result.Error -> pushState(State.ShowError(result.errorMessage
                ?: "We're sorry, something went wrong"))
        }
    }

    private fun doLogout() = launch {

        val result = withContext(Dispatchers.IO) {
            logoutUseCase.execute()
        }

        when(result) {
            is Result.Success -> pushState(State.GoToLoginPage)
            is Result.Error -> pushState(State.ShowError(result.errorMessage ?: "Failed to logout"))
        }
    }

}