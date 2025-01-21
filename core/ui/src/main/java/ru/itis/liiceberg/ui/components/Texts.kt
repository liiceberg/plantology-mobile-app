package ru.itis.liiceberg.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import ru.itis.liiceberg.ui.theme.AppTheme

@Composable
fun TitleLargeText(text: String, modifier: Modifier = Modifier) {
    Text(text = text, modifier = modifier, style = MaterialTheme.typography.titleLarge)
}

@Composable
fun TitleMediumText(text: String, modifier: Modifier = Modifier) {
    Text(text = text, modifier = modifier, style = MaterialTheme.typography.titleMedium)
}

@Composable
fun TitleSmallText(text: String, modifier: Modifier = Modifier) {
    Text(text = text, modifier = modifier, style = MaterialTheme.typography.titleSmall)
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
fun BodySmallText(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        modifier = modifier,
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.onBackground
    )
}

@Composable
fun BodyTextWithLink(text: String, link: String, modifier: Modifier = Modifier, onClick: () -> Unit) {
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
    AppTheme {
        Column(Modifier.fillMaxSize()) {
            TitleLargeText("example 2")
            TitleSmallText("example 2")
            BodyMediumText("example")
            BodyTextWithLink("text", "link") {}
        }
    }
}