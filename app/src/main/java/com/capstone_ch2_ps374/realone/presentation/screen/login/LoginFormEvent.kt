package com.capstone_ch2_ps374.realone.presentation.screen.login

sealed class LoginFormEvent {
    data class EmailChanged(val email: String) : LoginFormEvent()
    data class PasswordChanged(val password: String) : LoginFormEvent()
    object Submit: LoginFormEvent()
}