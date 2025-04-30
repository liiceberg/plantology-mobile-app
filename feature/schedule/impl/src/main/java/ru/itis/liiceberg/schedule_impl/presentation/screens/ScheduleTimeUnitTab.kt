package ru.itis.liiceberg.schedule_impl.presentation.screens

import androidx.annotation.StringRes
import ru.itis.liiceberg.common.model.TimeUnit
import ru.itis.liiceberg.schedule_impl.R

data class ScheduleTimeUnitTab(
    val unit: TimeUnit,
    @StringRes val stringId: Int,
)

val tabs = listOf(
    ScheduleTimeUnitTab(TimeUnit.DAYS, R.string.day_tab),
    ScheduleTimeUnitTab(TimeUnit.WEEKS, R.string.week_tab),
    ScheduleTimeUnitTab(TimeUnit.MONTHS, R.string.months_tab),
)
