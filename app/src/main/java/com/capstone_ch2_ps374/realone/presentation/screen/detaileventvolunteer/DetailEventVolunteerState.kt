package com.capstone_ch2_ps374.realone.presentation.screen.detaileventvolunteer

import com.capstone_ch2_ps374.realone.api.CategoryResponse
import com.capstone_ch2_ps374.realone.api.EventDetailResponse

data class DetailEventVolunteerState(
    val showConfirmDialog: Boolean = false,
    val isLoading: Boolean = false,
    val detail: EventDetailResponse? = null,
    val categories: List<CategoryResponse>? = null
)
