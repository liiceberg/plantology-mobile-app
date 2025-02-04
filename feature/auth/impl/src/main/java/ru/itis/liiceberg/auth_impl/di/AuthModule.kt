package ru.itis.liiceberg.auth_impl.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.itis.liiceberg.auth_api.domain.repository.AuthRepository
import ru.itis.liiceberg.auth_impl.data.AuthRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
interface AuthModule {
    @Binds
    fun bindAuthRepositoryToAuthRepositoryImpl(repository: AuthRepositoryImpl) : AuthRepository
}