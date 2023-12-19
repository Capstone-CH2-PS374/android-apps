package com.capstone_ch2_ps374.realone.presentation.screen.profilevolunteer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.capstone_ch2_ps374.realone.R
import com.capstone_ch2_ps374.realone.presentation.theme.Typography
import com.example.compose.md_theme_dark_primary

@Composable
fun ProfileVolunteerScreen(logoutOnClick:() -> Unit) {
    var selectedTabIndex by remember { mutableStateOf(0) }
    Column(

    )
    {
        Spacer(modifier = Modifier.height(50.dp))
        Row(
            modifier = Modifier.padding(start = 30.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile_palceholder),
                contentDescription = "profile",
                modifier = Modifier.clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text(text = "Reyhan", style = Typography.bodyLarge)
                Row {
                    ClickableText(
                        text = AnnotatedString("Edit Profile"),
                        onClick = {},
                        style = Typography.titleSmall.plus(
                            TextStyle(
                                color = Color(0xFF3884DD)
                            )
                        )
                    )
                    Icon(
                        imageVector = Icons.Filled.ArrowForwardIos,
                        contentDescription = "arrow",
                        tint = Color(0xFF3884DD)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(35.dp))
        Row (
            Modifier
                .padding(horizontal = 30.dp)
                .clickable(
                    onClick = logoutOnClick
                )
        ) {
            Icon(imageVector = Icons.Filled.Logout, contentDescription = "logout")
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = "Logout", style = Typography.titleMedium, color = Color(0xFF3884DD))
        }
        Spacer(modifier = Modifier.height(35.dp))
        TabRow(
            selectedTabIndex = selectedTabIndex,
            contentColor = Color.Black,
        ) {
            Tab(
                selected = selectedTabIndex == 0,
                onClick = { selectedTabIndex = 0 },
                text = {
                    Text(
                        text = "Partisipan", style = Typography.titleMedium
                    )
                },
                modifier = Modifier.background(color = if (selectedTabIndex == 0) md_theme_dark_primary else Color.White)
            )
            Tab(
                selected = selectedTabIndex == 1,
                onClick = { selectedTabIndex = 1 },
                text = {
                    Text(
                        text = "Event", style = Typography.titleMedium
                    )
                },
                modifier = Modifier.background(color = if (selectedTabIndex == 1) md_theme_dark_primary else Color.White)
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun ProfileVolunteerPreview() {
    ProfileVolunteerScreen(
        {  }
    )
}