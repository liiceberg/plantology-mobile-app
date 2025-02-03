package ru.itis.liiceberg.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePainter.State
import ru.itis.liiceberg.ui.R


@Composable
fun RoundedImage(url: String, modifier: Modifier = Modifier, onSuccess: ((State.Success) -> Unit)? = null) {
    SimpleImage(url = url, modifier = modifier.clip(RoundedCornerShape(12.dp)), onSuccess)
}

@Composable
fun SimpleImage(url: String, modifier: Modifier = Modifier, onSuccess: ((State.Success) -> Unit)? = null) {
    AsyncImage(
        model = url,
        contentDescription = null,
        modifier = modifier.aspectRatio(1f),
        contentScale = ContentScale.Crop,
        error = painterResource(id = R.drawable.error_image),
        onSuccess = onSuccess
    )
}

@Preview(showBackground = false)
@Composable
fun PreviewImages() {
    Column {
        RoundedImage(
            url = "",
        )
        SimpleImage(
            url = "",
        )
    }
}