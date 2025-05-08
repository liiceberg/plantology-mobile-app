package ru.itis.liiceberg.reminder_impl.presentation.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ru.itis.liiceberg.common.model.TaskType
import ru.itis.liiceberg.reminder_api.presentation.model.TaskUiModel
import ru.itis.liiceberg.reminder_api.presentation.model.Time
import ru.itis.liiceberg.reminder_impl.R
import ru.itis.liiceberg.ui.components.BodyLargeText
import ru.itis.liiceberg.ui.components.BodySmallText
import ru.itis.liiceberg.ui.components.DarkTopAppBar
import ru.itis.liiceberg.ui.components.DisplaySmallText
import ru.itis.liiceberg.ui.components.ErrorView
import ru.itis.liiceberg.ui.components.HeadlineLargeText
import ru.itis.liiceberg.ui.components.LoadingIndicator
import ru.itis.liiceberg.ui.components.PrimaryTabs
import ru.itis.liiceberg.ui.components.RoundedImage
import ru.itis.liiceberg.ui.components.SimpleButtonWithStartIcon
import ru.itis.liiceberg.ui.components.SimpleIcon
import ru.itis.liiceberg.ui.components.SimpleIconButton
import ru.itis.liiceberg.ui.components.TitleMediumText
import ru.itis.liiceberg.ui.model.LoadState
import ru.itis.liiceberg.ui.theme.Destructive600
import ru.itis.liiceberg.ui.theme.Green
import ru.itis.liiceberg.ui.theme.Neutral200
import ru.itis.liiceberg.ui.theme.Neutral400
import ru.itis.liiceberg.ui.theme.PlantologyTheme
import ru.itis.liiceberg.ui.theme.Primary600
import ru.itis.liiceberg.ui.theme.Warning500
import ru.itis.liiceberg.ui.theme.WaterBlue
import ru.itis.liiceberg.ui.R as R_UI

@Composable
fun ReminderView(
    viewModel: ReminderViewModel = hiltViewModel(),
    navigateToExplore: () -> Unit,
) {
    val state by viewModel.viewStates().collectAsStateWithLifecycle()
    ReminderView(
        state = state,
        onTabSelected = { id -> viewModel.obtainEvent(ReminderEvent.OnTabSelected(id)) },
        navigateToExplore = navigateToExplore
    )
}

