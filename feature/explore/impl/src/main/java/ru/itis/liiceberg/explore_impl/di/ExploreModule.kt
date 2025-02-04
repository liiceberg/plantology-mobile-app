package ru.itis.liiceberg.explore_impl.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.itis.liiceberg.explore_api.domain.repository.ExploreRepository
import ru.itis.liiceberg.explore_impl.data.ExploreRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
interface ExploreModule {
    @Binds
    fun bindExploreRepositoryToExploreRepositoryImpl(repository: ExploreRepositoryImpl) : ExploreRepository
}