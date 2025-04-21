package ru.itis.liiceberg.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.itis.liiceberg.ui.theme.PlantologyTheme

@Composable
fun PrimaryTabs(
    selectedItemIndex: Int,
    tabItems: List<String>,
    modifier: Modifier = Modifier,
    onTabSelected: (index: Int) -> Unit,
) {
    Row(
        modifier
            .fillMaxWidth()
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.surface),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        tabItems.forEachIndexed { index, name ->
            PrimaryTabItem(
                name, index == selectedItemIndex, Modifier
                    .padding(5.dp)
                    .weight(1f)
            ) {
                onTabSelected(index)
            }
        }

    }
}

@Composable
private fun PrimaryTabItem(
    text: String,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val containerColor = if (isSelected) MaterialTheme.colorScheme.primary else Color.Unspecified
    val textColor =
        if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSecondary
    TitleSmallText(
        text = text,
        modifier = modifier
            .clip(CircleShape)
            .background(containerColor)
            .clickable {
                onClick()
            }
            .padding(
                vertical = 10.dp,
            ),
        color = textColor,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleTabs(
    selectedItemIndex: Int,
    tabItems: Array<String>,
    modifier: Modifier = Modifier,
    onTabSelected: (index: Int) -> Unit,
) {
    SecondaryTabRow(
        selectedTabIndex = selectedItemIndex,
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.secondary,
    ) {
        tabItems.forEachIndexed { index, name ->
            val color =
                if (selectedItemIndex == index) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground
            Tab(
                selectedItemIndex == index,
                onClick = { onTabSelected(index) },
                text = { BodySmallText(text = name, color = color) })
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TabsPreview() {
    PlantologyTheme {
        Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(16.dp)) {
            var selectedTab by remember { mutableIntStateOf(0) }
            PrimaryTabs(selectedTab, listOf("First", "Second", "Third")) {
                selectedTab = it
            }
            SimpleTabs(selectedTab, arrayOf("First", "Second", "Third")) {
                selectedTab = it
            }
        }
    }
}