@Composable
private fun ReminderView(
    state: ReminderState,
    navigateToExplore: () -> Unit,
    onTabSelected: (id: Int) -> Unit,
) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        when (state.loadState) {
            LoadState.Initial, LoadState.Loading -> LoadingIndicator()
            else -> {

                Scaffold(
                    topBar = {
                        DarkTopAppBar(
                            title = stringResource(R.string.reminder_top_bar_text),
                            action = {
                                SimpleIconButton(
                                    icon = painterResource(id = R_UI.drawable.notifications),
                                    size = 24.dp,
                                    tint = MaterialTheme.colorScheme.onPrimary,
                                    onClick = {}
                                )
                            }
                        )
                    },
                ) { innerPadding ->
                    Column(Modifier.padding(innerPadding)) {

                        if (state.loadState != LoadState.Initial && state.tasks.isEmpty()) {

                            DisplaySmallText(
                                stringResource(R.string.reminder_title),
                                modifier = Modifier.padding(
                                    top = 36.dp,
                                    start = 16.dp,
                                    bottom = 28.dp
                                )
                            )

                            PrimaryTabs(
                                selectedItemIndex = state.selectedTab,
                                tabItems = reminderTabs.map { stringResource(it) },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 20.dp)
                            ) {
                                onTabSelected(it)
                            }

                            when (reminderTabs[state.selectedTab]) {
                                R.string.tab_all -> {
                                    val todayTasks =
                                        state.tasks.indexOfFirst { it.time == Time.FUTURE }

                                    HeadlineLargeText(
                                        stringResource(R.string.tasks_today),
                                        Modifier.padding(top = 36.dp, start = 16.dp, bottom = 16.dp)
                                    )
                                    if (todayTasks + 1 > 0) {
                                        TasksList(state.tasks.subList(0, todayTasks + 1))
                                    }
                                    HeadlineLargeText(
                                        stringResource(R.string.tasks_upcoming),
                                        Modifier.padding(top = 36.dp, start = 16.dp, bottom = 16.dp)
                                    )
                                    if (todayTasks != -1) {
                                        TasksList(state.tasks.subList(todayTasks, state.tasks.size))
                                    }
                                }

                                R.string.tab_watering -> {
                                    HeadlineLargeText(
                                        stringResource(R.string.watering_tasks),
                                        Modifier.padding(top = 36.dp, start = 16.dp, bottom = 16.dp)
                                    )
                                    TasksList(state.tasks)
                                }

                                R.string.tab_fertilizer -> {
                                    HeadlineLargeText(
                                        stringResource(R.string.fertilizer_tasks),
                                        Modifier.padding(top = 36.dp, start = 16.dp, bottom = 16.dp)
                                    )
                                    TasksList(state.tasks)
                                }
                            }

                        } else {
                            EmptyScreen(navigateToExplore = navigateToExplore)
                        }
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
private fun TasksList(tasks: List<TaskUiModel>) {
    LazyColumn(
        Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .padding(horizontal = 16.dp, vertical = 20.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(tasks.size) { idx ->
            Task(tasks[idx]) {}
        }
    }
}

@Composable
private fun Task(
    task: TaskUiModel, onClick: () -> Unit
) {

    with(task) {
        val background = if (completed) MaterialTheme.colorScheme.surface else Color.Unspecified
        val borderColor = when (time) {
            Time.PAST -> Destructive600
            Time.PRESENT -> Primary600
            Time.FUTURE -> Neutral200
        }
        val shape = RoundedCornerShape(12.dp)
        Row(
            Modifier
                .fillMaxWidth()
                .height(102.dp)
                .clip(shape)
                .background(background)
                .border(2.dp, borderColor, shape)
                .clickable { onClick() },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        )
        {
            Row {
                RoundedImage(imageUrl, Modifier)
                Column(
                    Modifier
                        .padding(vertical = 14.dp, horizontal = 14.dp)
                        .fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly,
                ) {
                    BodyLargeText(plantName)
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(2.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val icon = when (time) {
                            Time.PAST -> R.drawable.assignment_late
                            Time.PRESENT -> R.drawable.today
                            Time.FUTURE -> R.drawable.event_upcoming
                        }
                        val textColor = when (time) {
                            Time.PAST -> Destructive600
                            Time.PRESENT -> Primary600
                            Time.FUTURE -> Neutral400
                        }
                        SimpleIcon(painterResource(icon), 24.dp)
                        BodySmallText(dateText, color = textColor)
                    }
                }
            }
            Box(Modifier.padding(end = 20.dp), Alignment.Center) {
                TaskIconButton(task) { }
            }
        }
    }
}

@Composable
private fun TaskIconButton(task: TaskUiModel, onClick: () -> Unit) {

    if (task.completed) {
        IconButton(onClick) {
            SimpleIcon(painter = painterResource(R.drawable.done), size = 52.dp)
        }
    } else {
        @DrawableRes val icon: Int
        val iconColor: Color
        when (task.type) {
            TaskType.WATER -> {
                icon = R_UI.drawable.water_drops
                iconColor = WaterBlue
            }

            else -> {
                icon = R_UI.drawable.fertilizer
                iconColor = Warning500
            }

        }
        val background: Color
        val borderColor: Color
        when (task.time) {
            Time.PAST -> {
                background = Green
                borderColor = Destructive600
            }

            Time.PRESENT -> {
                background = Green
                borderColor = Primary600
            }

            Time.FUTURE -> {
                background = Neutral200
                borderColor = Neutral400
            }
        }
        IconButton(
            onClick = onClick, Modifier
                .size(52.dp)
                .clip(CircleShape)
                .background(background)
                .border(BorderStroke(2.dp, borderColor), CircleShape)
        ) {
            SimpleIcon(
                painter = painterResource(icon),
                size = 24.dp,
                tint = iconColor,
            )
        }
    }
}


@Composable
private fun EmptyScreen(navigateToExplore: () -> Unit) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(R.drawable.plant_illustration),
            contentDescription = null,
            modifier = Modifier
                .size(height = 255.dp, width = 200.dp)
                .padding(bottom = 28.dp)
        )
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DisplaySmallText(stringResource(R.string.get_started_title))
            TitleMediumText(stringResource(R.string.reminder_empty_screen_description))
        }
        SimpleButtonWithStartIcon(
            text = stringResource(R.string.add_plant_button),
            painter = painterResource(id = R_UI.drawable.plus),
            iconSize = 18.dp,
            modifier = Modifier.padding(top = 24.dp),
            tint = MaterialTheme.colorScheme.secondary,
            onClick = navigateToExplore,
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun ReminderPreview() {
    PlantologyTheme {
        ReminderView(ReminderState(), {}) { }
    }
}