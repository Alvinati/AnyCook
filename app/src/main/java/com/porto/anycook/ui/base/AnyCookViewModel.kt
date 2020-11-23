package com.porto.anycook.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.porto.anycook.ui.commons.StateObserveOnce
import com.porto.commons.models.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

abstract class AnyCookViewModel<Event, State> : ViewModel(), CoroutineScope {

    private val parentJob = SupervisorJob()

    private val _state = MutableLiveData<StateObserveOnce<State>>()
    val state : LiveData<StateObserveOnce<State>>
        get() = _state

    override val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main

    abstract fun onEventReceived(event: Event)

    protected fun pushState(state: State) {
        _state.value = StateObserveOnce(state)
    }

    protected suspend fun <T: Any> fetch(execute : Result<T>) : Result<T> =
            withContext(Dispatchers.IO) {
                return@withContext execute
            }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }


}