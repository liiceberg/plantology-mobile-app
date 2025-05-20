//package ru.itis.liiceberg.ui.di
//
//import android.app.NotificationManager
//import android.content.Context
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.android.qualifiers.ApplicationContext
//import dagger.hilt.components.SingletonComponent
//import ru.itis.liiceberg.common.notification.NotificationManagerWrapper
//import ru.itis.liiceberg.ui.notification.NotificationManagerWrapperImpl
//
//@Module
//@InstallIn(SingletonComponent::class)
//class UiModule {
//
//    @Provides
//    fun provideNotificationWrapper(@ApplicationContext context: Context): NotificationManagerWrapper {
//        val notificationManager =
//            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//        return NotificationManagerWrapperImpl(context, notificationManager)
//    }
//
//}