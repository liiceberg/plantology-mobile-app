package ru.itis.liiceberg.reminder_impl.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.itis.liiceberg.reminder_api.domain.repository.ReminderRepository
import ru.itis.liiceberg.reminder_impl.data.ReminderRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
interface ReminderModule {
    @Binds
    fun bindReminderRepositoryToReminderRepositoryImpl(repository: ReminderRepositoryImpl) : ReminderRepository
}