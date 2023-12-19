package com.capstone_ch2_ps374.realone.presentation.screen.login

data class LoginFormState(
    val email: String = "",
    val emailError:String? = null,
    val password: String = "",
    val passwordError: String? = null
)
