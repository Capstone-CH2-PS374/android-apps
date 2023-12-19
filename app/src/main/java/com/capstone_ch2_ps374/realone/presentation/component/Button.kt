package com.capstone_ch2_ps374.realone.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.md_theme_dark_primary

@Composable
fun FullSizeButton(
    text: String = "Daftar Kegiatan",
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier,
    icon: Int? = null,
    enabled: Boolean = true,
    textColor: Color = Color.White,
) {
    FilledIconButton(
        onClick = onClick,
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = md_theme_dark_primary
        ),
        enabled = enabled,
        modifier = modifier
    ) {
        Box() {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp)
            ) {
                if (icon != null) {
                    Image(
                        painter = painterResource(id = icon),
                        contentDescription = null,
                        modifier = Modifier.padding(vertical = 5.dp)
                    )
                }
                Text(
                    text = text,
                    color = textColor,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun CustomIconButton(
    icon: ImageVector = Icons.Filled.Person,
    backgroundColor: Color = md_theme_dark_primary,
    onClick: () -> Unit = {}
) {
    FilledIconButton(
        onClick = onClick,
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = backgroundColor
        ),
        shape = RoundedCornerShape(10.dp)
    )
    {
        Icon(imageVector = icon, contentDescription = "icon_button")
    }
}


@Preview(showBackground = true)
@Composable
fun ButtonPreview() {
    Column {
        Spacer(modifier = Modifier.height(20.dp))
        CustomIconButton()
    }
}