package com.capstone_ch2_ps374.realone.presentation.screen.homevolunteer

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class HomeVolunteerViewModel: ViewModel() {
    val searchInput by mutableStateOf("")
}