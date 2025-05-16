package ru.itis.liiceberg.reminder_impl.presentation.screens

import androidx.compose.runtime.Immutable
import ru.itis.liiceberg.reminder_api.presentation.model.TaskUiModel
import ru.itis.liiceberg.reminder_api.presentation.model.Time
import ru.itis.liiceberg.ui.model.LoadState
import ru.itis.liiceberg.ui.model.UiState

@Immutable
data class ReminderState(
    val tasks: Map<Time, List<TaskUiModel>> = emptyMap<Time, List<TaskUiModel>>(),
    val selectedTab: Int = 0,
    val loadState: LoadState = LoadState.Initial,
) : UiState


