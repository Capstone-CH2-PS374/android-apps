package com.capstone_ch2_ps374.realone.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.capstone_ch2_ps374.realone.R
import com.capstone_ch2_ps374.realone.ui.theme.Typography
import com.capstone_ch2_ps374.realone.ui.theme.padding
import com.example.compose.md_theme_dark_primary
import androidx.lifecycle.viewmodel.compose.viewModel
import com.capstone_ch2_ps374.realone.TextFieldType
import com.capstone_ch2_ps374.realone.ui.component.CustomTextField

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = viewModel()
) {
    var selectedTabIndex by remember { mutableStateOf(0) }

    Box(
        Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.login_background),
            contentDescription = "background-top"
        )
        Column(Modifier.padding(horizontal = padding)) {
            Spacer(Modifier.height(90.dp))
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


            when (selectedTabIndex) {
                0 -> VolunteeLoginScreen()
                1 -> OrganizationLoginScreen()
            }
        }
    }
}


@Composable
fun OrganizationLoginScreen() {

}

@Composable
fun VolunteeLoginScreen() {
    Column {
        CustomTextField(
            type = TextFieldType.EMAIL
        )
    }
}
