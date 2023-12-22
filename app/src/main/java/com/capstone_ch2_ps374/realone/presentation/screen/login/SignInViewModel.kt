package com.capstone_ch2_ps374.realone.presentation.screen.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone_ch2_ps374.realone.api.UserResponse
import com.capstone_ch2_ps374.realone.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val userRepo: UserRepository
) : ViewModel() {

    private val _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()

    fun onSignInResult(result: SignInResult) {
        _state.update {
            it.copy(
                isLoading = false,
                isSignInSuccessful = result.data != null,
                signInError = result.errorMessage
            )
        }
    }

    fun setIsLoading(condition: Boolean) {
        _state.update {
            it.copy(
                isLoading = condition
            )
        }
    }

    fun registApi (id: String) {
        viewModelScope.launch {
            try {
                userRepo.register(id)
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }

    fun fetchUserData(id:String) {
        val exceptionHandler = CoroutineExceptionHandler { _, exception ->
            _state.update {
                it.copy(
                    userDataApi = UserResponse(
                        address = "error"
                    )
                )
            }
            exception.printStackTrace()

        }
        viewModelScope.launch(exceptionHandler) {
            try {
                _state.update {
                    it.copy(
                        isLoading = true
                    )
                }
                val response = async {  userRepo.getUserById(id) }.await()

                if (response != null) {
                    _state.update {
                        it.copy(
                            userDataApi = response
                        )
                    }
                }else{
                    _state.update {
                        it.copy(
                            isLoading = false
                        )
                    }
                }
            } catch (e: IOException){
                e.printStackTrace()
            }
            catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun resetState() {
        _state.update { SignInState() }
    }


}