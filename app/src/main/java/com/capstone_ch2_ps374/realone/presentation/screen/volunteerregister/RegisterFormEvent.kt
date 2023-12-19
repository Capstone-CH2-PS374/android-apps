package com.capstone_ch2_ps374.realone.presentation.screen.volunteerregister

sealed class RegisterFormEvent {
    data class EmailChanged(val email: String) : RegisterFormEvent()
    data class PasswordChanged(val password: String) : RegisterFormEvent()
    data class PasswordRepeatedChanged(val passwordRepeated: String) : RegisterFormEvent()
    object Submit: RegisterFormEvent()
}
