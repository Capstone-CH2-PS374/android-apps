package com.capstone_ch2_ps374.realone

import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.capstone_ch2_ps374.realone.api.UserResponse
import com.capstone_ch2_ps374.realone.navigation.Screen
import com.capstone_ch2_ps374.realone.presentation.screen.login.LoginScreen
import com.capstone_ch2_ps374.realone.presentation.screen.login.SignInResult
import com.capstone_ch2_ps374.realone.presentation.screen.login.SignInViewModel
import com.capstone_ch2_ps374.realone.presentation.screen.volunteelayout.VolunteerLayout
import com.capstone_ch2_ps374.realone.presentation.screen.volunteerregister.RegisterViewModel
import com.capstone_ch2_ps374.realone.presentation.screen.volunteerregister.VolunteerRegisterScreen
import com.capstone_ch2_ps374.realone.domain.usecase.GoogleAuthUiClient
import com.capstone_ch2_ps374.realone.presentation.screen.AddEvent.AddEventForm
import com.capstone_ch2_ps374.realone.presentation.screen.detaileventvolunteer.DetailEventVolunteerScreen
import com.capstone_ch2_ps374.realone.presentation.screen.confirmeventform.ConfirmEventScreen
import com.capstone_ch2_ps374.realone.presentation.screen.detaileventparticipatedvolunteer.DetailEventParticipatedVolunteerScreen
import com.capstone_ch2_ps374.realone.presentation.screen.generateqrscreen.GenerateQRScreen
import com.capstone_ch2_ps374.realone.presentation.screen.organizationhome.OrganizationHomeScreen
import com.capstone_ch2_ps374.realone.presentation.screen.volunteerdataform.VolunteerForm
import com.capstone_ch2_ps374.realone.presentation.screen.volunteerpresence.VolunteerPresence
import com.example.compose.RelaOneTheme
import com.google.android.gms.auth.api.identity.Identity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RelaOneTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp(
                        applicationContext = applicationContext,
                        googleAuthUiClient = googleAuthUiClient,
                        lifecycleScope = lifecycleScope
                    )
                }
            }
        }
    }
}

