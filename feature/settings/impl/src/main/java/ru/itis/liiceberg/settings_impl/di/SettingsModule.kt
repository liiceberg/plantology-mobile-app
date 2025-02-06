package ru.itis.liiceberg.settings_impl.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.itis.liiceberg.settings_api.domain.repository.SettingsRepository
import ru.itis.liiceberg.settings_impl.data.SettingsRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
interface SettingsModule {
    @Binds
    fun bindSettingsRepositoryToSettingsRepositoryImpl(repository: SettingsRepositoryImpl) : SettingsRepository
}