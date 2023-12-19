@file:OptIn(ExperimentalMaterial3Api::class)

package com.capstone_ch2_ps374.realone.presentation.screen.login

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.capstone_ch2_ps374.realone.R
import com.capstone_ch2_ps374.realone.navigation.Screen
import com.capstone_ch2_ps374.realone.presentation.component.FullSizeButton
import com.capstone_ch2_ps374.realone.presentation.theme.Typography
import com.capstone_ch2_ps374.realone.presentation.theme.padding
import com.example.compose.md_theme_dark_error
import com.example.compose.md_theme_dark_primary


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    signInWithGoogleClick: () -> Unit,
    onClickRegister: (Int) -> Unit,
    isLoading: Boolean,
    context: Context,
    navController: NavHostController
) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val pagerState = rememberPagerState {
        2
    }
    val stateSignIn by viewModel.stateSignIn.collectAsStateWithLifecycle()
    LaunchedEffect(selectedTabIndex) {
        pagerState.animateScrollToPage(selectedTabIndex)
    }
    LaunchedEffect(pagerState.currentPage) {
        selectedTabIndex = pagerState.currentPage
    }
    LaunchedEffect(stateSignIn.isSignInSuccessful) {
        if (stateSignIn.isSignInSuccessful) {
            Toast.makeText(
                context,
                "Login Berhasil",
                Toast.LENGTH_SHORT
            ).show()
            navController.navigate(Screen.VolunteerLayout.route)
        }
    }
    if (isLoading || stateSignIn.isLoading) {
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
                Column(Modifier.padding(horizontal = padding)) {
                    Spacer(Modifier.height(50.dp))
                    TabRow(
                        selectedTabIndex = selectedTabIndex,
                        modifier = Modifier.clip(
                            shape = RoundedCornerShape(25.dp)
                        ),
                        contentColor = Color.Black,
                    ) {
                        Tab(
                            selected = selectedTabIndex == 0,
                            onClick = { selectedTabIndex = 0 },
                            text = {
                                Text(
                                    text = "Relawan", style = Typography.titleMedium
                                )
                            },
                            modifier = Modifier.background(color = if (selectedTabIndex == 0) md_theme_dark_primary else Color.White)
                        )
                        Tab(
                            selected = selectedTabIndex == 1,
                            onClick = { selectedTabIndex = 1 },
                            text = {
                                Text(
                                    text = "Organisasi", style = Typography.titleMedium
                                )
                            },
                            modifier = Modifier.background(color = if (selectedTabIndex == 1) md_theme_dark_primary else Color.White)
                        )
                    }
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
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    when (selectedTabIndex) {
                        0 -> VolunteeLoginScreen(
                            viewModel = viewModel,
                            signInWithGoogleClick = signInWithGoogleClick,
                            onClickRegister = onClickRegister,
                            context = context
                        )

                        1 -> OrganizationLoginScreen(viewModel = viewModel, navController)
                    }
                }
            }
        }
    }
}


@Composable
fun OrganizationLoginScreen(
    viewModel: LoginViewModel,
    navController: NavHostController
) {
    Column {
        val state = viewModel.state
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
                    viewModel.onEvent(LoginFormEvent.EmailChanged(it))
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
                    text = state.emailError,
                    color = md_theme_dark_error,
                    modifier = Modifier.align(Alignment.End)
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
            OutlinedTextField(
                value = state.password,
                onValueChange = {
                    viewModel.onEvent(LoginFormEvent.PasswordChanged(it))
                },
                isError = state.passwordError != null,
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(text = "Password")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),
                visualTransformation = PasswordVisualTransformation()
            )
            if (state.passwordError != null) {
                Text(
                    text = state.passwordError,
                    color = md_theme_dark_error,
                    modifier = Modifier.align(Alignment.End)
                )
            }
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                TextButton(onClick = {}) {
                    Text(
                        text = "Lupa Password?",
                        color = Color.Blue,
                        textDecoration = TextDecoration.Underline
                    )
                }
            }
            FullSizeButton(
                text = "Masuk",
                onClick = {
                          navController.navigate(Screen.OrgGenerateQR.route)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .padding(horizontal = 60.dp),
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VolunteeLoginScreen(
    viewModel: LoginViewModel,
    signInWithGoogleClick: () -> Unit,
    onClickRegister: (Int) -> Unit,
    context: Context
) {
    val state = viewModel.state
    val passWordVisible by viewModel.passwordVisible.collectAsState()

    LaunchedEffect(key1 = context) {
        viewModel.validationEvents.collect { event ->
            when (event) {
                is LoginViewModel.ValidationEvent.Succes -> {
                    Toast.makeText(
                        context,
                        "Registration successful",
                        Toast.LENGTH_LONG
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
                viewModel.onEvent(LoginFormEvent.EmailChanged(it))
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
                text = state.emailError,
                color = md_theme_dark_error,
                modifier = Modifier.align(Alignment.End)
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
        OutlinedTextField(
            value = state.password,
            onValueChange = {
                viewModel.onEvent(LoginFormEvent.PasswordChanged(it))
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
                val image = if (passWordVisible)
                    Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff

                val description = if (passWordVisible) "Hide password" else "Show password"

                IconButton(onClick = { viewModel.togglePasswordVisibility() }) {
                    Icon(imageVector = image, description)
                }
            }
        )
        if (state.passwordError != null) {
            Text(
                text = state.passwordError,
                color = md_theme_dark_error,
                modifier = Modifier.align(Alignment.End)
            )
        }
        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.fillMaxWidth()
        ) {
            TextButton(onClick = {}) {
                Text(
                    text = "Lupa Password?",
                    color = md_theme_dark_primary,
                    textDecoration = TextDecoration.Underline
                )
            }
        }
        FullSizeButton(
            text = "Masuk",
            onClick = {
                viewModel.onEvent(LoginFormEvent.Submit)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .padding(horizontal = 30.dp),
        )
        Spacer(modifier = Modifier.height(20.dp))
        FullSizeButton(
            text = "Masuk Dengan Google",
            onClick = signInWithGoogleClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .padding(horizontal = 30.dp),
            icon = R.drawable.ic_google,
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        )
        {
            Text(text = "Belum Punya Akun ?", style = Typography.labelMedium, color = Color.Gray)
            ClickableText(
                text = AnnotatedString("Daftar"), style = Typography.labelMedium.plus(
                    TextStyle(
                        color = md_theme_dark_primary,
                        textDecoration = TextDecoration.Underline
                    )
                ), onClick = onClickRegister
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {

}
