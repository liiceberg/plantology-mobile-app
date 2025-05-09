package ru.itis.liiceberg.schedule_impl.presentation.screens

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.itis.liiceberg.common.exceptions.ExceptionHandlerDelegate
import ru.itis.liiceberg.common.exceptions.runCatching
import ru.itis.liiceberg.common.model.TimeValues
import ru.itis.liiceberg.schedule_impl.domain.usecase.EditScheduleUseCase
import ru.itis.liiceberg.schedule_impl.domain.usecase.GetPlantInfoUseCase
import ru.itis.liiceberg.schedule_impl.presentation.mapper.SchedulePlantUiModelMapper
import ru.itis.liiceberg.ui.base.BaseViewModel
import ru.itis.liiceberg.ui.model.LoadState
import javax.inject.Inject

@HiltViewModel
class ChangeScheduleViewModel @Inject constructor(
    private val editScheduleUseCase: EditScheduleUseCase,
    private val getPlantInfoUseCase: GetPlantInfoUseCase,
    private val exceptionHandlerDelegate: ExceptionHandlerDelegate,
    private val mapper: SchedulePlantUiModelMapper,
) : BaseViewModel<ChangeScheduleState, ChangeScheduleEvent, ChangeScheduleAction>(
    ChangeScheduleState()
) {

    override fun obtainEvent(event: ChangeScheduleEvent) {
        when (event) {
            is ChangeScheduleEvent.ScreenOpened -> loadInfo(event.plantId)
            is ChangeScheduleEvent.OnSave -> {
                with(viewState.plant) {
                    saveSchedule(id, event.watering, event.fertilizer)
                }
            }
        }
    }

    private fun saveSchedule(
        id: String,
        wateringPeriod: TimeValues?,
        fertilizerPeriod: TimeValues?
    ) {
        viewModelScope.launch {
            runCatching(exceptionHandlerDelegate) {
                viewState = viewState.copy(loadingState = LoadState.Loading)
                editScheduleUseCase.invoke(id, wateringPeriod, fertilizerPeriod)
            }.onSuccess {
                editSchedule(wateringPeriod, fertilizerPeriod)
                viewState = viewState.copy(loadingState = LoadState.Success)
            }.onFailure { ex ->
                ex.message?.let {
                    viewState = viewState.copy(loadingState = LoadState.Error(it))
                }
            }
        }
    }

    private fun editSchedule(
        wateringPeriod: TimeValues?,
        fertilizerPeriod: TimeValues?
    ) {
        with(viewState.plant) {
            val newWateringSchedule = wateringSchedule.copy(
                value = wateringPeriod,
                stringValue = mapper.getScheduleText(wateringPeriod)
            )
            val newFertilizerSchedule = fertilizerSchedule.copy(
                value = fertilizerPeriod,
                stringValue = mapper.getScheduleText(fertilizerPeriod)
            )
            viewState = viewState.copy(
                plant = this.copy(
                    wateringSchedule = newWateringSchedule,
                    fertilizerSchedule = newFertilizerSchedule
                )
            )
        }
    }

    private fun loadInfo(plantId: String) {
        viewModelScope.launch {
            runCatching(exceptionHandlerDelegate) {
                viewState = viewState.copy(loadingState = LoadState.Loading)
                getPlantInfoUseCase.invoke(plantId)
            }.onSuccess { model ->
                viewState = viewState.copy(
                    loadingState = LoadState.Success,
                    plant = mapper.mapSchedulePlantToSchedulePlantUiModel(model)
                )
            }.onFailure { ex ->
                ex.message?.let {
                    viewState = viewState.copy(loadingState = LoadState.Error(it))
                }
            }
        }
    }
}