package com.capstone_ch2_ps374.realone.presentation.screen.generateqrscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.capstone_ch2_ps374.realone.domain.usecase.rememberQrBitmapPainter

@Composable
fun GenerateQRScreen() {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()) {

        Image(
            painter = rememberQrBitmapPainter("https://dev.to"),
            contentDescription = "DEV Communit Code",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.size(300.dp),
        )

    }

}