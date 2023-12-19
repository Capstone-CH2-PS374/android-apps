package com.capstone_ch2_ps374.realone.presentation.screen.confirmeventform

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.capstone_ch2_ps374.realone.R
import com.capstone_ch2_ps374.realone.presentation.theme.Typography
import com.example.compose.md_theme_dark_primary
import com.example.compose.md_theme_dark_secondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfirmEventScreen(popNavigateBack: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp)
    ) {
        Spacer(modifier = Modifier.height(110.dp))
        Image(painter = painterResource(id = R.drawable.ic_accept), contentDescription = null)
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Daftar Sukses", style = Typography.bodyLarge, color = Color(0xFF30B42D))
        Spacer(modifier = Modifier.height(45.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = md_theme_dark_secondary,
                    shape = RoundedCornerShape(15.dp)
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp, vertical = 10.dp)
            ) {
                Text(
                    text = "Join Group",
                    style = Typography.bodyLarge,
                    color = Color(0xFF30B42D)
                )
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_whatsapp),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = "Grup wa", style = Typography.bodySmall, color = Color(0xFF003B81))
                }
            }
        }
    }
    Box(
        contentAlignment = Alignment.BottomCenter, modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
    ) {
        Button(
            onClick = popNavigateBack
            , modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = md_theme_dark_primary
            )
        ) {
            Text(text = "Kembali")
        }
    }
}

@Preview
@Composable
fun ConfirmEventPreview() {
}

