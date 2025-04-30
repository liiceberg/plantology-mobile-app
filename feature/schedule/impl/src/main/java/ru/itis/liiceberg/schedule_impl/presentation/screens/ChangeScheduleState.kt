package ru.itis.liiceberg.schedule_impl.presentation.screens

import androidx.compose.runtime.Immutable
import ru.itis.liiceberg.schedule_api.presentation.SchedulePlantUiModel
import ru.itis.liiceberg.ui.model.LoadState
import ru.itis.liiceberg.ui.model.UiState

@Immutable
data class ChangeScheduleState(
    val plant: SchedulePlantUiModel = SchedulePlantUiModel.empty(),
    val loadingState: LoadState = LoadState.Initial,
) : UiState
