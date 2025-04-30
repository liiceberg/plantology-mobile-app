package ru.itis.liiceberg.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.itis.liiceberg.ui.theme.Green
import ru.itis.liiceberg.ui.theme.PlantologyTheme

@Composable
fun SimpleSlider(
    value: Float,
    onValueChange: (Float) -> Unit,
    modifier: Modifier = Modifier,
    valueRange: ClosedFloatingPointRange<Float> = 0f..100f,
) {
    Column(modifier) {
        Slider(
            value = value,
            onValueChange = onValueChange,
            valueRange = valueRange,
            colors = SliderDefaults.colors().copy(inactiveTrackColor = Green)
        )
        BodySmallText(
            text = value.toInt().toString(),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SliderPreview() {
    PlantologyTheme {
        Column {
            SimpleSlider(100f, {})
        }
    }
}