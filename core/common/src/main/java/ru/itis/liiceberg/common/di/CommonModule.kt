package ru.itis.liiceberg.common.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import ru.itis.liiceberg.common.resources.ResourceManager
import ru.itis.liiceberg.common.resources.ResourceManagerImpl

@Module
@InstallIn(SingletonComponent::class)
interface CommonModule {

    @Binds
    fun bindResourceManagerToResourceManagerImpl(impl:ResourceManagerImpl): ResourceManager

}