package ru.itis.liiceberg.reminder_impl.presentation.screens

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.itis.liiceberg.common.exceptions.ExceptionHandlerDelegate
import ru.itis.liiceberg.common.exceptions.runCatching
import ru.itis.liiceberg.common.model.TaskType
import ru.itis.liiceberg.reminder_api.presentation.model.TaskUiModel
import ru.itis.liiceberg.reminder_api.presentation.model.Time
import ru.itis.liiceberg.reminder_impl.R
import ru.itis.liiceberg.reminder_impl.domain.CompleteTaskUseCase
import ru.itis.liiceberg.reminder_impl.domain.GetTasksUseCase
import ru.itis.liiceberg.reminder_impl.presentation.mapper.TaskModelMapper
import ru.itis.liiceberg.ui.base.BaseViewModel
import ru.itis.liiceberg.ui.model.LoadState
import javax.inject.Inject

@HiltViewModel
class ReminderViewModel @Inject constructor(
    private val getTasksUseCase: GetTasksUseCase,
    private val completeTaskUseCase: CompleteTaskUseCase,
    private val mapper: TaskModelMapper,
    private val exceptionHandler: ExceptionHandlerDelegate,
) : BaseViewModel<ReminderState, ReminderEvent, ReminderAction>(ReminderState()) {

    private var allTasks = mapOf<Time, List<TaskUiModel>>()

    override fun init() {
        viewModelScope.launch {
            runCatching(exceptionHandler) {
                viewState = viewState.copy(loadState = LoadState.Loading)
                getTasksUseCase.invoke()
            }.onSuccess { result ->
                allTasks =
                    result.sorted().map { mapper.mapTaskModelToTaskUiModel(it) }.groupBy { it.time }
                viewState = viewState.copy(loadState = LoadState.Success, tasks = allTasks)
            }.onFailure { ex ->
                ex.message?.let {
                    viewState = viewState.copy(loadState = LoadState.Error(it))
                }
            }
        }
    }

    override fun obtainEvent(event: ReminderEvent) {
        when (event) {
            ReminderEvent.ScreenOpened -> init()
            is ReminderEvent.OnTabSelected -> {
                event.index.let {
                    viewState = viewState.copy(selectedTab = it, tasks = filterTasks(it))
                }
            }

            is ReminderEvent.OnTaskClick -> {
                makeTaskCompleted(event.id, event.key)
            }
        }
    }

    private fun makeTaskCompleted(id: String, key: Time) {
        viewModelScope.launch {
            runCatching(exceptionHandler) {
                viewState = viewState.copy(
                    tasks = mapValues(viewState.tasks) { time, tasks ->
                        if (time == key) {
                            tasks.map { if (it.id == id) it.copy(completed = true) else it }
                        } else {
                            tasks
                        }
                    }
                )
                completeTaskUseCase(id)
            }.onSuccess { result ->
                allTasks = mapValues(allTasks) { k, tasks ->
                    if (k == key) tasks.filterNot { it.id == id } else tasks
                }
                viewState = viewState.copy(
                    tasks = mapValues(viewState.tasks) { k, tasks ->
                        if (k == key) tasks.filterNot { it.id == id } else tasks
                    }
                )
            }.onFailure { ex ->
                ex.message?.let {
                    viewState = viewState.copy(
                        loadState = LoadState.Error(it),
                        tasks = mapValues(viewState.tasks) { time, tasks ->
                            if (time == key) {
                                tasks.map { if (it.id == id) it.copy(completed = false) else it }
                            } else {
                                tasks
                            }
                        }
                    )
                }
            }

        }
    }

    private fun filterTasks(index: Int): Map<Time, List<TaskUiModel>> {
        return when (reminderTabs[index]) {
            R.string.tab_all -> {
                allTasks
            }

            R.string.tab_watering -> {
                mapValues(allTasks) { _, tasks -> tasks.filter { it.type == TaskType.WATER } }
            }

            R.string.tab_fertilizer -> {
                mapValues(allTasks) { _, tasks -> tasks.filter { it.type == TaskType.FERTILIZER } }
            }

            else -> {
                emptyMap()
            }
        }
    }

    private fun mapValues(
        map: Map<Time, List<TaskUiModel>>,
        transform: (Time, List<TaskUiModel>) -> List<TaskUiModel>
    ): Map<Time, List<TaskUiModel>> {
        return map.mapValues { (time, tasks) -> transform(time, tasks) }
            .filterValues { it.isNotEmpty() }
    }
}