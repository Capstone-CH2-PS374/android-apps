package com.capstone_ch2_ps374.realone.presentation.screen.AddEvent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone_ch2_ps374.realone.domain.usecase.ValidateEmpty
import com.capstone_ch2_ps374.realone.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddFormViewModel @Inject constructor(
    private val validateEmpty: ValidateEmpty,
    private val userRepository: UserRepository
):ViewModel() {
    private val _state = MutableStateFlow(AddEventState())
    val state = _state.asStateFlow()

    fun onEvent(event: AddEventEvent){
        when(event){
            is AddEventEvent.NameChanged -> {
                _state.update {
                    it.copy(
                        name = event.name
                    )
                }
            }

            is AddEventEvent.StartChanged -> {
                _state.update {
                    it.copy(
                        startTime = event.start
                    )
                }
            }

            is AddEventEvent.EndChanged -> {
                _state.update {
                    it.copy(
                        endTime = event.end
                    )
                }
            }

            is AddEventEvent.DescChanged -> {
                _state.update {
                    it.copy(
                        desc = event.desc
                    )
                }
            }

            is AddEventEvent.EnrollDeadlineChanged -> {
                _state.update {
                    it.copy(
                        enrollDeadline = event.endrollDeadline
                    )
                }
            }

            is AddEventEvent.FieldChanged -> {
                _state.update {
                    it.copy(
                        field = event.field
                    )
                }
            }

            else -> {}
        }
    }

    fun fetchCategory(){
        viewModelScope.launch {
            try {
                val response = userRepository.getCategories()

                if (response != null){
                    _state.update {
                        it.copy(
                            categories = response
                        )
                    }
                }
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }

}