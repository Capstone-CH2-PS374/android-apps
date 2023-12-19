package com.capstone_ch2_ps374.realone.presentation.screen.detaileventvolunteer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.capstone_ch2_ps374.realone.R
import com.capstone_ch2_ps374.realone.presentation.component.CustomIconButton
import com.capstone_ch2_ps374.realone.presentation.component.FullSizeButton
import com.capstone_ch2_ps374.realone.presentation.theme.Typography
import com.capstone_ch2_ps374.realone.util.fillWidthOfParent

@Composable
fun DetailEventVolunteerScreen(
    description: String = "Lorem Ipsum",
    navigateToForm: (String) -> Unit,
    viewModel: DetailEventVolunteerViewModel
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    Box {
        if (state.showConfirmDialog) {
            Dialog(onDismissRequest = {
                viewModel.showDialog(false)
            }) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .height(200.dp)
                        .fillMaxWidth()
                        .background(color = Color.White, shape = RoundedCornerShape(20.dp))
                ) {
                    Spacer(modifier = Modifier.height(50.dp))
                    Text(text = "Lanutkan Pendaftaran", style = Typography.titleMedium)
                    Spacer(modifier = Modifier.height(25.dp))
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 50.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.clickable{
                                viewModel.showDialog(false)
                            }
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_cancel),
                                contentDescription = null,
                                contentScale = ContentScale.Fit,
                                modifier = Modifier.size(30.dp)
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(text = "Tidak", style = Typography.titleMedium)
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.clickable {
                                viewModel.showDialog(false)
                                navigateToForm("2")
                            }
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_check),
                                contentDescription = null,
                                contentScale = ContentScale.Fit,
                                modifier = Modifier.size(30.dp)
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(text = "Iya", style = Typography.titleMedium)
                        }
                    }
                }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(50.dp))
            Image(
                painter = painterResource(id = R.drawable.poster_detail),
                contentDescription = "poster_detail",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            )
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = "Judul",
                style = Typography.titleLarge,
                color = Color(0xFF003B81),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "tanggal",
                style = Typography.titleSmall,
                color = Color.Gray,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(30.dp))
            Divider(
                thickness = 1.dp,
                color = Color.Gray,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillWidthOfParent(30.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Image(
                    painter = painterResource(id = R.drawable.ic_calendar),
                    contentDescription = "ic calendar"
                )
                Spacer(modifier = Modifier.width(15.dp))
                Text(text = "Tanggal", style = Typography.bodyMedium, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_loc),
                    contentDescription = "ic calendar"
                )
                Spacer(modifier = Modifier.width(15.dp))
                Text(text = "Offline", style = Typography.bodyMedium, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_majority),
                    contentDescription = "ic calendar"
                )
                Spacer(modifier = Modifier.width(15.dp))
                Text(text = "Kategori", style = Typography.bodyMedium, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_deadline),
                    contentDescription = "ic calendar"
                )
                Spacer(modifier = Modifier.width(15.dp))
                Text(text = "Periode", style = Typography.bodyMedium, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(10.dp))
            Divider(
                thickness = 1.dp,
                color = Color.Gray,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillWidthOfParent(30.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Deskripsi Kegiatan",
                style = Typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = description, style = Typography.bodyMedium, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(80.dp))
        }
        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 40.dp, vertical = 20.dp)
        ) {
            CustomIconButton(
                icon = Icons.Filled.Share
            )
            Spacer(modifier = Modifier.width(20.dp))
            CustomIconButton(
                icon = Icons.Filled.Bookmark
            )
            Spacer(modifier = Modifier.width(30.dp))
            FullSizeButton(
                text = "Daftar Kegiatan",
                modifier = Modifier.fillMaxWidth(),
                textColor = Color.Black,
                onClick = {
                    viewModel.showDialog(true)
                })
        }
    }
}

@Preview
@Composable
fun previewDetailEventV() {
}
