package com.capstone_ch2_ps374.realone.presentation.screen.login

data class LoginFormState(
    val email: String = "",
    val emailError:String? = null,
    val password: String = "",
    val passwordError: String? = null,
    val orgEmail: String = "",
    val orgEmailError:String? = null,
    val orgPassword: String = "",
    val orgPasswordError: String? = null
)
