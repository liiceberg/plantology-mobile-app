package ru.itis.liiceberg.myplants_impl.screens

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
import ru.itis.liiceberg.myplants_api.domain.model.MyPlant
import ru.itis.liiceberg.myplants_impl.R
import ru.itis.liiceberg.ui.components.BodyMediumText
import ru.itis.liiceberg.ui.components.DarkTopAppBar
import ru.itis.liiceberg.ui.components.ErrorMediumText
import ru.itis.liiceberg.ui.components.ErrorMessage
import ru.itis.liiceberg.ui.components.RoundedImage
import ru.itis.liiceberg.ui.components.SimpleButtonWithStartIcon
import ru.itis.liiceberg.ui.components.SimpleIconButton
import ru.itis.liiceberg.ui.components.SmallCard
import ru.itis.liiceberg.ui.components.TitleMediumText
import ru.itis.liiceberg.ui.theme.AppTheme
import ru.itis.liiceberg.ui.R as R_UI

@Composable
fun MyPlantsView(
    viewModel: MyPlantsViewModel = hiltViewModel(),
    goToSettings: () -> Unit,
) {
    val state by viewModel.viewStates().collectAsStateWithLifecycle()
    val error by viewModel.error().collectAsStateWithLifecycle()

    MyPlantsView(
        state = state,
        goToSettings = goToSettings,
        onRemove = { viewModel.obtainEvent(MyPlantsEvent.RemovePlant(it)) },
        error = error,
    )

    LaunchedEffect(Unit) {
        viewModel.init()

        viewModel.viewActions().collect { action ->
            when (action) {

                else -> {}
            }
        }
    }
}

@Composable
private fun MyPlantsView(
    state: MyPlantsState,
    goToSettings: () -> Unit,
    onRemove: (String) -> Unit,
    error: String?,
) {
    Box(Modifier.fillMaxSize()) {
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
                    .padding(top = 16.dp)
            ) {
                items(state.myPlants) {
                    PlantItem(
                        it.name,
                        it.scientificName,
                        it.image,
                        it.watering,
                        it.fertilizer
                    ) { onRemove.invoke(it.id) }
                }
            }
        }
        error?.let {
            ErrorMessage(errorText = it)
        }
    }
}

@Composable
private fun PlantItem(
    name: String,
    scientificName: String,
    image: String,
    watering: Int,
    fertilizer: Int,
    onRemove: () -> Unit
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
                    }
                }
            }
            Row(Modifier.fillMaxWidth().padding(top = 16.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                if (watering <= 0 || fertilizer <= 0) {
                    SimpleButtonWithStartIcon(
                        text = stringResource(R.string.add_reminder),
                        icon = painterResource(id = R_UI.drawable.alarm),
                        tint = MaterialTheme.colorScheme.onPrimary
                    ) {}
                } else {
                    SmallCard(
                        title = stringResource(R.string.water),
                        icon = painterResource(id = R_UI.drawable.water_drops),
                        text = stringResource(R.string.water_in_days, watering),
                        modifier = Modifier.padding(12.dp)
                    )
                    SmallCard(
                        title = stringResource(R.string.fertilizer),
                        icon = painterResource(R_UI.drawable.fertilizer),
                        text = stringResource(R.string.fertilizer_in_week, fertilizer),
                        modifier = Modifier.padding(12.dp)
                    )
                }
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
                MyPlantsState(
                    listOf(
                        MyPlant(
                            "",
                            "Wild mint",
                            "Mentha arvensis",
                            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcREHjj0QVmfJLo5BrdEKQZ5td36QsOqjgTQFg&s",
                            "",
                            1,
                            2,
                        )
                    )
                ), {}, {}, null
            )
        }
    }
}