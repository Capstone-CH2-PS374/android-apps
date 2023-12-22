package com.capstone_ch2_ps374.realone.presentation.screen.homevolunteer

import com.capstone_ch2_ps374.realone.api.EventResponse
import com.capstone_ch2_ps374.realone.api.UserResponse

data class HomeState(
    val userDataApi: UserResponse? = null,
    val events: List<EventResponse>? = null,
    val isLoading: Boolean = false,
)