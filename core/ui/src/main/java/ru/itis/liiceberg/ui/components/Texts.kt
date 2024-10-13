package ru.itis.liiceberg.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import ru.itis.liiceberg.ui.theme.AppTheme

@Composable
fun TitleText(text: String, modifier: Modifier = Modifier) {
    Text(text = text, modifier = modifier, style = MaterialTheme.typography.titleLarge)
}

@Composable
fun BodyText(text: String, modifier: Modifier = Modifier) {
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
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.onBackground,
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewText() {
    AppTheme {
        Column(Modifier.fillMaxSize()) {
            TitleText("example 2")
            BodyText("example")
            BodyTextWithLink("text", "link") {}
        }
    }
}