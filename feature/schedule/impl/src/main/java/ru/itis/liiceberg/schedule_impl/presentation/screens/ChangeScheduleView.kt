package ru.itis.liiceberg.schedule_impl.presentation.screens

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.launch
import ru.itis.liiceberg.common.model.TaskType
import ru.itis.liiceberg.common.model.TimeUnit
import ru.itis.liiceberg.common.model.TimeValues
import ru.itis.liiceberg.common.util.Constants
import ru.itis.liiceberg.schedule_api.presentation.ScheduleItem
import ru.itis.liiceberg.schedule_impl.R
import ru.itis.liiceberg.ui.components.BodyLargeText
import ru.itis.liiceberg.ui.components.DarkIcon
import ru.itis.liiceberg.ui.components.DarkTopAppBar
import ru.itis.liiceberg.ui.components.ErrorView
import ru.itis.liiceberg.ui.components.KeyValueText
import ru.itis.liiceberg.ui.components.LoadingIndicator
import ru.itis.liiceberg.ui.components.SimpleButton
import ru.itis.liiceberg.ui.components.SimpleImage
import ru.itis.liiceberg.ui.components.SimpleOutlinedButton
import ru.itis.liiceberg.ui.components.SimpleSlider
import ru.itis.liiceberg.ui.components.SimpleTabs
import ru.itis.liiceberg.ui.components.SmallCard
import ru.itis.liiceberg.ui.components.TitleLargeText
import ru.itis.liiceberg.ui.components.TitleSmallText
import ru.itis.liiceberg.ui.model.LoadState
import ru.itis.liiceberg.ui.theme.PlantologyTheme
import ru.itis.liiceberg.ui.R as R_UI


