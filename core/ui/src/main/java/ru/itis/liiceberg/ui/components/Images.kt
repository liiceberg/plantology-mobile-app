package ru.itis.liiceberg.ui.components

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import ru.itis.liiceberg.ui.R


@Composable
fun SimpleImage(url: String, modifier: Modifier = Modifier) {
    AsyncImage(
        model = url,
        contentDescription = null,
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .aspectRatio(1f)
            .fillMaxSize(),
        contentScale = ContentScale.Crop,
        error = painterResource(id = R.drawable.app_icon_white)
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewImages() {
    SimpleImage(
        url = "https://s3.stc.all.kpcdn.net/family/wp-content/uploads/2024/05/photo-in-article-risunki-i-kartinki-dlja-srisovki-legkie-01-1024x1024-1.jpg",
        modifier = Modifier.fillMaxSize()
    )
}