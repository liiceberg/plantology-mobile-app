package ru.itis.liiceberg.schedule_impl.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.itis.liiceberg.schedule_api.domain.repository.EditScheduleRepository
import ru.itis.liiceberg.schedule_impl.data.EditScheduleRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
interface ScheduleModule {
    @Binds
    fun bindEditScheduleRepositoryToEditScheduleRepositoryImpl(repository: EditScheduleRepositoryImpl) : EditScheduleRepository
}