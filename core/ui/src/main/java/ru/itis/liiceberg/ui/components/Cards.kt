package ru.itis.liiceberg.ui.components

import android.graphics.Bitmap
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.toBitmap
import ru.itis.liiceberg.ui.R
import ru.itis.liiceberg.ui.theme.PlantologyTheme

@Composable
fun SmallCard(
    modifier: Modifier = Modifier,
    title: String,
    icon: Painter,
    text: String
    ) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        DarkIcon(painter = icon, modifier = Modifier.padding(end = 16.dp))
        Column {
            TitleSmallText(text = title)
            BodySmallText(text = text)
        }
    }
}

@Composable
fun CardWithImageAndInfo(imageUrl: String, title: String, text: String) {
    var bitmap by remember { mutableStateOf<Bitmap?>(null) }
    var textColor by remember { mutableStateOf(Color.Black) }

    Box(modifier = Modifier.fillMaxSize()) {
        RoundedImage(
            url = imageUrl,
        ) { result ->
            val hardwareBitmap = result.result.image.toBitmap()
            bitmap = hardwareBitmap.copy(Bitmap.Config.ARGB_8888, true)
            bitmap?.let {
                val luminance = calculateAverageLuminance(it)
                textColor = if (luminance > 0.5) Color.Black else Color.White
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.33f)
                .align(Alignment.BottomCenter),
            contentAlignment = Alignment.CenterStart
        ) {
            RoundedImage(
                url = imageUrl, modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .blur(16.dp)
                    .graphicsLayer {
                        translationY = -this@graphicsLayer.size.height
                    }
            )

            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
            ) {
                Text(text = title, style = MaterialTheme.typography.titleSmall, color = textColor)
                Text(text = text, style = MaterialTheme.typography.bodySmall, color = textColor)
            }
        }
    }
}

private fun calculateAverageLuminance(bitmap: Bitmap): Float {
    var sumLuminance = 0f
    val width = bitmap.width
    val height = bitmap.height
    val pixels = IntArray(width * height)
    bitmap.getPixels(pixels, 0, width, 0, 0, width, height)

    for (pixel in pixels) {
        val red = Color(pixel).red
        val green = Color(pixel).green
        val blue = Color(pixel).blue
        sumLuminance += 0.2126f * red + 0.7152f * green + 0.0722f * blue
    }

    return sumLuminance / (width * height)

}


@Preview(showBackground = true)
@Composable
fun PreviewCard() {

    PlantologyTheme {
        Column {
            SmallCard(title = "title", icon = painterResource(id = R.drawable.globe), text = "description")
            }
        }
}