@Composable
fun ChangeScheduleView(
    viewModel: ChangeScheduleViewModel = hiltViewModel(),
    plantId: String,
    goBack: () -> Unit,
) {
    val state by viewModel.viewStates().collectAsStateWithLifecycle()

    LaunchedEffect(plantId) { viewModel.obtainEvent(ChangeScheduleEvent.ScreenOpened(plantId)) }

    ChangeScheduleView(
        state = state,
        goBack = goBack,
        saveResults = { watering, fertilizer ->
            viewModel.obtainEvent(ChangeScheduleEvent.OnSave(watering, fertilizer))
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ChangeScheduleView(
    state: ChangeScheduleState,
    goBack: () -> Unit,
    saveResults: (watering: TimeValues?, fertilizer: TimeValues?) -> Unit,
) {
    val scaffoldState = rememberBottomSheetScaffoldState()
    val scope = rememberCoroutineScope()

    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        when (state.loadingState) {
            LoadState.Initial, LoadState.Loading -> LoadingIndicator()
            else -> {
                with(state.plant) {
                    BottomSheetScaffold(
                        scaffoldState = scaffoldState,
                        sheetContent = {
                            ChangeScheduleSheetContent(
                                wateringSchedule = wateringSchedule,
                                fertilizerSchedule = fertilizerSchedule,
                                onSaveResults = { watering, fertilizer ->
                                    saveResults(watering, fertilizer)
                                    scope.launch { scaffoldState.bottomSheetState.partialExpand() }
                                }
                            )
                        },
                        topBar = {
                            DarkTopAppBar(
                                title = stringResource(R.string.schedule_top_bar_text),
                                onNavigate = goBack,
                            )
                        },
                        sheetPeekHeight = 0.dp,
                        sheetContainerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
                    ) { innerPadding ->

                        Column(
                            Modifier
                                .padding(innerPadding)
                                .padding(start = 16.dp, end = 16.dp, top = 40.dp),
                            verticalArrangement = Arrangement.spacedBy(24.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(74.dp)
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(MaterialTheme.colorScheme.surface)
                            ) {
                                SimpleImage(image)
                                Column(Modifier.padding(start = 14.dp, top = 12.dp, bottom = 12.dp)) {
                                    TitleLargeText(plantName)
                                    TitleSmallText(scientificName)
                                }
                            }
                            ScheduleInfo(wateringSchedule.stringValue, fertilizerSchedule.stringValue) {
                                scope.launch { scaffoldState.bottomSheetState.expand() }
                            }
                        }
                    }
                    if (state.loadingState is LoadState.Error) {
                        ErrorView(errorText = state.loadingState.message)
                    }
                }
            }
        }

    }
}

@Composable
fun ScheduleInfo(
    wateringPeriod: String,
    fertilizerPeriod: String,
    onClick: () -> Unit,
) {
    Column(
        Modifier
            .clip(RoundedCornerShape(14.dp))
            .background(MaterialTheme.colorScheme.surface)
            .padding(20.dp)
    ) {
        SmallCard(
            title = stringResource(R_UI.string.water),
            icon = painterResource(id = R_UI.drawable.water_drops),
            text = wateringPeriod,
        )
        SmallCard(
            title = stringResource(R_UI.string.fertilizer),
            icon = painterResource(R_UI.drawable.fertilizer),
            text = fertilizerPeriod,
            modifier = Modifier.padding(top = 14.dp, bottom = 28.dp)
        )
        SimpleOutlinedButton(stringResource(R.string.change_schedule_button)) { onClick() }
    }
}

@Composable
private fun ChangeScheduleSheetContent(
    wateringSchedule: ScheduleItem,
    fertilizerSchedule: ScheduleItem,
    onSaveResults: (watering: TimeValues?, fertilizer: TimeValues?) -> Unit,
) {
    var tempWateringSchedule by remember { mutableStateOf(wateringSchedule.value) }
    var tempFertilizerSchedule by remember { mutableStateOf(fertilizerSchedule.value) }
    val scrollState = rememberScrollState()

    Column(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .verticalScroll(state = scrollState)
    ) {
        Column(
            modifier = Modifier.padding(top = 48.dp, bottom = 24.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            KeyValueText(stringResource(R.string.suggest_water), wateringSchedule.description)
            KeyValueText(
                stringResource(R.string.suggest_fertilizer),
                fertilizerSchedule.description
            )
        }

        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            ScheduleItemView(tempWateringSchedule, TaskType.WATER) { newValue ->
                tempWateringSchedule = newValue
            }
            ScheduleItemView(tempFertilizerSchedule, TaskType.FERTILIZER) { newValue ->
                tempFertilizerSchedule = newValue
            }
        }

        SimpleButton(
            stringResource(R.string.done_button),
            Modifier.padding(horizontal = 4.dp, vertical = 48.dp)
        ) {
            onSaveResults(tempWateringSchedule, tempFertilizerSchedule)
        }
    }
}

@Composable
private fun ScheduleItemView(
    value: TimeValues?,
    type: TaskType,
    onValueChange: (TimeValues?) -> Unit
) {
    Card(
        colors = CardDefaults.cardColors()
            .copy(containerColor = MaterialTheme.colorScheme.surfaceContainerHigh),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    @DrawableRes val painterId: Int
                    @StringRes val stringId: Int
                    when (type) {
                        TaskType.WATER -> {
                            painterId = R_UI.drawable.water_drops
                            stringId = R_UI.string.water
                        }

                        else -> {
                            painterId = R_UI.drawable.fertilizer
                            stringId = R_UI.string.fertilizer
                        }
                    }
                    DarkIcon(painterResource(painterId))
                    BodyLargeText(stringResource(stringId))
                }
                Switch(
                    checked = value != null,
                    onCheckedChange = { checked ->
                        if (checked) {
                            val unit = value?.periodUnit ?: TimeUnit.DAYS
                            onValueChange(TimeValues(1, unit))
                        } else {
                            onValueChange(null)
                        }
                    })
            }

            if (value != null) {

                val currentTabIndex = tabs.indexOfFirst { it.unit == value.periodUnit }

                val maxRange = when (value.periodUnit) {
                    TimeUnit.DAYS -> Constants.MAX_DAYS
                    TimeUnit.WEEKS -> Constants.MAX_WEEKS
                    TimeUnit.MONTHS -> Constants.MAX_MONTH
                }.toFloat()

                SimpleTabs(
                    selectedItemIndex = currentTabIndex,
                    tabItems = tabs.map { stringResource(it.stringId) }
                ) { index ->
                    val newUnit = tabs[index].unit
                    onValueChange(TimeValues(1, newUnit))
                }

                SimpleSlider(
                    value = value.periodValue.toFloat(),
                    onValueChange = { newValue ->
                        onValueChange(TimeValues(newValue.toInt(), value.periodUnit))
                    },
                    valueRange = 1f..maxRange,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 32.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ChangeScheduleBottomSheetPreview() {
    PlantologyTheme {
        Column {
            ChangeScheduleView(ChangeScheduleState(), goBack = {}, saveResults = { _, _ ->})
        }
    }
}
