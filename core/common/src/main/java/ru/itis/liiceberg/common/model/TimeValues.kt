package ru.itis.liiceberg.common.model

data class TimeValues(
    val periodValue: Int,
    val periodUnit: TimeUnit,
)

enum class TimeUnit { DAYS, WEEKS, MONTHS }
