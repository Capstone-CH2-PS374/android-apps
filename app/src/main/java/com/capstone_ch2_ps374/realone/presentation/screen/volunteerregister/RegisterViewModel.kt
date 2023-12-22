package com.capstone_ch2_ps374.realone.presentation.screen.volunteerregister

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone_ch2_ps374.realone.domain.usecase.ValidateEmail
import com.capstone_ch2_ps374.realone.domain.usecase.ValidatePassword
import com.capstone_ch2_ps374.realone.domain.usecase.ValidateRepeatedPassword
import com.capstone_ch2_ps374.realone.presentation.screen.login.LoginViewModel
import com.capstone_ch2_ps374.realone.presentation.screen.login.SignInResult
import com.capstone_ch2_ps374.realone.presentation.screen.login.UserData
import com.capstone_ch2_ps374.realone.repositories.UserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.util.concurrent.CancellationException
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val validateEmail: ValidateEmail,
    private val validatePassword: ValidatePassword,
    private val validateRepeatedPassword: ValidateRepeatedPassword,
    private val auth: FirebaseAuth,
    private val userRepository: UserRepository
) : ViewModel() {


    var _state = MutableStateFlow(RegisterFormState())
    val state = _state.asStateFlow()

    private val _passwordVisible = MutableStateFlow(false)
    val passwordVisible: StateFlow<Boolean> get() = _passwordVisible

    private val _passwordRepeatedVisible = MutableStateFlow(false)
    val passwordRepeatedVisible: StateFlow<Boolean> get() = _passwordRepeatedVisible

    fun registerWithEmail(email: String, password: String): SignInResult {
        _state.update {
            it.copy(
                isLoading = true
            )
        }
        return try {
            var user: FirebaseUser? = null
            val exceptionHandler = CoroutineExceptionHandler { _, exception ->
                exception.printStackTrace()
                _state.update {
                    it.copy(
                        isLoading = false,
                        isRegisterSuccessful = false,
                        registerError = exception.message
                    )
                }
            }
            viewModelScope.launch(exceptionHandler) {
                user = auth.createUserWithEmailAndPassword(email, password).await().user
                _state.update {
                    it.copy(
                        isLoading = false,
                        isRegisterSuccessful = true
                    )
                }
                registApi(user!!.uid)
            }
            SignInResult(
                data = user?.run {
                    UserData(
                        userId = uid,
                        username = displayName,
                        profilePictureUrl = photoUrl?.toString()
                    )
                },
                errorMessage = null
            )
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is FirebaseAuthUserCollisionException) throw e
            _state.update {
                it.copy(
                    isLoading = false,
                    isRegisterSuccessful = false,
                    registerError = e.message
                )
            }
            SignInResult(
                data = null,
                errorMessage = e.message
            )
        }
    }

    fun togglePasswordVisibility() {
        _passwordVisible.value = !_passwordVisible.value
    }

    fun togglePasswordRepeatedVisibility() {
        _passwordRepeatedVisible.value = !_passwordRepeatedVisible.value
    }

    private val validationEventChannel = Channel<LoginViewModel.ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    suspend fun signInWithEmail(email: String, password: String): SignInResult {
        _state.update {
            it.copy(
                isLoading = true
            )
        }
        return try {
            val user = auth.signInWithEmailAndPassword(email, password).await().user
            _state.update {
                it.copy(
                    isLoading = false,
                    signInSuccess = true
                )
            }
            SignInResult(
                data = user?.run {
                    UserData(
                        userId = uid,
                        username = displayName,
                        profilePictureUrl = photoUrl?.toString()
                    )
                },
                errorMessage = null
            )
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            SignInResult(
                data = null,
                errorMessage = e.message
            )
        }
    }

    fun onEvent(event: RegisterFormEvent) {
        when (event) {
            is RegisterFormEvent.EmailChanged -> {
                _state.update {
                    it.copy(
                        email = event.email
                    )
                }
            }

            is RegisterFormEvent.PasswordChanged -> {
                _state.update {
                    it.copy(
                        password = event.password
                    )
                }
            }

            is RegisterFormEvent.PasswordRepeatedChanged -> {
                _state.update {
                    it.copy(
                        passwordRepeated = event.passwordRepeated
                    )
                }
            }

            is RegisterFormEvent.Submit -> {
                submitData()
            }
        }
    }

    fun registApi (id: String) {
        viewModelScope.launch {
            try {
                val response = userRepository.register(id)
            }catch (e:Exception){

            }
        }
    }

    private fun submitData() {
        val emailResult = validateEmail.execute(state.value.email)
        val passwordResult = validatePassword.execute(state.value.password)
        val passwordRepeatedResult = validateRepeatedPassword.execute(
            password = state.value.password,
            passwordRepeated = state.value.passwordRepeated
        )


        val hasError = listOf(
            emailResult,
            passwordResult,
            passwordRepeatedResult
        ).any { !it.successful }

        if (hasError) {
            _state.update {
                it.copy(
                    emailError = emailResult.errorMessage,
                    passwordError = passwordResult.errorMessage,
                    passwordRepeatedError = passwordRepeatedResult.errorMessage
                )
            }
        } else {
            viewModelScope.launch {
                registerWithEmail(state.value.email, state.value.password)
            }
        }
    }

}