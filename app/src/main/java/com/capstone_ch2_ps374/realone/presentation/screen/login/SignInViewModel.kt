package com.capstone_ch2_ps374.realone.presentation.screen.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class SignInViewModel : ViewModel() {

    private val _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()

    fun onSignInResult(result: SignInResult) {
        _state.update {
            it.copy(
                isLoading = false,
                isSignInSuccessful = result.data != null,
                signInError = result.errorMessage
            )
        }
    }

    fun setIsLoading(condition: Boolean) {
        _state.update {
            it.copy(
                isLoading = condition
            )
        }
    }

    fun resetState() {
        _state.update { SignInState() }
    }
}