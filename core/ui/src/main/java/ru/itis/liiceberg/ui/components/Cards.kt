package ru.itis.liiceberg.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.itis.liiceberg.ui.R
import ru.itis.liiceberg.ui.theme.AppTheme

@Composable
fun SmallCard(
    modifier: Modifier = Modifier,
    title: String,
    icon: Painter,
    text: String
    ) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        SimpleIcon(painter = icon, size = 32.dp, modifier = Modifier.padding(end = 8.dp))
        Column {
            TitleSmallText(text = title)
            BodySmallText(text = text)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewCard() {

    AppTheme {
        Column {
            SmallCard(title = "title", icon = painterResource(id = R.drawable.app_icon_primary), text = "description")
            }
        }
}
