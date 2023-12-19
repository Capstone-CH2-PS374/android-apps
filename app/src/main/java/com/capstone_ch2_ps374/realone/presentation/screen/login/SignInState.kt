package com.capstone_ch2_ps374.realone.presentation.screen.login

data class SignInState(
    val isLoading: Boolean = false,
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null
)