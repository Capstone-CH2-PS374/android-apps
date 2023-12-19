package com.capstone_ch2_ps374.realone.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.capstone_ch2_ps374.realone.CardPresenceType
import com.capstone_ch2_ps374.realone.DetailEventCardType
import com.capstone_ch2_ps374.realone.R
import com.capstone_ch2_ps374.realone.presentation.theme.Typography
import com.example.compose.md_theme_dark_secondary


@Composable
fun DetailEventCard(
    type: DetailEventCardType = DetailEventCardType.ABOUT,
    modifier: Modifier = Modifier,
    participantCount: String = "15"
) {
    when (type) {
        DetailEventCardType.ABOUT -> {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 2.dp,
                        color = md_theme_dark_secondary,
                        shape = RoundedCornerShape(15.dp)
                    )
                    .padding(top = 10.dp, start = 10.dp, end = 10.dp)
                    .height(100.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Transparent,
                ),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = stringResource(R.string.about_event))
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_down),
                        contentDescription = "arrowDown",
                        tint = md_theme_dark_secondary
                    )
                }
            }
        }

        DetailEventCardType.PARTICIPANT -> {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 2.dp,
                        color = md_theme_dark_secondary,
                        shape = RoundedCornerShape(15.dp)
                    )
                    .padding(top = 10.dp, start = 10.dp, end = 10.dp)
                    .height(90.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Transparent,
                )
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row {
                        Text(text = stringResource(R.string.participant))
                        Spacer(modifier = Modifier.width(3.dp))
                        Text(text = participantCount, color = Color.Gray)
                    }
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_down),
                        contentDescription = "arrowDown",
                        tint = md_theme_dark_secondary
                    )
                }
                for (i in 1..5) {
                    ParticipantTile()
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    }


}

@Composable
fun ParticipantTile(
    participantName: String = "Udin"
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_person),
            contentDescription = "avatar_participant"
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(text = participantName, style = Typography.labelSmall, fontWeight = FontWeight.Light)
    }
}

@Composable
fun CardEvent(
    eventId: String = "",
    eventName: String = "Event Name",
    image: Int = R.drawable.poster_1,
    navigateToDetail: (String) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 2.dp,
                color = md_theme_dark_secondary,
                shape = RoundedCornerShape(15.dp)
            )
            .height(100.dp)
            .clickable {
                navigateToDetail(eventId)
            },
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent,
        ),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxHeight()
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = "avatar_participant",
                contentScale = ContentScale.FillHeight,
                modifier = Modifier.fillMaxHeight()
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = eventName, style = Typography.labelSmall, fontWeight = FontWeight.Light)
        }
    }
}

@Composable
fun CardPresence(
    type: CardPresenceType,
    date: String,
    month: String,
    navigateToPresence: (String) -> Unit = {}
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = if (type == CardPresenceType.NORMAL) Color.White else Color.Gray.copy(alpha = 0.5F)
        ),
        border = BorderStroke(width = 1.dp, color = Color.Black),
        modifier = Modifier.defaultMinSize(minWidth = 140.dp)
    ) {
        val text = when (type) {
            CardPresenceType.NORMAL -> "Absen"
            CardPresenceType.LATE -> "Tidak Hadir"
            CardPresenceType.PRESENT -> "Hadir"
        }

        val color = when (type) {
            CardPresenceType.NORMAL -> Color(0XFF3884DD)
            CardPresenceType.LATE -> Color.Red
            CardPresenceType.PRESENT -> Color.Green
        }

        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, end = 10.dp, start = 10.dp, bottom = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = date, style = Typography.displayMedium)
            Text(text = month, style = Typography.titleLarge)
            Button(
                onClick = {
                          navigateToPresence("1")
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = color,
                    disabledContainerColor = color,
                    disabledContentColor = Color.White
                ),
                enabled = type == CardPresenceType.NORMAL
            ) {
                Text(
                    text = text,
                    style = Typography.titleMedium,
                    modifier = Modifier.padding(horizontal = 10.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailEventCardPreview() {
    Column {
        CardPresence(CardPresenceType.LATE, "12", "Desember",)
        Spacer(modifier = Modifier.height(20.dp))
        CardPresence(CardPresenceType.NORMAL, "12", "Desember",)
        Spacer(modifier = Modifier.height(20.dp))
        CardPresence(CardPresenceType.PRESENT, "12", "Desember",)
    }
}