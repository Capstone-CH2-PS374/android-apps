package com.capstone_ch2_ps374.realone.presentation.screen.organizationhome

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.capstone_ch2_ps374.realone.presentation.component.CardEvent
import com.capstone_ch2_ps374.realone.presentation.theme.Typography

@Composable
fun OrganizationHomeScreen() {

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.padding(horizontal = 30.dp)
        ) {
            Spacer(modifier = Modifier.height(100.dp))
            Text(text = "Acara-mu", style = Typography.titleMedium, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(20.dp))
            CardEvent(navigateToDetail = {

            })
        }
        Box(modifier = Modifier.fillMaxSize().padding(30.dp),
            contentAlignment = Alignment.BottomEnd) {
            FloatingActionButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = null)
            }
        }
    }
}

@Preview
@Composable
fun OrganizationHomeScreenPreview() {
    OrganizationHomeScreen()
}