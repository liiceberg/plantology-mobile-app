package ru.itis.liiceberg.myplants_impl.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import ru.itis.liiceberg.myplants_api.presenatation.MyPlantUiModel
import ru.itis.liiceberg.myplants_impl.R
import ru.itis.liiceberg.ui.components.BodyMediumText
import ru.itis.liiceberg.ui.components.DarkTopAppBar
import ru.itis.liiceberg.ui.components.ErrorMediumText
import ru.itis.liiceberg.ui.components.ErrorView
import ru.itis.liiceberg.ui.components.LoadingIndicator
import ru.itis.liiceberg.ui.components.RoundedImage
import ru.itis.liiceberg.ui.components.SimpleButtonWithStartIcon
import ru.itis.liiceberg.ui.components.SimpleIconButton
import ru.itis.liiceberg.ui.components.SimpleOutlinedButtonWithStartIcon
import ru.itis.liiceberg.ui.components.SmallCard
import ru.itis.liiceberg.ui.components.TitleMediumText
import ru.itis.liiceberg.ui.model.LoadState
import ru.itis.liiceberg.ui.theme.PlantologyTheme
import ru.itis.liiceberg.ui.R as R_UI

@Composable
fun MyPlantsView(
    viewModel: MyPlantsViewModel = hiltViewModel(),
    goToSettings: () -> Unit,
    goToExplore: () -> Unit,
    goToEditSchedule: (String) -> Unit,
) {
    val state by viewModel.viewStates().collectAsStateWithLifecycle()

    MyPlantsView(
        state = state,
        goToSettings = goToSettings,
        onRemove = { viewModel.obtainEvent(MyPlantsEvent.RemovePlant(it)) },
        toExplore = goToExplore,
        goToEditSchedule = goToEditSchedule,
    )

    LaunchedEffect(Unit) {
        viewModel.obtainEvent(MyPlantsEvent.ScreenOpened)
    }

}

@Composable
private fun MyPlantsView(
    state: MyPlantsState,
    goToSettings: () -> Unit,
    onRemove: (String) -> Unit,
    toExplore: () -> Unit,
    goToEditSchedule: (String) -> Unit,
) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        when(state.loadState) {
            is LoadState.Initial, LoadState.Loading -> LoadingIndicator()
            else -> {
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
                    Column {
                        LazyColumn(
                            modifier = Modifier
                                .padding(innerPadding)
                                .background(MaterialTheme.colorScheme.surface)
                                .padding(top = 16.dp)
                        ) {
                            items(state.myPlants) {
                                PlantItem(
                                    name = it.name,
                                    scientificName = it.scientificName,
                                    image = it.image,
                                    watering = it.watering,
                                    fertilizer = it.fertilizer,
                                    onRemove = { onRemove(it.id) },
                                    onAddReminder = { goToEditSchedule(it.plantId) },
                                )
                            }
                        }
                        SimpleOutlinedButtonWithStartIcon(
                            text = stringResource(R.string.more_plants),
                            icon = painterResource(id = R_UI.drawable.plus),
                            modifier = Modifier
                                .padding(16.dp)
                                .padding(top = 20.dp),
                            onClick = toExplore,
                        )
                    }
                }
                if (state.loadState is LoadState.Error) {
                    ErrorView(errorText = state.loadState.message)
                }
            }
        }
    }
}

@Composable
private fun PlantItem(
    name: String,
    scientificName: String,
    image: String,
    watering: String?,
    fertilizer: String?,
    onRemove: () -> Unit,
    onAddReminder: () -> Unit,
) {
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
                            onClick = onRemove,
                        )
                        DropdownMenuItem(
                            text = {
                                BodyMediumText(text = stringResource(R.string.edit_schedule))
                            },
                            onClick = onAddReminder,
                        )
                    }
                }
            }
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp), horizontalArrangement = Arrangement.Start
            ) {
                if (watering != null || fertilizer != null) {
                    SmallCard(
                        title = stringResource(R_UI.string.water),
                        icon = painterResource(id = R_UI.drawable.water_drops),
                        text = watering ?: stringResource(R_UI.string.no_schedule),
                    )
                    SmallCard(
                        title = stringResource(R_UI.string.fertilizer),
                        icon = painterResource(R_UI.drawable.fertilizer),
                        text = fertilizer ?: stringResource(R_UI.string.no_schedule),
                        modifier = Modifier.padding(start = 32.dp)
                    )

                } else {
                    SimpleButtonWithStartIcon(
                        text = stringResource(R.string.add_reminder),
                        painter = painterResource(id = R_UI.drawable.alarm),
                        iconSize = 18.dp,
                        tint = MaterialTheme.colorScheme.onPrimary
                    ) { onAddReminder() }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun MyPlantsPreview() {
    PlantologyTheme {
        Column {
            MyPlantsView(
                MyPlantsState(
                    listOf(
                        MyPlantUiModel(
                            "",
                            "",
                            "Wild mint",
                            "Mentha arvensis",
                            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcREHjj0QVmfJLo5BrdEKQZ5td36QsOqjgTQFg&s",
                            "",
                            "",
                        )
                    )
                ), {}, {}, {}, {}
            )
        }
    }
}