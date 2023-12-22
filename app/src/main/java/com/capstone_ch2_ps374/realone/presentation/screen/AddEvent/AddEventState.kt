package com.capstone_ch2_ps374.realone.presentation.screen.AddEvent

import com.capstone_ch2_ps374.realone.api.CategoryResponse

data class AddEventState(
    val name: String = "",
    val nameError: String? = null,
    val startTime: String = "",
    val startTimeError: String? = null,
    val endTime: String = "",
    val endTimeError: String? = null,
    val type: String = "",
    val typeError: String? = null,
    val desc: String = "",
    val descError: String? = null,
    val field: CategoryResponse = CategoryResponse(),
    val fieldError: String? = null,
    val enrollDeadline: String = "",
    val enrollDeadlineError: String? = null,
    val categories: List<CategoryResponse>? = null
)
