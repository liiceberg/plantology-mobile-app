package ru.itis.liiceberg.myplants_impl.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface MyPlantsModule {
//    @Binds
//    fun bindMyPlantsRepositoryToAuthRepositoryImpl(repository: AuthRepositoryImpl) : AuthRepository
}