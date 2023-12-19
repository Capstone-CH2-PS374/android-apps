package com.capstone_ch2_ps374.realone.presentation.screen.volunteerregister

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.capstone_ch2_ps374.realone.R
import com.capstone_ch2_ps374.realone.presentation.component.FullSizeButton
import com.capstone_ch2_ps374.realone.presentation.screen.login.LoginViewModel
import com.capstone_ch2_ps374.realone.presentation.theme.Typography
import com.capstone_ch2_ps374.realone.presentation.theme.padding
import com.example.compose.md_theme_dark_error

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VolunteerRegisterScreen(
    viewModel: RegisterViewModel,
    context: Context,
    navController:NavHostController,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    LaunchedEffect(key1 = state.isRegisterSuccessful, key2 = state.registerError) {
        if (state.isRegisterSuccessful) {
            viewModel.signInWithEmail(state.email, state.password)
        }
        if (state.registerError != null){
            Toast.makeText(context, state.registerError, Toast.LENGTH_SHORT).show()
        }
    }

    LaunchedEffect(key1 = state.signInSuccess) {
        if (state.signInSuccess != null && state.signInSuccess!!) {
            navController.popBackStack()
        }
    }

    if (state.isLoading) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Gray.copy(alpha = 0.5f))
        ) {
            CircularProgressIndicator()
        }
    } else {
        Column {
            Box(
                Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(R.drawable.login_background),
                    contentDescription = "background-top",
                    contentScale = ContentScale.FillWidth,
                    alignment = Alignment.BottomCenter,
                    modifier = Modifier.height(250.dp)
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(horizontal = padding)
                ) {
                    Spacer(modifier = Modifier.height(50.dp))
                    Text(text = "Pendaftaran Akun", style = Typography.displaySmall)
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.background_bottom_login),
                    contentDescription = "background bawah",
                    alignment = Alignment.BottomStart,
                    modifier = Modifier.fillMaxHeight()
                )
                Column {
                    val passWordVisible by viewModel.passwordVisible.collectAsState()
                    val passWordRepeatedVisible by viewModel.passwordRepeatedVisible.collectAsState()

                    LaunchedEffect(key1 = context) {
                        viewModel.validationEvents.collect { event ->
                            when (event) {
                                is LoginViewModel.ValidationEvent.Succes -> {
                                    Toast.makeText(
                                        context, "Registration successful", Toast.LENGTH_LONG
                                    ).show()
                                }
                            }
                        }
                    }
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 30.dp)
                    ) {
                        Spacer(modifier = Modifier.height(10.dp))
//        Row(
//            horizontalArrangement = Arrangement.Start,
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            Text(text = "Hai Relawan!", style = Typography.titleLarge, textAlign = TextAlign.Start)
//        }
//        Row(
//            horizontalArrangement = Arrangement.Start,
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            Text(
//                text = "Selamat Datang",
//                style = Typography.titleLarge,
//                textAlign = TextAlign.Start
//            )
//        }
                        Spacer(modifier = Modifier.height(30.dp))
                        OutlinedTextField(
                            value = state.email,
                            onValueChange = {
                                viewModel.onEvent(RegisterFormEvent.EmailChanged(it))
                            },
                            isError = state.emailError != null,
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = {
                                Text(text = "Email")
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Email
                            )
                        )
                        if (state.emailError != null) {
                            Text(
                                text = state.emailError!!,
                                color = md_theme_dark_error,
                                modifier = Modifier.align(Alignment.End)
                            )
                        }
                        Spacer(modifier = Modifier.height(30.dp))
                        OutlinedTextField(value = state.password,
                            onValueChange = {
                                viewModel.onEvent(RegisterFormEvent.PasswordChanged(it))
                            },
                            isError = state.passwordError != null,
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = {
                                Text(text = "Password")
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Password
                            ),
                            visualTransformation = if (passWordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                            trailingIcon = {
                                val image = if (passWordVisible) Icons.Filled.Visibility
                                else Icons.Filled.VisibilityOff

                                val description =
                                    if (passWordVisible) "Hide password" else "Show password"

                                IconButton(onClick = { viewModel.togglePasswordVisibility() }) {
                                    Icon(imageVector = image, description)
                                }
                            })
                        if (state.passwordError != null) {
                            Text(
                                text = state.passwordError!!,
                                color = md_theme_dark_error,
                                modifier = Modifier.align(Alignment.End)
                            )
                        }
                        Spacer(modifier = Modifier.height(30.dp))
                        OutlinedTextField(value = state.passwordRepeated,
                            onValueChange = {
                                viewModel.onEvent(RegisterFormEvent.PasswordRepeatedChanged(it))
                            },
                            isError = state.passwordRepeatedError != null,
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = {
                                Text(text = "Ulangi Password")
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Password
                            ),
                            visualTransformation = if (passWordRepeatedVisible) VisualTransformation.None else PasswordVisualTransformation(),
                            trailingIcon = {
                                val image = if (passWordRepeatedVisible) Icons.Filled.Visibility
                                else Icons.Filled.VisibilityOff

                                val description =
                                    if (passWordRepeatedVisible) "Hide password" else "Show password"

                                IconButton(onClick = { viewModel.togglePasswordRepeatedVisibility() }) {
                                    Icon(imageVector = image, description)
                                }
                            })
                        if (state.passwordRepeatedError != null) {
                            Text(
                                text = state.passwordRepeatedError!!,
                                color = md_theme_dark_error,
                                modifier = Modifier.align(Alignment.End)
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        FullSizeButton(
                            text = "Daftar",
                            onClick = {
                                viewModel.onEvent(RegisterFormEvent.Submit)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(40.dp)
                                .padding(horizontal = 60.dp),
                        )
                    }
                }
            }
        }
    }
}