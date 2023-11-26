package com.capstone_ch2_ps374.realone.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.capstone_ch2_ps374.realone.DetailEventCardType
import com.capstone_ch2_ps374.realone.R
import com.capstone_ch2_ps374.realone.ui.theme.Typography
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
                for(i in 1..5){
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

@Preview(showBackground = true)
@Composable
fun DetailEventCardPreview() {
    Column {
        DetailEventCard()
        Spacer(modifier = Modifier.height(20.dp))
        DetailEventCard(type = DetailEventCardType.PARTICIPANT)
    }
}