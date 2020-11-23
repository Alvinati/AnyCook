package com.porto.anycook.ui.main

import com.porto.anycook.ui.base.AnyCookViewModel
import com.porto.commons.models.Result
import com.porto.core.domain.authentication.GetAuthenticationUseCase
import com.porto.core.domain.user.models.InitialPage
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(private val getAuthenticationUseCase: GetAuthenticationUseCase)
    : AnyCookViewModel<MainViewModel.Event, MainViewModel.State>() {

    sealed class Event {
        object OnLoadPage : Event()
    }

    sealed class State {
        class ShowStartPage(val page : InitialPage) : State()
    }

    override fun onEventReceived(event: Event) {
        when(event) {
            is Event.OnLoadPage -> checkAuthentication()
        }

    }

    private fun checkAuthentication() = launch {
        when(val result = getAuthenticationUseCase.execute()){
            is Result.Success -> {
                pushState(State.ShowStartPage(result.value))
            }
            is Result.Error -> {}
        }
    }



}