package ru.itis.liiceberg.myplants_impl.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.itis.liiceberg.myplants_api.domain.MyPlantsRepository
import ru.itis.liiceberg.myplants_impl.data.MyPlantsRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
interface MyPlantsModule {
    @Binds
    fun bindMyPlantsRepositoryToMyPlantsRepositoryImpl(repository: MyPlantsRepositoryImpl) : MyPlantsRepository
}