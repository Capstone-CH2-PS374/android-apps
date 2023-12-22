package com.capstone_ch2_ps374.realone.presentation.screen.login

import com.capstone_ch2_ps374.realone.api.UserResponse

data class SignInState(
    val isLoading: Boolean = false,
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null,
    val userDataApi: UserResponse? = null,
    val isOrg:Boolean = false
)