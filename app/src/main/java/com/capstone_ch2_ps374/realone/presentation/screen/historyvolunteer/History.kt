package com.capstone_ch2_ps374.realone.presentation.screen.historyvolunteer

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.capstone_ch2_ps374.realone.R
import com.capstone_ch2_ps374.realone.presentation.component.CardEvent
import com.capstone_ch2_ps374.realone.presentation.theme.Typography

@Composable
fun HistoryVolunteer(
    userName: String = "Reyhan",
    certCount: String = "0",
    navigateToParticipatedEvent: (String) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp)
    ) {
        Spacer(modifier = Modifier.height(100.dp))
        Image(
            painter = painterResource(id = R.drawable.profile_palceholder),
            contentDescription = "profile",
            contentScale = ContentScale.FillHeight,
            modifier = Modifier.clip(CircleShape)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = userName, style = Typography.titleMedium)
        Spacer(modifier = Modifier.height(30.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .border(2.dp, Color.Black, RoundedCornerShape(CornerSize(10.dp))),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_certificate),
                contentDescription = "Certificate",
                modifier = Modifier.padding(horizontal = 15.dp, vertical = 10.dp)
            )
            Spacer(Modifier.width(10.dp))
            Column {
                Text(text = "Certificate", style = Typography.bodyMedium)
                Text(text = certCount, style = Typography.bodySmall)
            }
        }
        Spacer(modifier = Modifier.height(45.dp))
        Text(
            text = "Kegiatan aktif",
            style = Typography.titleSmall,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(20.dp))
        CardEvent(
            navigateToDetail = {
                navigateToParticipatedEvent(it)
            }
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Riwayat Partisipasi",
            style = Typography.titleSmall,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(20.dp))
        Column(
            modifier = Modifier.padding(horizontal = 10.dp)
        ) {
            for (i in 1..3) {
                CardEvent(
                    navigateToDetail = {

                    }
                )
                Spacer(modifier = Modifier.height(15.dp))
            }
        }
    }
}


@Preview
@Composable
fun PreviewHistory() {
    HistoryVolunteer(
        navigateToParticipatedEvent = {

        }
    )
}