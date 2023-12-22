package com.capstone_ch2_ps374.realone.presentation.screen.login

import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone_ch2_ps374.realone.api.ApiService
import com.capstone_ch2_ps374.realone.domain.usecase.ValidateEmail
import com.capstone_ch2_ps374.realone.domain.usecase.ValidatePassword
import com.capstone_ch2_ps374.realone.repositories.UserRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
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
class LoginViewModel @Inject constructor(
    private val validateEmail: ValidateEmail,
    private val validatePassword: ValidatePassword,
    private val auth: FirebaseAuth,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _state = MutableStateFlow(SignInState())
    val stateSignIn = _state.asStateFlow()

    var state by mutableStateOf(LoginFormState())

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    private val _passwordVisible = MutableStateFlow(false)
    val passwordVisible: StateFlow<Boolean> get() = _passwordVisible

    fun togglePasswordVisibility() {
        _passwordVisible.value = !_passwordVisible.value
    }

    suspend fun signInWithEmail(
        email: String,
        password: String,
        isOrg: Boolean = false
    ): SignInResult {
        _state.update {
            it.copy(
                isLoading = true
            )
        }
        return try {
            val user = auth.signInWithEmailAndPassword(email, password).await().user
            val userData = userRepository.getUserById(user!!.uid)
            _state.update {
                it.copy(
                    isOrg = userData.typeOrganization!!.isNotEmpty()
                )
            }
            _state.update {
                it.copy(
                    isLoading = false,
                    isSignInSuccessful = true
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
            _state.update {
                it.copy(
                    isLoading = false,
                    signInError = "Email atau password salah"
                )
            }
            SignInResult(
                data = null,
                errorMessage = e.message
            )
        }
    }

    fun onEvent(event: LoginFormEvent) {
        when (event) {
            is LoginFormEvent.EmailChanged -> {
                state = state.copy(email = event.email)
            }

            is LoginFormEvent.PasswordChanged -> {
                state = state.copy(password = event.password)
            }

            is LoginFormEvent.OrgEmailChanged -> {
                state = state.copy(orgEmail = event.email)
            }

            is LoginFormEvent.OrgPasswordChanged -> {
                state = state.copy(orgPassword = event.password)
            }

            is LoginFormEvent.Submit -> {
                submitData()
            }

            is LoginFormEvent.SubmitOrg -> {
                submitOrgData()
            }

            else -> {}
        }
    }

    private fun submitOrgData() {
        val orgEmailResult = validateEmail.execute(state.orgEmail)
        val orgPasswordResult = validatePassword.execute(state.orgPassword)

        val hasError = listOf(
            orgEmailResult,
            orgPasswordResult
        ).any { !it.successful }

        if (hasError) {
            state = state.copy(
                emailError = orgEmailResult.errorMessage,
                passwordError = orgPasswordResult.errorMessage
            )
        } else {
            viewModelScope.launch {
                signInWithEmail(state.orgEmail, state.orgPassword, true)
            }
        }
    }

    private fun submitData() {
        val emailResult = validateEmail.execute(state.email)
        val passwordResult = validatePassword.execute(state.password)

        val hasError = listOf(
            emailResult,
            passwordResult
        ).any { !it.successful }

        if (hasError) {
            state = state.copy(
                emailError = emailResult.errorMessage,
                passwordError = passwordResult.errorMessage
            )
        } else {
            viewModelScope.launch {
                validationEventChannel.send(ValidationEvent.Succes)
                signInWithEmail(state.email, state.password)
            }
        }
    }

    fun resetState() {
        _state.update { SignInState() }
    }

    sealed class ValidationEvent {
        object Succes : ValidationEvent()
    }
}