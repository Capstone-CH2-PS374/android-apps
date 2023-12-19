package com.capstone_ch2_ps374.realone.presentation.screen.detaileventparticipatedvolunteer

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.capstone_ch2_ps374.realone.CardPresenceType
import com.capstone_ch2_ps374.realone.R
import com.capstone_ch2_ps374.realone.domain.usecase.generatePDF
import com.capstone_ch2_ps374.realone.presentation.component.CardPresence
import com.capstone_ch2_ps374.realone.presentation.theme.Typography
import java.io.File

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun DetailEventParticipatedVolunteerScreen(
    navigateToPresent: (String, String) -> Unit,
    context: Context
) {
    var hasStoragePermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            hasStoragePermission = isGranted
        }
    )

    LaunchedEffect(key1 = hasStoragePermission) {
        if (!hasStoragePermission) {
            launcher.launch(
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            launcher.launch(
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
        }
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "Absensi",
            style = Typography.displaySmall,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier
                .horizontalScroll(
                    rememberScrollState(rememberScrollState().maxValue)
                )
                .padding(30.dp)
        ) {
            CardPresence(type = CardPresenceType.LATE, date = "12", month = "Desember")
            Spacer(modifier = Modifier.width(20.dp))
            CardPresence(type = CardPresenceType.LATE, date = "12", month = "Desember")
            Spacer(modifier = Modifier.width(20.dp))
            CardPresence(type = CardPresenceType.LATE, date = "12", month = "Desember")
            Spacer(modifier = Modifier.width(20.dp))
            CardPresence(type = CardPresenceType.PRESENT, date = "12", month = "Desember")
            Spacer(modifier = Modifier.width(20.dp))
            CardPresence(
                type = CardPresenceType.NORMAL,
                date = "12",
                month = "Desember",
                navigateToPresence = {
                    navigateToPresent("2", it)
                })
        }
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "Selamat Anda Memenuhi Syarat Untuk Mendapat Sertifikat",
            style = Typography.titleMedium,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp)
        )
        Spacer(modifier = Modifier.height(30.dp))
        Button(onClick = {
            generatePDF(context)
        }) {
            Text("Download Sertifikat")
        }
    }
}

@Preview()
@Composable
fun DetailEventOnGoingVolunteerScreenPreview() {
}