package com.capstone_ch2_ps374.realone.presentation.screen.AddEvent

import com.capstone_ch2_ps374.realone.api.CategoryResponse

sealed class AddEventEvent(){
    data class NameChanged(val name: String): AddEventEvent()
    data class StartChanged(val start: String): AddEventEvent()
    data class EndChanged(val end: String): AddEventEvent()
    data class TypeChanged(val type: String): AddEventEvent()
    data class DescChanged(val desc: String): AddEventEvent()
    data class FieldChanged(val field: CategoryResponse): AddEventEvent()
    data class EnrollDeadlineChanged(val endrollDeadline: String): AddEventEvent()
    object Submit: AddEventEvent()

}
