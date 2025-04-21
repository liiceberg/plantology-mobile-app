package ru.itis.liiceberg.reminder_impl.presentation.screens

import androidx.compose.runtime.Immutable
import ru.itis.liiceberg.reminder_api.presentation.model.TaskUiModel
import ru.itis.liiceberg.ui.model.LoadState
import ru.itis.liiceberg.ui.model.UiState

@Immutable
data class ReminderState(
    val tasks: List<TaskUiModel> = emptyList(),
    val selectedTab: Int = 0,
    val loadState: LoadState = LoadState.Initial,
) : UiState


