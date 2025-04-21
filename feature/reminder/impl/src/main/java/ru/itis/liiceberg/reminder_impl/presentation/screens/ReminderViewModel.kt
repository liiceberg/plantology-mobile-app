package ru.itis.liiceberg.reminder_impl.presentation.screens

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.itis.liiceberg.common.exceptions.ExceptionHandlerDelegate
import ru.itis.liiceberg.common.exceptions.runCatching
import ru.itis.liiceberg.common.model.TaskType
import ru.itis.liiceberg.reminder_api.presentation.model.TaskUiModel
import ru.itis.liiceberg.reminder_impl.R
import ru.itis.liiceberg.reminder_impl.domain.GetTasksUseCase
import ru.itis.liiceberg.reminder_impl.presentation.mapper.TaskModelMapper
import ru.itis.liiceberg.ui.base.BaseViewModel
import ru.itis.liiceberg.ui.model.LoadState
import javax.inject.Inject

@HiltViewModel
class ReminderViewModel @Inject constructor(
    private val getTasksUseCase: GetTasksUseCase,
    private val mapper: TaskModelMapper,
    private val exceptionHandler: ExceptionHandlerDelegate,
) : BaseViewModel<ReminderState, ReminderEvent, ReminderAction>(ReminderState()) {

    init {
        init()
    }

    private var allTasks = listOf<TaskUiModel>()

    override fun init() {
        getTasks()
    }

    override fun obtainEvent(event: ReminderEvent) {
        when (event) {
            is ReminderEvent.OnTabSelected -> {
                with(event.index) {
                    viewState = viewState.copy(selectedTab = this, tasks = filterTasks(this))
                }
            }
        }
    }

    private fun getTasks() {
        viewModelScope.launch {
            runCatching(exceptionHandler) {
                viewState = viewState.copy(loadState = LoadState.Loading)
                getTasksUseCase.invoke()
            }.onSuccess { result ->
                allTasks = result.sorted().map { mapper.mapTaskModelToTaskUiModel(it) }
                viewState = viewState.copy(loadState = LoadState.Success, tasks = allTasks)
            }.onFailure { ex ->
                ex.message?.let {
                    viewState = viewState.copy(loadState = LoadState.Error(it))
                }
            }
        }
    }

    private fun filterTasks(index: Int): List<TaskUiModel> {
        return when (reminderTabs[index]) {
            R.string.tab_all -> allTasks
            R.string.watering_tasks -> allTasks.filter { it.type == TaskType.WATER }
            R.string.fertilizer_tasks -> allTasks.filter { it.type == TaskType.FERTILIZER }
            else -> return emptyList()
        }
    }
}