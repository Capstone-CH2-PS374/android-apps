package com.capstone_ch2_ps374.realone.presentation.screen.volunteelayout

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Groups
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.capstone_ch2_ps374.realone.navigation.Screen
import com.capstone_ch2_ps374.realone.presentation.screen.historyvolunteer.HistoryVolunteer
import com.capstone_ch2_ps374.realone.presentation.screen.homevolunteer.HomeVolunteerScreen
import com.capstone_ch2_ps374.realone.presentation.screen.login.UserData
import com.capstone_ch2_ps374.realone.presentation.screen.profilevolunteer.ProfileVolunteerScreen

data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean,
    val badgeCount: Int? = null
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VolunteerLayout(signedInUser: UserData?, logoutOnClick: () -> Unit, mainNavHostController: NavHostController) {
    val navHostController: NavHostController = rememberNavController()
    val items = listOf(
        BottomNavigationItem(
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            hasNews = false,
        ),
        BottomNavigationItem(
            title = "Event",
            selectedIcon = Icons.Filled.Groups,
            unselectedIcon = Icons.Outlined.Groups,
            hasNews = false,
            badgeCount = 45
        ),
        BottomNavigationItem(
            title = "Profile",
            selectedIcon = Icons.Filled.Person,
            unselectedIcon = Icons.Outlined.Person,
            hasNews = true,
        ),
    )
    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }
    Scaffold(
        bottomBar = {
            NavigationBar {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedItemIndex == index,
                        onClick = {
                            selectedItemIndex = index
                            navHostController.navigate(item.title)
                        },
                        alwaysShowLabel = false,
                        icon = {
                            Icon(
                                imageVector = if (index == selectedItemIndex) {
                                    item.selectedIcon
                                } else item.unselectedIcon,
                                contentDescription = item.title
                            )
                        }
                    )
                }
            }
        }
    ) {
        Box(
            modifier = Modifier.padding(it)
        ) {
            NavHost(navController = navHostController, startDestination = "Home") {
                composable("Home") {
                    HomeVolunteerScreen(userData = signedInUser!!, viewModel = hiltViewModel(), navigateTodetail = {
                        mainNavHostController.navigate(Screen.VolunteerDetailEvent.createRoute(it))
                    })
                }
                composable("Event") {
                    HistoryVolunteer(
                        navigateToParticipatedEvent = {
                            mainNavHostController.navigate(
                                Screen.VolunteerDetailEvent.createRoute(
                                    "2"
                                )
                            )
                        }
                    )
                }
                composable("Profile") {
                    ProfileVolunteerScreen(
                        logoutOnClick = logoutOnClick
                    )
                }
            }
        }
    }
}