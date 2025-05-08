package ru.itis.liiceberg.common.model

import androidx.compose.runtime.Immutable

@Immutable
data class TimeValues(
    val periodValue: Int = 0,
    val periodUnit: TimeUnit = TimeUnit.DAYS,
)

enum class TimeUnit { DAYS, WEEKS, MONTHS }
