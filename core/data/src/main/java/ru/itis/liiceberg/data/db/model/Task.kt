package ru.itis.liiceberg.data.db.model

import com.google.firebase.Timestamp
import ru.itis.liiceberg.common.model.TaskType

data class Task(
    val favId: String = "",
    val type: TaskType = TaskType.UNKNOWN,
    val lastCaringDate: Timestamp? = null,
)
