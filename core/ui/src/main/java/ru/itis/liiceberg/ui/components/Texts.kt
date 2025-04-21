package ru.itis.liiceberg.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import ru.itis.liiceberg.ui.theme.PlantologyTheme

@Composable
fun DisplayLargeText(text: String, modifier: Modifier = Modifier) {
    Text(text = text, modifier = modifier, style = MaterialTheme.typography.displayLarge)
}

@Composable
fun DisplayMediumText(text: String, modifier: Modifier = Modifier) {
    Text(text = text, modifier = modifier, style = MaterialTheme.typography.displayMedium)
}

@Composable
fun DisplaySmallText(text: String, modifier: Modifier = Modifier) {
    Text(text = text, modifier = modifier, style = MaterialTheme.typography.displaySmall)
}

@Composable
fun HeadlineLargeText(text: String, modifier: Modifier = Modifier) {
    Text(text = text, modifier = modifier, style = MaterialTheme.typography.headlineLarge)
}

@Composable
fun HeadlineMediumText(text: String, modifier: Modifier = Modifier) {
    Text(text = text, modifier = modifier, style = MaterialTheme.typography.headlineMedium)
}

@Composable
fun HeadlineSmallText(text: String, modifier: Modifier = Modifier) {
    Text(text = text, modifier = modifier, style = MaterialTheme.typography.headlineSmall)
}

@Composable
fun TitleLargeText(text: String, modifier: Modifier = Modifier) {
    Text(text = text, modifier = modifier, style = MaterialTheme.typography.titleLarge)
}

@Composable
fun TitleMediumText(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        modifier = modifier,
        style = MaterialTheme.typography.titleMedium,
        textAlign = TextAlign.Center
    )
}

@Composable
fun TitleSmallText(text: String, modifier: Modifier = Modifier, color: Color? = null) {
    Text(
        text = text,
        modifier = modifier,
        style = MaterialTheme.typography.titleSmall,
        color = color ?: Color.Unspecified,
        textAlign = TextAlign.Center,
    )
}

@Composable
fun BodyLargeText(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        modifier = modifier,
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onBackground
    )
}

@Composable
fun BodyMediumText(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        modifier = modifier,
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onBackground
    )
}

@Composable
fun BodySmallText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onBackground
) {
    Text(
        text = text,
        modifier = modifier,
        style = MaterialTheme.typography.bodySmall,
        color = color
    )
}

@Composable
fun BodyTextWithLink(
    text: String,
    link: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val annotatedString = buildAnnotatedString {
        append(text)
        append(" ")
        withLink(
            link = LinkAnnotation.Clickable(
                tag = "TAG",
                linkInteractionListener = {
                    onClick.invoke()
                },
            ),
        ) {
            withStyle(
                SpanStyle(
                    color = MaterialTheme.colorScheme.primary,
                    textDecoration = TextDecoration.Underline,
                )
            ) {
                append(link)
            }
        }
    }

    Text(
        text = annotatedString,
        modifier = modifier,
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onBackground,
    )
}


@Composable
fun KeyValueText(key: String, value: String, modifier: Modifier = Modifier) {

    val annotatedString = buildAnnotatedString {
        withStyle(
            SpanStyle(
                fontWeight = FontWeight.W600,
                fontSize = 14.sp,
            )
        ) {
            append(key)
            append(": ")
        }
        withStyle(
            SpanStyle(
                fontSize = 14.sp,
            )
        ) {
            append(value)
        }
    }

    Text(
        text = annotatedString,
        modifier = modifier,
    )
}


@Composable
fun ErrorMediumText(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        modifier = modifier,
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onError
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewText() {
    PlantologyTheme {
        Column(Modifier.fillMaxSize()) {
            TitleLargeText("example 2")
            TitleSmallText("example 2")
            BodyMediumText("example")
            BodyTextWithLink("text", "link") {}
            KeyValueText("Key", "value")
        }
    }
}