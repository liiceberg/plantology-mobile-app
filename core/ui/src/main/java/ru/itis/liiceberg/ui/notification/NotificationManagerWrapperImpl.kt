//package ru.itis.liiceberg.ui.notification
//
//import android.app.Notification
//import android.app.NotificationChannel
//import android.app.NotificationManager
//import android.app.PendingIntent
//import android.content.Context
//import android.content.Intent
//import android.graphics.BitmapFactory
//import androidx.core.app.NotificationCompat
//import ru.itis.liiceberg.common.model.TaskType
//import ru.itis.liiceberg.common.notification.NotificationManagerWrapper
//import ru.itis.liiceberg.common.notification.NotificationManagerWrapper.Channel
//import ru.itis.liiceberg.common.resources.ResourceManager
//import ru.itis.liiceberg.ui.R
//
//class NotificationManagerWrapperImpl(
//    private val context: Context,
//    private val notificationManager: NotificationManager,
//    private val resourceManager: ResourceManager,
//) : NotificationManagerWrapper {
//
//    init {
//        createChannel()
//    }
//
//    private fun createChannel() {
//        val channels = Channel.entries.map {
//            NotificationChannel(
//                it.getId(context),
//                it.channelName,
//                NotificationManager.IMPORTANCE_DEFAULT
//            ).apply {
//                description = it.channelDescription
//            }
//        }
//
//        notificationManager.createNotificationChannels(channels)
//    }
//
//    override fun notify(
//        id: Int,
//        channel: Channel,
//        plantName: String,
//        type: TaskType,
//    ) {
//        val textId = when(type) {
//            TaskType.WATER -> R.string.notification_watering_text
//            else -> R.string.notification_fertilizer_text
//        }
//
////        val intent = Intent(context, "").apply {
////            flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
////        }
//        val notification = getNotification(
//            id = id,
//            channel = channel,
//            title = resourceManager.getString(R.string.notification_title),
//            smallIcon = R.mipmap.icon_primary,
//            largeIcon = R.mipmap.icon_primary,
//            text = resourceManager.getString(textId, plantName),
//            intent = intent)
//
//        notificationManager.notify(id, notification)
//    }
//
//    private fun getNotification(
//        id: Int,
//        channel: Channel,
//        title: String?,
//        smallIcon: Int,
//        largeIcon: Int,
//        text: String?,
//        intent: Intent,
//    ): Notification {
//        val style = NotificationCompat.BigTextStyle().bigText(text)
//        val pendingIntent = PendingIntent.getActivity(
//            context,
//            id,
//            intent,
//            PendingIntent.FLAG_IMMUTABLE
//        )
//
//        return NotificationCompat.Builder(context, channel.getId(context))
//            .setStyle(style)
//            .setSmallIcon(smallIcon)
//            .setLargeIcon(
//                BitmapFactory.decodeResource(
//                    context.resources,
//                    largeIcon
//                )
//            )
//            .setContentTitle(title)
//            .setContentText(text)
//            .setContentIntent(pendingIntent)
//            .setAutoCancel(true)
//            .build()
//    }
//}