@Composable
fun MyApp(
    applicationContext: Context,
    navHostController: NavHostController = rememberNavController(),
    googleAuthUiClient: GoogleAuthUiClient,
    lifecycleScope: LifecycleCoroutineScope,
) {
    NavHost(navController = navHostController, startDestination = Screen.Login.route) {
        composable(Screen.Login.route) {
            val viewModel: SignInViewModel = hiltViewModel()
            val state by viewModel.state.collectAsStateWithLifecycle()

            LaunchedEffect(key1 = Unit, key2 = state.userDataApi) {

                if (googleAuthUiClient.getSignedInUser() != null) {
                    Log.d("TAG", "${googleAuthUiClient.getSignedInUser()}")
                    var userDataApi: UserResponse? = null
                    val userDataApiPro = async {
                        viewModel.fetchUserData(
                            googleAuthUiClient.getSignedInUser()!!.userId
                        )
                        if (state.userDataApi?.userId != null) {
                            if (state.userDataApi?.typeOrganization!!.isNotEmpty()) {
                                navHostController.navigate(Screen.OrganizationHome.route)
                            } else {
                                navHostController.navigate(Screen.VolunteerLayout.route)
                            }
                        }
                        if (state.userDataApi?.address == "error") {
                            navHostController.navigate(Screen.VolunteerForm.route)
                        }
                    }
                }
            }

            val launcher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.StartIntentSenderForResult(),
                onResult = { result ->
                    if (result.resultCode == RESULT_OK) {
                        lifecycleScope.launch {
                            val signInResult = googleAuthUiClient.signInWithIntent(
                                intent = result.data ?: return@launch
                            )
                            viewModel.onSignInResult(signInResult)
                        }
                    }
                    if (result.resultCode == RESULT_CANCELED) {
                        viewModel.onSignInResult(
                            SignInResult(
                                data = null,
                                errorMessage = "Login Diabatalakn"
                            )
                        )
                    }
                }
            )

            LaunchedEffect(key1 = state.isSignInSuccessful) {
                if (state.isSignInSuccessful) {
                    Toast.makeText(
                        applicationContext,
                        "login Berhasil",
                        Toast.LENGTH_LONG
                    ).show()
                    viewModel.registApi(googleAuthUiClient.getSignedInUser()!!.userId)
                    viewModel.fetchUserData(googleAuthUiClient.getSignedInUser()!!.userId)
                    viewModel.resetState()
                    navHostController.navigate("volunteer")
                }

                if (state.signInError != null) {
                    Toast.makeText(
                        applicationContext,
                        state.signInError,
                        Toast.LENGTH_LONG
                    ).show()
                    viewModel.resetState()
                }
            }

            LoginScreen(
                signInWithGoogleClick = {
                    viewModel.setIsLoading(true)
                    lifecycleScope.launch {
                        val signInIntentSender = googleAuthUiClient.signIn()
                        launcher.launch(
                            IntentSenderRequest.Builder(
                                signInIntentSender ?: return@launch
                            ).build()
                        )
                    }
                },
                onClickRegister = {
                    navHostController.navigate(Screen.VolunteerRegister.route)
                },
                isLoading = state.isLoading,
                context = applicationContext,
                navController = navHostController,
            )
        }
        composable(Screen.VolunteerLayout.route) {
//            googleAuthUiClient.getSignedInUser()
//                ?.let { it -> HomeScreen(userData = it, viewModel = hiltViewModel()) }
            VolunteerLayout(
                googleAuthUiClient.getSignedInUser(),
                logoutOnClick = {
                    lifecycleScope.launch {
                        googleAuthUiClient.signOut()
                        Toast.makeText(
                            applicationContext,
                            "Signed out",
                            Toast.LENGTH_LONG
                        ).show()

                        navHostController.popBackStack()
                    }
                },
                mainNavHostController = navHostController
            )
        }
        composable(Screen.VolunteerRegister.route) {
            val viewModel: RegisterViewModel = hiltViewModel()
            val state by viewModel.state.collectAsStateWithLifecycle()
            LaunchedEffect(key1 = state.isRegisterSuccessful) {
                if (state.isRegisterSuccessful) {
                    Toast.makeText(
                        applicationContext,
                        "Register successful",
                        Toast.LENGTH_LONG
                    ).show()
                    googleAuthUiClient.signIn()
                }
            }
            VolunteerRegisterScreen(
                viewModel = viewModel,
                context = applicationContext,
                navController = navHostController
            )
        }
        composable(route = Screen.VolunteerForm.route) {
            VolunteerForm(
                userId = googleAuthUiClient.getSignedInUser(),
                navController = navHostController,
            )
        }
        composable(
            route = Screen.VolunteerDetailEvent.route,
            arguments = listOf(navArgument("eventId") { type = NavType.StringType })
        ) {
            val id = it.arguments?.getString("eventId")
            if (id == "2") {
                DetailEventParticipatedVolunteerScreen(
                    context = applicationContext,
                    navigateToPresent = { eventId, presenceId ->
                        navHostController.navigate(
                            Screen.VolunteerParticipatePresence.createRoute(
                                eventId,
                                presenceId
                            )
                        )
                    },
                )
            } else {
                DetailEventVolunteerScreen(
                    id = id,
                    navigateToForm = {
                        navHostController.navigate(
                            Screen.VolunteerParticipateForm.createRoute(
                                it
                            )
                        )
                    },
                    viewModel = hiltViewModel()
                )
            }
        }
        composable(
            route = Screen.VolunteerParticipateForm.route,
            arguments = listOf(navArgument("eventId") { type = NavType.StringType })
        ) {
            val id = it.arguments?.getString("eventId")
            ConfirmEventScreen(
                popNavigateBack = {
                    navHostController.popBackStack()
                }
            )
        }
        composable(Screen.OrgGenerateQR.route) {
            GenerateQRScreen(

            )
        }
        composable(Screen.OrganizationHome.route) {
            OrganizationHomeScreen(
                navHostController,
                logout = {
                    lifecycleScope.launch {
                        googleAuthUiClient.signOut()
                        Toast.makeText(
                            applicationContext,
                            "Signed out",
                            Toast.LENGTH_LONG
                        ).show()

                        navHostController.popBackStack()
                    }
                }
            )
        }
        composable(
            route = Screen.VolunteerParticipatePresence.route,
            arguments = listOf(
                navArgument("eventId") { type = NavType.StringType },
                navArgument("presenceId") { type = NavType.StringType },
            )
        ) {
            val eventId = it.arguments?.getString("eventId")
            val presenceId = it.arguments?.getString("presenceId")
            VolunteerPresence(
            )
        }
        composable(Screen.AddEventForm.route) {
            AddEventForm()
        }
    }
}


