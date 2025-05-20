package ru.itis.liiceberg.common.notification

import android.content.Context
import ru.itis.liiceberg.common.model.TaskType

interface NotificationManagerWrapper {

    fun notify(
        id: Int = 1,
        channel: Channel = Channel.DEFAULT,
        plantName: String,
        type: TaskType,
    )

    enum class Channel(val channelName: String, val channelDescription: String) {
        DEFAULT("Default", "Default channel");

        fun getId(context: Context) = "${context.packageName}_$channelName"
    }
}