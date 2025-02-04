package ru.itis.liiceberg.myplants_impl.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ru.itis.liiceberg.myplants_impl.model.MyPlant
import ru.itis.liiceberg.myplants_impl.R
import ru.itis.liiceberg.ui.R as R_UI
import ru.itis.liiceberg.ui.components.BodyMediumText
import ru.itis.liiceberg.ui.components.DarkTopAppBar
import ru.itis.liiceberg.ui.components.ErrorMediumText
import ru.itis.liiceberg.ui.components.RoundedImage
import ru.itis.liiceberg.ui.components.SimpleIconButton
import ru.itis.liiceberg.ui.components.SmallCard
import ru.itis.liiceberg.ui.components.TitleMediumText
import ru.itis.liiceberg.ui.theme.AppTheme

@Composable
fun MyPlantsView(
    viewModel: MyPlantsViewModel = hiltViewModel(),
    goToSettings: () -> Unit,
) {
    val state by viewModel.viewStates().collectAsStateWithLifecycle()

    MyPlantsView(
        state.myPlants,
        goToSettings
    )

    LaunchedEffect(Unit) {
        viewModel.viewActions().collect { action ->
            when (action) {

                else -> {}
            }
        }
    }
}

@Composable
private fun MyPlantsView(
    list: List<MyPlant>,
    goToSettings: () -> Unit,
) {
    Scaffold(
        topBar = {
            DarkTopAppBar(
                title = stringResource(R.string.my_plants_top_bar_text),
                action = {
                    SimpleIconButton(
                        icon = painterResource(id = R_UI.drawable.settings),
                        size = 24.dp,
                        tint = MaterialTheme.colorScheme.onPrimary,
                        onClick = goToSettings
                    )
                }
            )
        },
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.surface)
        ) {
            items(list) {
                PlantItem(it.name, it.scientificName, it.image)
            }
        }
    }
}

@Composable
private fun PlantItem(name: String, scientificName: String, image: String) {
    var menuExpanded by remember { mutableStateOf(false) }
    Card(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(
                modifier = Modifier.padding(bottom = 10.dp),
            ) {
                RoundedImage(url = image, modifier = Modifier.weight(1f))
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(vertical = 8.dp)
                        .padding(start = 10.dp)
                ) {
                    TitleMediumText(text = name, modifier = Modifier.padding(bottom = 8.dp))
                    BodyMediumText(text = scientificName)
                }
                Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.TopEnd) {
                    SimpleIconButton(
                        icon = Icons.Default.MoreVert,
                        size = 24.dp,
                    ) {
                        menuExpanded = menuExpanded.not()
                    }
                    DropdownMenu(
                        expanded = menuExpanded,
                        onDismissRequest = { menuExpanded = false },
                    ) {
                        DropdownMenuItem(
                            text = {
                                ErrorMediumText(text = stringResource(R.string.remove_plant))
                            },
                            onClick = { /* TODO */ },
                        )
                    }
                }
            }
            Row {
                SmallCard(
                    title = "Water",
                    icon = painterResource(id = R_UI.drawable.water_drops),
                    text = "Water in ${4} days",
                    modifier = Modifier.padding(12.dp)
                )
                SmallCard(
                    title = "Fertilizer",
                    icon = painterResource(R_UI.drawable.fertilizer),
                    text = "in ${5} week",
                    modifier = Modifier.padding(12.dp)
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun MyPlantsPreview() {
    AppTheme {
        Column {
            MyPlantsView(
                listOf(
                    MyPlant(
                        "Wild mint",
                        "Mentha arvensis",
                        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcREHjj0QVmfJLo5BrdEKQZ5td36QsOqjgTQFg&s",
                        "",
                        4,
                        6
                    )
                )
            ) {}
        }
    }
}