package com.capstone_ch2_ps374.realone.navigation

sealed class Screen(val route: String) {
    object Login: Screen("login")
}
