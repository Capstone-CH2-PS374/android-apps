package com.capstone_ch2_ps374.realone.presentation.screen.login

data class SignInResult(
    val data: UserData? = null,
    val errorMessage: String? = null
)

data class UserData(
    val userId: String,
    val username: String?,
    val profilePictureUrl: String?
)