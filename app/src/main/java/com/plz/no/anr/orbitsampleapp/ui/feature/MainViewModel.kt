package com.plz.no.anr.orbitsampleapp.ui.feature

import com.plz.no.anr.orbitsampleapp.repository.Model
import com.plz.no.anr.orbitsampleapp.repository.Repository
import com.plz.no.anr.orbitsampleapp.ui.base.BaseContract
import com.plz.no.anr.orbitsampleapp.ui.base.BaseViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce

class MainContract {
    data class State(
        val isLoading: Boolean,
        val isError: Boolean,
        val data: Model?
    ): BaseContract.UiState {
        companion object {
            fun initial() = State(
                isLoading = false,
                isError = false,
                data = null
            )
        }
    }

    sealed class SideEffect : BaseContract.SideEffect {
        data class ShowError(val error: Throwable) : SideEffect()
    }

    sealed class Intent : BaseContract.Intent {
        data class GetGenderOfName(val name: String) : Intent()
    }
}

class MainViewModel(
    private val repository: Repository,
): BaseViewModel<MainContract.State, MainContract.Intent, MainContract.SideEffect>() {

    override fun initialState(): MainContract.State = MainContract.State.initial()

    override fun handleIntents(intent: MainContract.Intent) = when(intent) {
        is MainContract.Intent.GetGenderOfName -> getGenderOfName(intent.name)
    }

    init {
        getGenderOfName("Peter")
    }

    private fun getGenderOfName(name: String) = intent {
        repository.getGenderOfName(name)
            .onStart {
                reduce {
                    state.copy(isLoading = true)
                }
            }.catch {
                reduce { state.copy(isLoading = false, isError = true) }
                postSideEffect(MainContract.SideEffect.ShowError(it))
            }.collect { result ->
                result.onSuccess {
                    reduce { state.copy(isLoading = false, data = it) }
                }.onFailure {
                    reduce { state.copy(isLoading = false, isError = true) }
                }

            }
    }


}