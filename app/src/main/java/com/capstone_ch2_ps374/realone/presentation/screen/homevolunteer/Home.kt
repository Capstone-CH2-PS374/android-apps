package com.capstone_ch2_ps374.realone.presentation.screen.homevolunteer

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.capstone_ch2_ps374.realone.R
import com.capstone_ch2_ps374.realone.presentation.component.CardEvent
import com.capstone_ch2_ps374.realone.presentation.screen.login.UserData
import com.capstone_ch2_ps374.realone.presentation.theme.Typography
import com.example.compose.md_theme_dark_primary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeVolunteerScreen(
    userData: UserData,
    viewModel: HomeVolunteerViewModel,
    navigateTodetail: (String) -> Unit,
    onSearchValueChanged: (String) -> Unit = {}
) {
    Column(
        modifier = Modifier.padding(start = 30.dp, top = 50.dp, end = 30.dp)
    ) {
        Row {
            Image(
                painter = painterResource(id = R.drawable.ic_hello),
                contentDescription = "hello_icon"
            )
            Spacer(modifier = Modifier.width(20.dp))
            Text(text = "Hi, ${userData.userId}", style = Typography.titleSmall)
        }
        Spacer(modifier = Modifier.height(15.dp))
        OutlinedTextField(
            value = viewModel.searchInput,
            onValueChange = {
            },
            modifier = Modifier
                .fillMaxWidth()
                .border(2.dp, Color.White, RoundedCornerShape(CornerSize(30.dp)))
                .shadow(elevation = 5.dp, RoundedCornerShape(CornerSize(30.dp))),
            placeholder = {
                Text(text = "Search")
            },
            trailingIcon = {
                Icon(imageVector = Icons.Outlined.Search, contentDescription = "Search Icon", tint= Color.White)
            },
            shape = RoundedCornerShape(CornerSize(30.dp)),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = md_theme_dark_primary,
                textColor = Color.White,
                placeholderColor = Color.White,
            )
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Rekomendasi untukmu", style = Typography.titleMedium, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(20.dp))
        Column{
            for (i in 1..3){
                CardEvent(
                    eventName = "Sinergi apalah",
                    image = R.drawable.poster_1,
                    navigateToDetail = {
                        navigateTodetail(it)
                    }
                )
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}