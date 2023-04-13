package com.plz.no.anr.orbitsampleapp.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container

abstract class BaseViewModel<State: BaseContract.UiState, Intent: BaseContract.Intent, SideEffect: BaseContract.SideEffect>
    : ContainerHost<State, SideEffect>, ViewModel() {

    abstract fun initialState(): State

    abstract fun handleIntents(intent: Intent)

    private val initialState: State by lazy { initialState() }

    override val container: Container<State, SideEffect> = container(initialState)

    private val _intent: MutableSharedFlow<Intent> = MutableSharedFlow()

    private val exceptionHandler by lazy {
        CoroutineExceptionHandler { _, t ->
            println("Error: $t")
        }
    }

    init {
        subscribeToIntents()
    }

    private fun subscribeToIntents() {
        viewModelScope.launch(exceptionHandler) {
            _intent.collect {
                handleIntents(it)
            }
        }
    }

    fun setIntent(intent: Intent) {
        viewModelScope.launch(exceptionHandler) { _intent.emit(intent) }
    }

}