package com.capstone_ch2_ps374.realone.presentation.screen.detaileventvolunteer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone_ch2_ps374.realone.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailEventVolunteerViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
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

    fun fetchDetailEvent(id: String){
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }
            try {
                val response = userRepository.getEventDetail(id)
                if (response != null){
                    _state.update {
                        it.copy(
                            detail = response,
                            isLoading = false
                        )
                    }
                }
            }catch (e: Exception){

            }
        }
    }

    fun fetchCategories(){
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }
            try {
                val response = userRepository.getCategories()
                if (response != null){
                    _state.update {
                        it.copy(
                            categories = response,
                            isLoading = false
                        )
                    }
                }
            }catch (e: Exception){

            }
        }
    }
}