package com.capstone_ch2_ps374.realone.ui.screen.home

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.compose.md_theme_dark_errorContainer
import com.example.compose.md_theme_dark_primary
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(): ViewModel() {
    var containerColor = MutableStateFlow(md_theme_dark_primary)

    fun changeContainerColor (color: Color) {
        containerColor.value = color
    }
}