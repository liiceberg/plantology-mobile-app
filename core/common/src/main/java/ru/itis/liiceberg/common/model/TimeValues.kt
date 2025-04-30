package ru.itis.liiceberg.common.model

import androidx.compose.runtime.Immutable

@Immutable
data class TimeValues(
    val periodValue: Int,
    val periodUnit: TimeUnit,
)

enum class TimeUnit { DAYS, WEEKS, MONTHS }
