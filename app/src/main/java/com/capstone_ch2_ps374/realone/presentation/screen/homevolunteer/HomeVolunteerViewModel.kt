package com.capstone_ch2_ps374.realone.presentation.screen.homevolunteer

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
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
class HomeVolunteerViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    val searchInput by mutableStateOf("")

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    fun fetchUserData(id: String) {
        _state.update {
            it.copy(
                isLoading = true
            )
        }
        viewModelScope.launch {
            try {
                val response = userRepository.getUserById(id)
                if (response != null){
                    _state.update {
                        it.copy(
                            userDataApi = response,
                            isLoading = false
                        )
                    }
                }
            } catch (e: Exception) {
            }
        }
    }

    fun fetchEvents() {
        _state.update {
            it.copy(
                isLoading = true
            )
        }
        viewModelScope.launch {
            try {
                val response = userRepository.getEvents()
                if (response != null){
                    _state.update {
                        it.copy(
                            events = response,
                            isLoading = false
                        )
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}