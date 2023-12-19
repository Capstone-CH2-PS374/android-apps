package com.capstone_ch2_ps374.realone.presentation.screen.volunteerregister

data class RegisterFormState(
    val email: String = "",
    val emailError:String? = null,
    val password: String = "",
    val passwordError: String? = null,
    val passwordRepeated: String = "",
    val passwordRepeatedError: String? = null,
    val isRegisterSuccessful: Boolean = false,
    val registerError: String? = null,
    val signInSuccess: Boolean? = null,
    val isLoading: Boolean = false,
)
