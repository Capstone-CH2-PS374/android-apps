package com.capstone_ch2_ps374.realone.presentation.screen.detaileventvolunteer

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class DetailEventVolunteerViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(DetailEventVolunteerState())
    val state = _state.asStateFlow()

    fun showDialog(isShow: Boolean) {
        if (isShow) {
            _state.update {
                it.copy(
                    showConfirmDialog = true
                )
            }
        } else {
            _state.update {
                it.copy(
                    showConfirmDialog = false
                )
            }
        }
    }
}